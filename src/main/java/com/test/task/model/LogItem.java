package com.test.task.model;

import java.time.LocalDateTime;

public class LogItem {

    private String userName;

    private LocalDateTime dateTime;

    private String message;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return userName + "," + dateTime + "," + message;
    }
}
