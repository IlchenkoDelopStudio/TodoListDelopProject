package com.example.todolist;

public class Note {
    private String title;
    private String date;

    public Note(String title, String date) {
        this.title = title;
        this.date = date;
    }


    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}