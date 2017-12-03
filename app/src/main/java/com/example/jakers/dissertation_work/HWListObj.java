package com.example.jakers.dissertation_work;

/**
 * Created by Jakers on 01/12/2017.
 */

public class HWListObj {
    String homework_desc;

    public void setHomework_desc(String homework_desc) {
        this.homework_desc = homework_desc;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    String task_desc;
    String completed;

    public String getHomework_desc() {
        return homework_desc;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public String getCompleted() {
        return completed;
    }}
