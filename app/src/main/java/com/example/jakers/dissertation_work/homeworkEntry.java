package com.example.jakers.dissertation_work;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
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
 * Editing of homework tasks, which creates a Toast.
 *
 */

public class homeworkEntry extends Activity {
    DatabaseHelper dbhelper;
    SQLiteDatabase db;
    Spinner subjectSpinner;
    private ArrayList<subjectObj> subjectList;
    private ArrayList<String> subjects;
    private ArrayList<homeworkObj> homeworkList;
    private List<String> subject;
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

        // Gets subjects, stores in an array list - to be used by spinner.
        for(subjectObj so : subjectList){
            subjects.add(so.getDescription());}

        // Adds subject list to spinner
        ArrayAdapter<String> subjectForSpinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, subjects);
        subjectForSpinnerAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(subjectForSpinnerAdapter);
        // Sets homework entry floating action buttn
        FloatingActionButton addHomework  = (FloatingActionButton) findViewById(R.id.addHomework);
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

                ArrayList<String> homework = new ArrayList<>();

                // Gets reference to the object if exists
                homeworkList = dbhelper.getAllHomeworkForSubject(subject);
                for(homeworkObj hwo : homeworkList){
                    homework.add(hwo.getHomework_desc().toLowerCase());}

                // Tests to see if the element already exists.
                if(!homework.contains(title.toLowerCase())){
                    dbhelper.addHomework(title, subject, date);
                    Intent goBack = new Intent(getApplicationContext(), homework.class);
                    goBack.putExtra("homeworkName", title);
                    goBack.putExtra("homeworkSubject", subject);
                    startActivity(goBack);
                } else {
                    Toast.makeText(getApplicationContext(), "That homework task already exists " +
                            "for this subject",
                            Toast.LENGTH_LONG).show();

                }
    }});
}}