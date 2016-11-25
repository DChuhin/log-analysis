package com.test.task.filtering;

import com.test.task.model.LogItem;

public interface LogFilter {

    boolean applyFilter(LogItem logItem);

}
