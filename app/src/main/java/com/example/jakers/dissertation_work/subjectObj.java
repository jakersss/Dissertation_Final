package com.example.jakers.dissertation_work;

/**
 * Created by Jakers on 26/11/2017.
 */

class subjectObj{

    private String description;
    private String teacher;
    private String room;
    private int picture;
    private int numHomeworks;

    public void setNumHomeworks(int numHomeworks) {this.numHomeworks = numHomeworks;}

    public int getNumHomeworks() {return numHomeworks;}

    public void setRoom(String room) {this.room = room;}

    public String getRoom() {return room;}

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getPicture() {return picture;}

    void setDescription(String description) {
        this.description = description;
    }

    void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    String getDescription() {return description;}

    String getTeacher() {
        return teacher;
    }
}