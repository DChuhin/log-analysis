package com.test.task.service;

import com.test.task.model.LogItem;
import com.test.task.model.ProcessingContext;
import com.test.task.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileProcessor implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileProcessor.class);

    private final File file;
    private final List<LogItem> localContainer = new LinkedList<>();
    private final ProcessingContext processingContext;

    public FileProcessor(File file, ProcessingContext processingContext) {
        this.file = file;
        this.processingContext = processingContext;
    }

    @Override
    public void run() {
        try {
            LOGGER.info("Start processing file " + file.getAbsolutePath());
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                LogItem logItem = LogUtil.parseLine(scanner.nextLine());
                localContainer.add(logItem);
            }
            scanner.close();
            processingContext.getLogItems().addAll(localContainer);
            processingContext.getFilesProcessed().add(file.getAbsolutePath());
            LOGGER.info("File " + file.getAbsolutePath() + " has been processed successfully");
        } catch (Exception e) {
            LOGGER.error("Error parsing file " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }


}
