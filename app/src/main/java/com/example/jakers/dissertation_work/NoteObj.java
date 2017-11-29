package com.example.jakers.dissertation_work;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Jakers on 28/11/2017.
 */

public class NoteObj {

    public String Note_Subject_Description;
    public String Note_Description;
    public Timestamp Note_Time;


    public void setNote_Subject_Description(String note_Subject_Description) {
        Note_Subject_Description = note_Subject_Description;
    }

    public void setNote_Time(Timestamp note_Time) {
        Note_Time = note_Time;
    }

    public void setNote_Description(String note_Description) {
        Note_Description = note_Description;
    }

    public String getNote_Subject_Description() {
        return Note_Subject_Description;
    }

    public String getNote_Description() {
        return Note_Description;
    }

    public Timestamp getNote_Time() {
        return Note_Time;
    }

}
