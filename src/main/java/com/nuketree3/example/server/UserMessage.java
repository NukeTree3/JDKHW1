package com.nuketree3.example.server;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserMessage {

    private final LocalDateTime date;
    private final String username;
    private final String message;
    private final DateTimeFormatter formatter;

    public UserMessage(String username, String message) {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.username = username;
        this.message = message;
        date = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date.format(formatter) + "\n" + username + ": " + message + "\n";
    }
}
