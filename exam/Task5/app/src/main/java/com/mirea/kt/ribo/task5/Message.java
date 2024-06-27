package com.mirea.kt.ribo.task5;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private String text;
    private LocalDateTime localDateTime;
    private boolean isChecked;

    public Message(int id, String text, LocalDateTime localDateTime, boolean isChecked) {
        this.id = id;
        this.text = text;
        this.localDateTime = localDateTime;
        this.isChecked = isChecked;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public boolean isChecked() {
        return isChecked;
    }
}