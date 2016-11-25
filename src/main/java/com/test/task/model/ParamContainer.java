package com.test.task.model;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ParamContainer {

    private String inputDir;

    private String userName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String pattern;

    private boolean groupByUser;

    private TimeUnit timeUnit;

    private int threadCount;

    private String outputFile;

    private ParamContainer(Builder builder) {
        userName = builder.userName;
        startTime = builder.startTime;
        endTime = builder.endTime;
        groupByUser = builder.groupByUser;
        timeUnit = builder.timeUnit;
        threadCount = builder.threadCount;
        outputFile = builder.outputFile;
        pattern = builder.pattern;
        inputDir = builder.inputDir;
    }

    public String getInputDir() {
        return inputDir;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getPattern() {
        return pattern;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public boolean isGroupByUser() {
        return groupByUser;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public String getOutputFile() {
        return outputFile;
    }

    @Override
    public String toString() {
        return "ParamContainer{" +
                "inputDir='" + inputDir + '\'' +
                ", userName='" + userName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", pattern='" + pattern + '\'' +
                ", groupByUser=" + groupByUser +
                ", timeUnit=" + timeUnit +
                ", threadCount=" + threadCount +
                ", outputFile='" + outputFile + '\'' +
                '}';
    }

    public static final class Builder {
        private String userName;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private boolean groupByUser;
        private TimeUnit timeUnit;
        private int threadCount;
        private String outputFile;
        private String pattern;
        public String inputDir;

        private Builder() {
        }

        public Builder withUserName(String val) {
            userName = val;
            return this;
        }

        public Builder withStartTime(LocalDateTime val) {
            startTime = val;
            return this;
        }

        public Builder withEndTime(LocalDateTime val) {
            endTime = val;
            return this;
        }

        public Builder withGroupByUser(boolean val) {
            groupByUser = val;
            return this;
        }

        public Builder withPattern(String val) {
            pattern = val;
            return this;
        }

        public Builder withTimeUnit(TimeUnit val) {
            timeUnit = val;
            return this;
        }

        public Builder withThreadCount(int val) {
            threadCount = val;
            return this;
        }

        public Builder withOutputFile(String val) {
            outputFile = val;
            return this;
        }

        public Builder withInputDir(String val) {
            inputDir = val;
            return this;
        }

        public ParamContainer build() {
            return new ParamContainer(this);
        }
    }
}
