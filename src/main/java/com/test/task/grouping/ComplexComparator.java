package com.test.task.grouping;

import com.test.task.model.LogItem;

import java.util.Comparator;

public class ComplexComparator implements Comparator<LogItem> {

    @Override
    public int compare(LogItem o1, LogItem o2) {
        int nameCompare = o1.getUserName().compareTo(o2.getUserName());
        return nameCompare != 0 ? nameCompare : o1.getDateTime().compareTo(o2.getDateTime());
    }
}
