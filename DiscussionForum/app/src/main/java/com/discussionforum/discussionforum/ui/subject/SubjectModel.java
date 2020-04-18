package com.discussionforum.discussionforum.ui.subject;

public class SubjectModel {

    private String title;
    private String time;
    private String owner;
    private String bio;

    public SubjectModel() {
    }

    public SubjectModel(String title, String owner, String time,String bio) {
        this.title = title;
        this.time = time;
        this.owner = owner;
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
