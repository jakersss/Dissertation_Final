package com.example.jakers.dissertation_work;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Jakers on 02/12/2017.
 * To do:
 * Populate pages with dummy data
 * Obtain data from the database, and put into the page
 */

public class homeworkViewIndividual extends AppCompatActivity {

    DatabaseHelper dbhelper;
    SQLiteDatabase db;
    String retrievedTitle, retrievedSubject, dueDate;
    homeworkIndividualAdapter adapter;

    // Currently testing to check if data can be obtained from the intent.
    // When it is, I'll add the adapter to add data.
    protected void onCreate(Bundle savedInstanceState) {
        // Set title for page
        Intent passedFromHomework = getIntent();
        Bundle titleAndSubject = passedFromHomework.getExtras();
        // Gets the variables from the passed intent, stores as local variables for
        // subject and homework title.
        if (titleAndSubject != null) {
            retrievedTitle = (String) titleAndSubject.get("title");
            retrievedSubject = (String) titleAndSubject.get("subject");
            dueDate = (String) titleAndSubject.get("date");
            // sets the title
            getSupportActionBar().setTitle(retrievedTitle);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.homework_individual);
            dbhelper = new DatabaseHelper(this);
            db = dbhelper.getReadableDatabase();
            adapter = new homeworkIndividualAdapter(getApplicationContext(),
                    retrievedTitle, dueDate, retrievedSubject, this);
            // Gets intent, sets a bundle to retrieve the data, then passes this data.
        }}}