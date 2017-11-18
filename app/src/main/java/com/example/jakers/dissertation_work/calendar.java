package com.example.jakers.dissertation_work;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.Calendar;
import java.util.Locale;
import java.util.Date;

import io.blackbox_vision.materialcalendarview.view.CalendarView;

// Things to do:
// Add calendar functionality
// Get logins working
// Get database stuff working

/**
 * Created by Jakers on 17/11/2017.
 * @version 1.0
 */

public class calendar extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);

        // Initiated the calendar
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar_view);

        calendarView.shouldAnimateOnEnter(true);
         //       .setFirstDayOfWeek(Calendar.MONDAY)
         //       .setOnDateClickListener(this::onDateClick)
         //       .setOnMonthChangeListener(this::onMonthChange)
         //       .setOnDateLongClickListener(this::onDateLongClick)
         //       .setOnMonthTitleClickListener(this::onMonthTitleClick);

        //if (calendarView.isMultiSelectDayEnabled()) {
        //    calendarView.setOnMultipleDaySelectedListener(this::onMultipleDaySelected);
        //}

        //calendarView.update(Calendar.getInstance(Locale.getDefault()));
        //if (calendarView.isMultiSelectDayEnabled()) {
        //    calendarView.setOnMultipleDaySelectedListener(this::onMultipleDaySelected);
        //}

 //       calendarView.update(Calendar.getInstance(Locale.getDefault()));
    }
}