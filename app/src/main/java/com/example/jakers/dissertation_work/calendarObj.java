package com.example.jakers.dissertation_work;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Jakers on 29/11/2017.
 */

public class calendarObj {
    String Event_Title;
    String Event_Description;
    String Event_startTime;
    String Event_endTime;

    public void setEvent_Title(String event_Title) {
        Event_Title = event_Title;
    }

    public String getEvent_Title() {
        return Event_Title;
    }

    public String getEvent_Description() {
        return Event_Description;
    }

    public String getEvent_startTime() {
        return Event_startTime;
    }

    public String getEvent_endTime() {
        return Event_endTime;
    }

    public void setEvent_Description(String event_Description) {
        Event_Description = event_Description;
    }

    public void setEvent_startTime(String event_startTime) {
        Event_startTime = event_startTime;
    }

    public void setEvent_endTime(String event_endTime) {
        Event_endTime = event_endTime;
    }}