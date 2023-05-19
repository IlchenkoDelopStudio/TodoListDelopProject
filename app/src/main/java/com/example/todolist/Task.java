package com.example.todolist;


import java.sql.Date;
import java.sql.Time;

public class Task {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public Task(String taskName, Date date, Time time) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}