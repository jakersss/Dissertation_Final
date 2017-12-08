package com.example.jakers.dissertation_work;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jakers on 07/12/2017.
 *
 * Things to add:
 * Populate the spinner with a list of subjects (DONE)
 * Get the information from added homeworks, display them dynamically (DONE)
 * add homework function, which formats the date picked. (DONE)
 * call update of homework list (DONE)
 * add validation to data entry. If already exists for subject, don't enter, else do.
 */

public class homeworkEntry extends Activity {
    DatabaseHelper dbhelper;
    SQLiteDatabase db;
    Spinner subjectSpinner;
    private ArrayList<subjectObj> subjectList;
    private ArrayList<String> subjects;
    private TextView homeworkTitle;
    private DatePicker homeworkDatePicker;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework_entry);
        dbhelper = new DatabaseHelper(getApplicationContext());
        db = dbhelper.getReadableDatabase();
        // Obtains list of subjects from database
        subjectList = dbhelper.getAllSubjects();
        subjects = new ArrayList<String>();
        // Obtains references to items in layout view
        homeworkTitle = (TextView) findViewById(R.id.homeworkNameEntry);
        subjectSpinner = (Spinner) findViewById(R.id.homeworkSubjectEntry);
        homeworkDatePicker = (DatePicker) findViewById(R.id.dueDateEntry);

        // Gets subjects, stores in an array list
        for(subjectObj so : subjectList){
            subjects.add(so.getDescription());}

        // Adds subject list to spinner
        ArrayAdapter<String> subjectForSpinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, subjects);
        subjectForSpinnerAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(subjectForSpinnerAdapter);

        FloatingActionButton addHomework = (FloatingActionButton) findViewById(R.id.addHomework);
        addHomework.setOnClickListener(new View.OnClickListener(){
            // Tested 07/12/2017: wasn't printing date off correctly, formatted and is now working perfectly.
            @Override
            public void onClick(View v) {
                String title = homeworkTitle.getText().toString();
                String subject = subjectSpinner.getSelectedItem().toString();

                // Gets/formats date picker info, stores result as String.
                int day = homeworkDatePicker.getDayOfMonth();
                int month = homeworkDatePicker.getMonth();
                int year = homeworkDatePicker.getYear();
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy");
                Date d = new Date (year, month, day);
                String date = dateFormatter.format(d);

                // Perform validation and enter the data if it doesn't exist.
                // If it does exist, display an error message.
                try {
                    if (title != null) {
                        dbhelper.addHomework(title, subject, date);
                    }
                // This needs to be improved. Doesn't work.
                } catch (SQLiteException e){
                    Toast.makeText(getApplicationContext(), "That record already exists",
                            Toast.LENGTH_LONG).show();
                }
                Intent goBack = new Intent(getApplicationContext(), homework.class);
                goBack.putExtra("homeworkName", title);
                goBack.putExtra("homeworkSubject", subject);
                startActivity(goBack);
            }
        });
    }}