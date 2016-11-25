package com.test.task.grouping;

import com.test.task.model.LogItem;

import java.util.Comparator;

public class TimeUnitComparator implements Comparator<LogItem> {

    @Override
    public int compare(LogItem o1, LogItem o2) {
        return o1.getDateTime().compareTo(o2.getDateTime());
    }
}
