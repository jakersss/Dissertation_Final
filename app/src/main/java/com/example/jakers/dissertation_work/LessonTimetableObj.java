package com.example.jakers.dissertation_work;

/**
 * Created by Jakers on 29/11/2017.
 */

public class LessonTimetableObj {

    String SubjectDesc;
    String Room;
    String WeekOccurance;
    String Day;
    String Time;

    public void setSubjectDesc(String subjectDesc) {
        SubjectDesc = subjectDesc;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public void setWeekOccurance(String weekOccurance) {
        WeekOccurance = weekOccurance;
    }

    public void setDay(String day) {
        Day = day;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getSubjectDesc() {

        return SubjectDesc;
    }

    public String getRoom() {
        return Room;
    }

    public String getWeekOccurance() {
        return WeekOccurance;
    }

    public String getDay() {
        return Day;
    }

    public String getTime() {
        return Time;
    }
}
