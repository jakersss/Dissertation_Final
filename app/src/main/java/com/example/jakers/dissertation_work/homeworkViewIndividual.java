package com.example.jakers.dissertation_work;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jakers on 02/12/2017.
 * To do:
 *
 * Add edit text in homework for title
 * Add options button for removal of task
 * Add/remove resources (by swipe/addition)
 * Add/remove tasks (by swipe/addition)
 * Add date picker to change due date
 *
 */

// Perhaps needs to extend activity?
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

    /* Get floating action bar working.
  // Sets on click listener for editor...
        FloatingActionButton addHomework  = (FloatingActionButton) findViewById(R.id.edit_homework);
        addHomework.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {}});*/

    /* get editing of homework task working */

        // Gets the variables from the passed intent, stores as local variables for
        // subject and homework title.
        if (titleAndSubject != null) {
            retrievedTitle = (String) titleAndSubject.get("title");
            retrievedSubject = (String) titleAndSubject.get("subject");
            dueDate = (String) titleAndSubject.get("date");
            // sets the title
            getSupportActionBar().setTitle(retrievedSubject + " : " + retrievedTitle);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.homework_individual);
            dbhelper = new DatabaseHelper(this);
            db = dbhelper.getReadableDatabase();
            adapter = new homeworkIndividualAdapter(getApplicationContext(),
                    retrievedTitle, dueDate, retrievedSubject, this);
            // Gets intent, sets a bundle to retrieve the data, then passes this data.
        }}}