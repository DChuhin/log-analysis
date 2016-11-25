package com.test.task.service;

import com.test.task.filtering.LogFilter;
import com.test.task.filtering.PatternFilter;
import com.test.task.filtering.TimePeriodFilter;
import com.test.task.filtering.UserNameFilter;
import com.test.task.grouping.ComplexComparator;
import com.test.task.grouping.TimeUnitComparator;
import com.test.task.grouping.UserNameComparator;
import com.test.task.model.LogItem;
import com.test.task.model.ParamContainer;
import com.test.task.model.ProcessingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class LogAnalyzer {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAnalyzer.class);

    public static void getLogs(List<File> source, ParamContainer params) {
        ProcessingContext processingContext = readLogs(source, params);
        LOGGER.info("Processed files: \n" + processingContext.getFilesProcessed());
        List<LogItem> filteredLogs = filterLogs(processingContext, params);
        sortLogs(filteredLogs, params);
        writeLogs(filteredLogs, params);
    }

    private static ProcessingContext readLogs(List<File> files, ParamContainer params) {
        ExecutorService executorService = Executors.newFixedThreadPool(params.getThreadCount());
        ProcessingContext processingContext = new ProcessingContext();
        files.forEach(file -> executorService.submit(new FileProcessor(file, processingContext)));
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            LOGGER.error("Error, timeout expired");
            e.printStackTrace();
        }
        return processingContext;
    }

    private static List<LogItem> filterLogs(ProcessingContext processingContext, ParamContainer params) {
        List<LogFilter> logFilters = new LinkedList<>();
        if (params.getUserName() != null && !params.getUserName().isEmpty()) {
            logFilters.add(new UserNameFilter(params.getUserName()));
        }
        if (params.getStartTime() != null || params.getEndTime() != null) {
            logFilters.add(new TimePeriodFilter(params.getStartTime(), params.getEndTime()));
        }
        if (params.getPattern() != null && !params.getPattern().isEmpty()) {
            logFilters.add(new PatternFilter(params.getPattern()));
        }
        return processingContext.getLogItems().stream()
                .filter(logItem -> applyFilter(logItem, logFilters))
                .collect(Collectors.toList());

    }

    private static boolean applyFilter(LogItem logItem, List<LogFilter> filters) {
        return !filters.stream().filter(filter -> !filter.applyFilter(logItem)).findAny().isPresent();
    }

    private static void sortLogs(List<LogItem> filteredLogs, ParamContainer params) {
        Comparator<LogItem> comparator = null;
        if (params.isGroupByUser() && params.getTimeUnit() != null) {
            comparator = new ComplexComparator();
        } else if (params.isGroupByUser()) {
            comparator = new UserNameComparator();
        } else {
            if (params.getTimeUnit() != null) {
                comparator = new TimeUnitComparator();
            }
        }
        Collections.sort(filteredLogs, comparator);
    }

    private static void writeLogs(List<LogItem> filteredLogs, ParamContainer params) {
        try {
            PrintWriter pw = new PrintWriter(params.getOutputFile());
            filteredLogs.forEach(pw::println);
            pw.close();
        } catch (FileNotFoundException e) {
            LOGGER.error("Error creating output file");
            e.printStackTrace();
        }

    }

}
