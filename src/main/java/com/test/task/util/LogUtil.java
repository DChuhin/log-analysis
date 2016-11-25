package com.test.task.util;

import com.test.task.model.LogItem;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LogUtil {

    public static final DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private LogUtil() {
    }

    public static LogItem parseLine(String line) {
        String[] split = line.split(",");
        String userName = split[0];
        LocalDateTime dateTime = LocalDateTime.parse(split[1], dtf);
        String message = split[2];
        LogItem logItem = new LogItem();
        logItem.setUserName(userName);
        logItem.setDateTime(dateTime);
        logItem.setMessage(message);
        return logItem;
    }

    public static void collectLogFiles(File root, List<File> fileList) {
        File[] listFiles = root.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    collectLogFiles(file, fileList);
                } else {
                    String[] split = file.getName().split("\\.");
                    if ("log".equals(split[split.length - 1])) {
                        fileList.add(file);
                    }
                }
            }
        }
    }
}
