package com.test.task.filtering;

import com.test.task.model.LogItem;

import java.time.LocalDateTime;

public class TimePeriodFilter implements LogFilter {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimePeriodFilter(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }


    @Override
    public boolean applyFilter(LogItem logItem) {
        if (start == null) {
            return end.compareTo(logItem.getDateTime()) >= 0;
        }
        if (end == null) {
            return start.compareTo(logItem.getDateTime()) <= 0;
        }
        return start.compareTo(logItem.getDateTime()) <= 0 &&
                end.compareTo(logItem.getDateTime()) >= 0;
    }
}
