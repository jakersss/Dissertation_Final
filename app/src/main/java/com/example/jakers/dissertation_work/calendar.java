package com.example.jakers.dissertation_work;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CalendarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;

import static java.util.Calendar.*;

// Things to do:
// Add calendar functionality
// Get logins working
// Get database stuff working

/**
 * Created by Jakers on 17/11/2017.
 * @version 1.0
 */

public class calendar extends AppCompatActivity{

    // Sets arrayList of months, to be compared against to gather the title
    protected ArrayList months = new ArrayList<String>(
            Arrays.asList("January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"));

    // Sets the title for the page
    Calendar c = Calendar.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        CalendarView calendar = (CalendarView) findViewById(R.id.calendar_view);
        calendar.setFirstDayOfWeek(MONDAY);
        setTitle();
    }

    public void setTitle(){
        // gets date, sets as title
        // to do: update so that when you switch between months, the title updates.
        int year = c.get(Calendar.YEAR); int month = c.get(Calendar.MONTH);
        String title = (String) months.get(month) +  " " + year;
        getSupportActionBar().setTitle(title);
    }
}