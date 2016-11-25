package com.test.task.filtering;

import com.test.task.model.LogItem;

public class UserNameFilter implements LogFilter {

    private final String userName;

    public UserNameFilter(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean applyFilter(LogItem logItem) {
        return userName.equals(logItem.getUserName());
    }
}
