package com.test.task.filtering;

import com.test.task.model.LogItem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternFilter implements LogFilter {

    private final Pattern pattern;

    public PatternFilter(String patternString) {
        this.pattern = Pattern.compile(patternString);
    }

    @Override
    public boolean applyFilter(LogItem logItem) {
        Matcher m = pattern.matcher(logItem.getMessage());
        return m.matches();
    }
}
