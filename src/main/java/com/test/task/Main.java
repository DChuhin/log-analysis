package com.test.task;

import com.test.task.model.ParamContainer;
import com.test.task.service.LogAnalyzer;
import com.test.task.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.test.task.util.LogUtil.dtf;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            ParamContainer params = readParams();
            LOGGER.info("Configuration was read successfully: \n" + params);
            File root = new File(params.getInputDir());
            List<File> logFiles = new ArrayList<>();
            LogUtil.collectLogFiles(root, logFiles);
            LOGGER.info("List of files to process: \n" + logFiles);
            LogAnalyzer.getLogs(logFiles, params);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Error: " + e.getMessage());
        }
    }

    private static ParamContainer readParams() throws IOException {
        Properties props = new Properties();
        props.load(Main.class.getClassLoader().getResourceAsStream("application.properties"));
        String inputDir = props.getProperty("param.input.dir");
        String output = props.getProperty("param.output.file");
        String startTimeString = props.getProperty("param.filter.start");
        String endTimeString = props.getProperty("param.filter.end");
        String timeUnitString = props.getProperty("param.group.timeUnit");
        String groupByUserString = props.getProperty("param.group.username");
        String threadCountString = props.getProperty("param.thread.count");
        if (inputDir.isEmpty() || groupByUserString.isEmpty() || threadCountString.isEmpty() || output.isEmpty()) {
            throw new RuntimeException("param.group.username, param.input.dir, param.thread.count, param.output.file params must be specified");
        }
        return ParamContainer.newBuilder()
                .withInputDir(inputDir)
                .withUserName(props.getProperty("param.filter.username"))
                .withStartTime(startTimeString.isEmpty() ? null : LocalDateTime.parse(startTimeString, dtf))
                .withEndTime(endTimeString.isEmpty() ? null : LocalDateTime.parse(endTimeString, dtf))
                .withPattern(props.getProperty("param.filter.pattern"))
                .withGroupByUser(Boolean.valueOf(groupByUserString))
                .withTimeUnit(timeUnitString.isEmpty() ? null : TimeUnit.valueOf(timeUnitString))
                .withThreadCount(Integer.parseInt(threadCountString))
                .withOutputFile(output)
                .build();
    }
}
