package com.test.task.grouping;

import com.test.task.model.LogItem;

import java.util.Comparator;

public class UserNameComparator implements Comparator<LogItem> {

    @Override
    public int compare(LogItem o1, LogItem o2) {
        return o1.getUserName().compareTo(o2.getUserName());
    }
}
