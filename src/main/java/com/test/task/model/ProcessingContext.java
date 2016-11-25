package com.test.task.model;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ProcessingContext {

    private final Collection<LogItem> logItems = new ConcurrentLinkedQueue<>();
    private final Collection<String> filesProcessed = new ConcurrentLinkedQueue<>();

    public Collection<LogItem> getLogItems() {
        return logItems;
    }

    public Collection<String> getFilesProcessed() {
        return filesProcessed;
    }
}
