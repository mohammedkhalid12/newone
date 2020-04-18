package com.discussionforum.discussionforum;

public class ResponsModel {

    private String author;
    private String time;
    private String content;

    public ResponsModel() {
    }

    public ResponsModel(String author, String time, String content) {
        this.author = author;
        this.time = time;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
