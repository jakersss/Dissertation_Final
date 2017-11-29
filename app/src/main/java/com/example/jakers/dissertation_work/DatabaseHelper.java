package com.example.jakers.dissertation_work;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

// Persistent databases - test.
// Test the database is created correctly (DONE)
// Test validating user records works okay (DONE)
// Make sure validation is working on data entry (DONE)
// Fix 'add' functionality - add single quotes. Update testing class, to make sure that data is being added correctly. (DONE)
// Add 'on first load, create database' functionality. (DONE)
// Add 'on first load, import data' functionality. (DONE)
// Implement validation when you click on the login button (DONE)
// Validate on use fields (DONE)
// Git commit (DONE)
// Plan the rest of the database (Done)
    // Add all tables in ERD (Done)
        // Events table for events (Done)
        // Homework table (Done)
        // Note table (Done)
    // Add room FK to subjects (Done)
        // First thing - subject cards.
            // Create database for subjects, test (DONE)
            // Add rooms table and references, test
            // Create cards, test
            // Add images programatically using glide, test
    // Then, individual subject pages
// When everything's in place and tested, work on different views.

// V2:
// Implement cipher, and work on seperate data entry file.


/**
 * Created by Jakers on 18/11/2017.
 * Version 1.0 - first iteration - getting login functionality to work with SQLCrypt
 * Images are implemented with Blob, put into place with Glide.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    /**
     * Creates user table within database planner.db.
     */
    // Database version
    private static final int DATABASE_VERSION = 1;

    // Set database name
    private static final String DATABASE_NAME = "planner.db";

    // Create column statements for user table
    public static final String COLUMN_USER_USERNAME = "user_username";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_PASSWORD = "user_password";

    // Create column statements for subject table
    public static final String COLUMN_SUBJECT_USERNAME = "user_subject_username";
    public static final String COLUMN_SUBJECT_SUBJECT = "subject_description";
    public static final String COLUMN_SUBJECT_TEACHER = "subject_teacher";
    public static final String COLUMN_SUBJECT_ROOM = "subject_room";

    // Create column statements for lesson information
    public static final String COLUMN_LESSON_USERNAME = "lesson_username";
    public static final String COLUMN_LESSON_DESCRIPTION = "lesson_subject_description";
    public static final String COLUMN_LESSON_RESOURCE = "lesson_subject_resource";
    public static final String COLUMN_LESSON_RESOURCEURL = "lesson_subject_resource_url";

    // Create column statements for lesson timetable
    public static final String COLUMN_LESSONTIMETABLE_USERNAME = "lesson_username";
    public static final String COLUMN_LESSONTIMETABLE_SUBJECTDESC = "subject_description";
    public static final String COLUMN_LESSONTIMETABLE_ROOMID = "room_id";
    public static final String COLUMN_LESSONTIMETABLE_WEEKOCCURANCE = "week occurance";
    public static final String COLUMN_LESSONTIMETABLE_DAY = "lesson_day";
    public static final String COLUMN_LESSONTIMETABLE_TIME = "lesson_time";

    // Create column statements for notes
    public static final String COLUMN_NOTE_SUBJECT_DESC = "note_subject";
    public static final String COLUMN_NOTE_DESCRIPTION = "note_description";
    public static final String COLUMN_NOTE_TIMESTAMP = "note_timestamp";

    // Create columns for Homework
    public static final String COLUMN_HOMEWORK_USERNAME = "homework_username";
    public static final String COLUMN_HOMEWORK_SUBJECT = "homework_subject";
    public static final String COLUMN_HOMEWORK_DESC = "homework_description";
    public static final String COLUMN_HOMEWORK_TASK  = "homework_task";
    public static final String COLUMN_HOMEWORK_DUEDATE = "homework_due_date";

    // Create columns for homework_resources
    public static final String COLUMN_HOMEWORK_RESOURCE_DESCRIPTION = "Homework_Description";
    public static final String COLUMN_RESOURCE = "Homework_Resource_Desc";
    public static final String COLUMN_RESOURCEURL = "Homework_Resource_URL";
    public static final String COLUMN_RESOURCEINTENT = "Homework_ResourceIntent";

    // Set table names
    public static final String TABLE_NAME = "user";
    public static final String TABLE_SUBJECT = "subject";
    public static final String TABLE_RESOURCE = "resources";
    public static final String TABLE_LESSON_TIMETABLE = "lesson_timetable";
    public static final String TABLE_NOTES = "notes";
    public static final String TABLE_CALENDAR_EVENT = "calendar_event";
    public static final String TABLE_HOMEWORK = "Homework";
    public static final String TABLE_HOMEWORK_RESOURCE = "Homework_resource";

    // Create column for Calendar_Events
    public static final String COLUMN_CALENDAR_EVENT_USERNAME = "event_username";
    public static final String COLUMN_CALENDAR_EVENT_ID = "calendar_event_id";
    public static final String COLUMN_CALENDAR_EVENT_DESCRIPTION = "calendar_event_description";
    public static final String COLUMN_CALENDAR_EVENT_STARTDATE = "calendar_startDate";
    public static final String COLUMN_CALENDAR_EVENT_ENDDATE = "calendar_endDate";
    public static final String COLUMN_CALENDAR_EVENT_STARTTIME = "calendar_startTime";
    public static final String COLUMN_CALENDAR_EVENT_ENDTIME = "calendar_endTime";

    // Create table statements for notes
    // Delete notes
    // Add notes
    public static final String CREATE_NOTE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NOTES + "(" +
                    COLUMN_NOTE_DESCRIPTION+ " TEXT NOT NULL UNIQUE, " +
                    COLUMN_NOTE_SUBJECT_DESC + " TEXT, " +
                    COLUMN_NOTE_TIMESTAMP + " DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (" + COLUMN_NOTE_SUBJECT_DESC + ") REFERENCES " +
                    TABLE_SUBJECT + "(" + COLUMN_SUBJECT_SUBJECT + "));";

    // Create table for calendar events
    public static final String CREATE_CALENDAR_EVENT =
            "CREATE TABLE IF NOT EXISTS " + TABLE_CALENDAR_EVENT +  "(" +
                    COLUMN_CALENDAR_EVENT_USERNAME + " TEXT, " +
                    COLUMN_CALENDAR_EVENT_ID + " INT NOT NULL PRIMARY KEY, " +
                    COLUMN_CALENDAR_EVENT_DESCRIPTION + " TEXT UNIQUE," +
                    COLUMN_CALENDAR_EVENT_STARTDATE + " DATE, " +
                    COLUMN_CALENDAR_EVENT_ENDDATE + " DATE, " +
                    COLUMN_CALENDAR_EVENT_STARTTIME + " TIME, " +
                    COLUMN_CALENDAR_EVENT_ENDTIME + " TIME, " +
                    "FOREIGN KEY (" + COLUMN_CALENDAR_EVENT_USERNAME + ") REFERENCES " +
                    TABLE_NAME + "(" + COLUMN_USER_USERNAME + "));";

    // Create table statement for homework
    // Delete homework
    // Add homework
    public static final String CREATE_HOMEWORK_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_HOMEWORK + "(" +
                    COLUMN_HOMEWORK_USERNAME + " TEXT, " +
                    COLUMN_HOMEWORK_DESC + " TEXT NOT NULL PRIMARY KEY UNIQUE, " +
                    COLUMN_HOMEWORK_SUBJECT + " TEXT, " +
                    COLUMN_HOMEWORK_TASK + " TEXT, " +
                    COLUMN_HOMEWORK_DUEDATE + " DATE, " +
                    "FOREIGN KEY (" + COLUMN_HOMEWORK_USERNAME + ") REFERENCES " +
                    TABLE_NAME + "(" + COLUMN_USER_USERNAME + "), " +
                    "FOREIGN KEY (" + COLUMN_HOMEWORK_SUBJECT + ") REFERENCES " +
                    TABLE_SUBJECT + "(" + COLUMN_SUBJECT_SUBJECT + "));";

    // Create table statement for homework_resource
    // Validation - else "that doesn't exist"
    public static final String CREATE_HOMEWORK_RESOURCE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_HOMEWORK_RESOURCE + "(" +
                    COLUMN_HOMEWORK_RESOURCE_DESCRIPTION + " TEXT, " +
                    COLUMN_RESOURCE + " TEXT, " +
                    COLUMN_RESOURCEURL + " TEXT, " +
                    COLUMN_RESOURCEINTENT + " TEXT, " +
                    "FOREIGN KEY (" + COLUMN_HOMEWORK_RESOURCE_DESCRIPTION + ") REFERENCES " +
                    TABLE_HOMEWORK + "(" + COLUMN_HOMEWORK_DESC + "));";

    // Create table statement - lesson timetable
    public static final String CREATE_LESSON_TIMETABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_LESSON_TIMETABLE + "(" +
                    COLUMN_LESSONTIMETABLE_USERNAME + " TEXT, " +
                    COLUMN_LESSONTIMETABLE_SUBJECTDESC + " TEXT, " +
                    COLUMN_LESSONTIMETABLE_ROOMID + " INT, " +
                    COLUMN_LESSONTIMETABLE_WEEKOCCURANCE + " INT, " +
                    COLUMN_LESSONTIMETABLE_DAY + " INT, " +
                    COLUMN_LESSONTIMETABLE_TIME + " TIME, " +
                    "FOREIGN KEY (" + COLUMN_LESSONTIMETABLE_USERNAME + ") REFERENCES " +
                    TABLE_NAME + "(" + COLUMN_USER_USERNAME + ")," +
                    "FOREIGN KEY (" + COLUMN_LESSONTIMETABLE_SUBJECTDESC + ") REFERENCES " +
                    TABLE_SUBJECT + "(" + COLUMN_SUBJECT_SUBJECT + "));";

    // Create table statement - user
    public static final String CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                    COLUMN_USER_USERNAME + " TEXT PRIMARY KEY, " +
                    COLUMN_USER_NAME + " TEXT, " +
                    COLUMN_USER_PASSWORD + " TEXT, " +
                    "UNIQUE (" + COLUMN_USER_USERNAME + ", " +
                    COLUMN_USER_PASSWORD + "));";

    // Create table statement - lesson resources
    public static final String CREATE_LESSON_RESOURCE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_RESOURCE + "(" +
                    COLUMN_LESSON_USERNAME + " TEXT, " +
                    COLUMN_LESSON_DESCRIPTION + " TEXT, " +
                    COLUMN_LESSON_RESOURCE + " TEXT, " +
                    COLUMN_LESSON_RESOURCEURL + " TEXT " +
                    COLUMN_LESSON_RESOURCE + " TEXT, " +
                    "FOREIGN KEY (" + COLUMN_LESSON_USERNAME + ") REFERENCES " +
                    TABLE_NAME + "(" + COLUMN_USER_USERNAME + "), " +
                    "FOREIGN KEY (" + COLUMN_LESSON_DESCRIPTION + ") REFERENCES " +
                    TABLE_SUBJECT + "(" + COLUMN_SUBJECT_SUBJECT + "));";

    // Create table statement - subject
    public static final String CREATE_SUBJECT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_SUBJECT + "(" +
                    COLUMN_SUBJECT_USERNAME + " TEXT, " +
                    COLUMN_SUBJECT_SUBJECT + " TEXT, " +
                    COLUMN_SUBJECT_TEACHER + " TEXT, " +
                    COLUMN_SUBJECT_ROOM + " INT, " +
                    " FOREIGN KEY" + "(" + COLUMN_SUBJECT_USERNAME + ")"
                    + " REFERENCES " + TABLE_NAME + "(" + COLUMN_USER_USERNAME + "));";

    // Implements data to login - how this is managed needs to be changed.
    public static final String SETUP_USER_ACCOUNTS1 =
            "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES " + "(" +
                    "'a.turing'," +  " 'Alan Turing', " +  "'Enigma');";

    public static final String SETUP_USER_ACCOUNTS2 =
            "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES " + "(" +
                    "'p.hastings'," +  " 'Paul Hastings', " +  " 'Incredible');";

    public static final String SETUP_USER_ACCOUNTS3 =
            "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES " + "(" +
                    "'p.andre'," +  " 'Peter Andre', " +  " 'Singing');";

    // Implements data to subject
    public static final String SUBJECT_ENGLISH =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'English', " +
                    "'Mr Ipsum', '902');";

    public static final String SUBJECT_FRENCH =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'French', " +
                    "'Mrs Pote', '902');";

    public static final String SUBJECT_GEOG =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'Geography', " +
                    "'Mr Craig', '902');";

    public static final String SUBJECT_MATHS =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'Maths', " +
                    "'Mr Daniels', '902');";

    public static final String SUBJECT_PE =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'PE', " +
                    "'Mr Chris', '902');";

    public static final String SUBJECT_SCIENCE =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'Science', " +
                    "'Mr Davies', '902');";

    // Drop table statements
    public static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String DROP_SUBJECT_TABLE = "DROP TABLE IF EXISTS " + TABLE_SUBJECT;
    public static final String DROP_RESOURCE_TABLE = "DROP TABLE IF EXISTS " + TABLE_RESOURCE;
    public static final String DROP_LESSON_TIMETABLE_TABLE = "DROP TABLE IF EXISTS " + TABLE_LESSON_TIMETABLE;
    public static final String DROP_TABLE_NOTES = "DROP TABLE IF EXISTS " + TABLE_NOTES;
    public static final String DROP_CALENDAR_EVENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_CALENDAR_EVENT;
    public static final String DROP_HOMEWORK_TABLE= "DROP TABLE IF EXISTS " + TABLE_HOMEWORK;
    public static final String DROP_HOMEWORK_RESOURCE_TABLE = "DROP TABLE IF EXISTS " + TABLE_HOMEWORK_RESOURCE;

    //Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Sets up the users
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_SUBJECT_TABLE);
        // Creates other tables
        db.execSQL(CREATE_CALENDAR_EVENT);
        db.execSQL(CREATE_NOTE_TABLE);
        db.execSQL(CREATE_LESSON_TIMETABLE);
        db.execSQL(CREATE_HOMEWORK_TABLE);
        db.execSQL(CREATE_HOMEWORK_RESOURCE);
        db.execSQL(CREATE_LESSON_RESOURCE_TABLE);
        // Sets up the user accounts
        db.execSQL(SETUP_USER_ACCOUNTS1);
        db.execSQL(SETUP_USER_ACCOUNTS2);
        db.execSQL(SETUP_USER_ACCOUNTS3);
        // Sets up the subject tables
        db.execSQL(SUBJECT_ENGLISH);
        db.execSQL(SUBJECT_FRENCH);
        db.execSQL(SUBJECT_GEOG);
        db.execSQL(SUBJECT_MATHS);
        db.execSQL(SUBJECT_PE);
        db.execSQL(SUBJECT_SCIENCE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table if exists
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_CALENDAR_EVENT_TABLE);
        db.execSQL(DROP_HOMEWORK_RESOURCE_TABLE);
        db.execSQL(DROP_HOMEWORK_TABLE);
        db.execSQL(DROP_LESSON_TIMETABLE_TABLE);
        db.execSQL(CREATE_LESSON_RESOURCE_TABLE);
        db.execSQL(DROP_SUBJECT_TABLE);
        db.execSQL(DROP_TABLE_NOTES);
        db.execSQL(DROP_RESOURCE_TABLE);
        // Create tables again
        onCreate(db);}

    /* Methods for the note table */
    // Add note
    public void addNote(String noteDescription, String subjectDescription){
        SQLiteDatabase db = this.getWritableDatabase();

        String insertStatement = "INSERT OR ABORT INTO " + TABLE_NOTES +
                " VALUES ( '" + noteDescription + "',  '" + subjectDescription
                + "', " + "CURRENT_TIMESTAMP" + ");";
        db.execSQL(insertStatement);
    }

    // Remove note
    public void removeNote(String noteDescription){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete a row
        db.delete(TABLE_NOTES, COLUMN_NOTE_DESCRIPTION + " = ?",
                new String[]{String.valueOf(noteDescription)});
    }

    // Get all notes (ordered by most recent)
    public ArrayList<NoteObj> getAllNotes(){
        String[] columns = {
                COLUMN_NOTE_DESCRIPTION,
                COLUMN_NOTE_SUBJECT_DESC,
                COLUMN_NOTE_TIMESTAMP};

        ArrayList<NoteObj> allNotes = new ArrayList<NoteObj>();

        SQLiteDatabase dbSubject = this.getReadableDatabase();

        String sortOrder = COLUMN_NOTE_TIMESTAMP + " ASC";

        Cursor subjCursor = dbSubject.query(TABLE_NOTES,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (subjCursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (subjCursor.moveToNext()) {
                NoteObj note = new NoteObj();
                note.setNote_Description(subjCursor.getString(subjCursor.getColumnIndex(COLUMN_NOTE_DESCRIPTION)));
                note.setNote_Subject_Description(subjCursor.getString(subjCursor.getColumnIndex(COLUMN_NOTE_SUBJECT_DESC)));
                note.setNote_Time(Timestamp.valueOf(subjCursor.getString(subjCursor.getColumnIndex(COLUMN_NOTE_TIMESTAMP))));
                // Time stamp
                allNotes.add(note);
            }
        }
        subjCursor.close();
        return allNotes;}

    // Get all notes for specific subject (ordered by most recent)
    // Not working - getting all subbjects.
    public ArrayList<NoteObj> getNotesForSubject(String Subject){
        String[] columns = {
                COLUMN_NOTE_DESCRIPTION,
                COLUMN_NOTE_SUBJECT_DESC,
                COLUMN_NOTE_TIMESTAMP};

        ArrayList<NoteObj> notesForSubject = new ArrayList<NoteObj>();

        SQLiteDatabase dbSubject = this.getReadableDatabase();

        String selection = COLUMN_NOTE_SUBJECT_DESC + " = ? ";

        String[] selectionArgs = {Subject};

        String sortOrder = COLUMN_NOTE_TIMESTAMP + " ASC";

        Cursor subjCursor = dbSubject.query(TABLE_NOTES,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        if (subjCursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (subjCursor.moveToNext()) {
                NoteObj note = new NoteObj();
                note.setNote_Description(subjCursor.getString(subjCursor.getColumnIndex(COLUMN_NOTE_DESCRIPTION)));
                note.setNote_Subject_Description(subjCursor.getString(subjCursor.getColumnIndex(COLUMN_NOTE_SUBJECT_DESC)));
                note.setNote_Time(Timestamp.valueOf(subjCursor.getString(subjCursor.getColumnIndex(COLUMN_NOTE_TIMESTAMP))));
                // Time stamp
                notesForSubject.add(note);
            }
        }
        subjCursor.close();
        return notesForSubject;
    }

    public NoteObj returnSpecificNote(String noteDescription) {
        String[] columns = {
                COLUMN_NOTE_DESCRIPTION,
                COLUMN_NOTE_SUBJECT_DESC,
                COLUMN_NOTE_TIMESTAMP};

        ArrayList<NoteObj> notesForSubject = new ArrayList<NoteObj>();

        SQLiteDatabase dbSubject = this.getReadableDatabase();

        NoteObj note = new NoteObj();

        String selection = COLUMN_NOTE_DESCRIPTION + " = ? ";

        String[] selectionArgs = {noteDescription};

        String sortOrder = COLUMN_NOTE_TIMESTAMP + " ASC";

        Cursor subjCursor = dbSubject.query(TABLE_NOTES,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        if (subjCursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (subjCursor.moveToNext()) {
                note.setNote_Description(subjCursor.getString(subjCursor.getColumnIndex(COLUMN_NOTE_DESCRIPTION)));
                note.setNote_Subject_Description(subjCursor.getString(subjCursor.getColumnIndex(COLUMN_NOTE_SUBJECT_DESC)));
                note.setNote_Time(Timestamp.valueOf(subjCursor.getString(subjCursor.getColumnIndex(COLUMN_NOTE_TIMESTAMP))));
                // Time stamp
                notesForSubject.add(note);
            }
        } return note; }

    /* Methods for the calendar event table */
    // Add calendar event
    // Remove calendar event
    // Get all calendar events for a specific date

    /* Methods for the homework table */
    // Add homework record
    // Remove homework table
    // Update homework table

    /* Methods for the homework resource table */
    // Add a homework resource
    // Remove a homework resource
    // Get all homework resources for a specific homework task

    /* Methods for lesson table */
    // Get all lessons for a specific date, for a specific week (A or B)

    /* Methods for lesson resource table */
    // Add a lesson resource
    // Get all resource information for a specific subject


    /* Methods for the subjects table */
    public ArrayList<subjectObj> getAllSubjects(){
        String[] columns = {
                COLUMN_SUBJECT_SUBJECT,
                COLUMN_SUBJECT_TEACHER};

        ArrayList<subjectObj> subjectInfo = new ArrayList<subjectObj>();

        SQLiteDatabase dbSubject = this.getReadableDatabase();

        String sortOrder = COLUMN_SUBJECT_SUBJECT + " ASC";

        Cursor subjCursor = dbSubject.query(TABLE_SUBJECT,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (subjCursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (subjCursor.moveToNext()) {
                subjectObj subjectobj = new subjectObj();
                subjectobj.setDescription(subjCursor.getString(subjCursor.getColumnIndex(COLUMN_SUBJECT_SUBJECT)));
                subjectobj.setTeacher(subjCursor.getString(subjCursor.getColumnIndex(COLUMN_SUBJECT_TEACHER)));
                subjectInfo.add(subjectobj);
            }
        }
        subjCursor.close();
        return subjectInfo;}

    /**
     * Methods for the user table
     */
    // Takes string variables and adds them to the DB.
    public void addUser(String username, String name, String password) {
        // Validation - if cannot validate (i.e doesn't exist, create new fields)
        SQLiteDatabase db = this.getWritableDatabase();
        // Single quotes around values, for insert statement
        String u_uname = "'" + username + "'";
        String u_name = "'" + name + "'";
        String pass = "'" + password + "'";
        String userWhole = u_uname + ", " + u_name + ", " + pass;

        // Inserts or aborts if data entry is the same as that which already exists.
        String insertStatement = "INSERT OR ABORT INTO " + TABLE_NAME + " VALUES (" +
                userWhole + ");";
        db.execSQL(insertStatement);
    }

    public HashMap<String, String> getUsernamesAndNames(){
        String[] columns = {
                COLUMN_USER_USERNAME,
                COLUMN_USER_PASSWORD};

        HashMap<String, String> userHashAndName = new HashMap<String, String>();

        String sortOrder = COLUMN_USER_NAME + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                columns,    // To return
                null,       // columns for the where clause
                null,    // values for the where clause
                null,       // group the rows
                null,         // filter by row groups
                sortOrder);         // Sort order

        if (cursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (cursor.moveToNext()) {
                // ... get this information, and store as a string, then add it to a list.
                // For each item in the database, get all fields.
                // User object is created, to be displayed.
                String username = cursor.getString(cursor.getColumnIndex(COLUMN_USER_USERNAME)) + ";";
                //String name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)) + ";";
                String name = (cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                // Adds the info to the DB
                userHashAndName.put(username, name);
            }

        }
        cursor.close();
        // returns the user list
        return userHashAndName;
    }

    private HashMap<String, String> getAllUserNamesAndPasswords() {
        String[] columns = {
                COLUMN_USER_USERNAME,
                COLUMN_USER_PASSWORD};

        // Specify sorting order
        String sortOrder = COLUMN_USER_NAME + " ASC";
        // Creates an arraylist of all users, as strings
        HashMap<String, String> userHash = new HashMap<String, String>();
        // Gets database instance.
        SQLiteDatabase db = this.getReadableDatabase();

        // Queries the user table (cursors provide read-write access to a database query result set
        // Set up the query...
        Cursor cursor = db.query(TABLE_NAME,
                columns,    // To return
                null,       // columns for the where clause
                null,    // values for the where clause
                null,       // group the rows
                null,         // filter by row groups
                sortOrder);         // Sort order

        // Originally  cursor.moveToFirst, do. Changed to better
        // If there is more than 0 records in the DB...
        if (cursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (cursor.moveToNext()) {
                // ... get this information, and store as a string, then add it to a list.
                // For each item in the database, get all fields.
                // User object is created, to be displayed.
                String username = cursor.getString(cursor.getColumnIndex(COLUMN_USER_USERNAME)) + ";";
                //String name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)) + ";";
                String password = (cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                String userInfo = username + " " + " " + password;
                // Adds the info to the DB
                userHash.put(username, password);
                System.out.println(userHash);
            }

        }
        cursor.close();
        // returns the user list
        return userHash;
}


    // Updates a user record's password (not their username on name, these are not likely to change...)
    public void updateUserPassword(String username, String NewPassword) {
        SQLiteDatabase db = this.getWritableDatabase();

//    HashMap<String, String> users = getAllUserNamesAndPasswords();
//    if(users.containsKey(username)){

        // Set content values...
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_USERNAME, username);
        values.put(COLUMN_USER_PASSWORD, NewPassword);

        // Update a row
        // put the values of the new password in place
        // updates to a new password, where username = username
        db.update(TABLE_NAME, values, COLUMN_USER_USERNAME + " = ?",
                new String[]{String.valueOf(username)});

        System.out.println(values);
//} else { System.out.println("User account does not exist. You cannot update the password. First, create the account.");}}
    }

// Deletes a user record
    public void deleteUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete a row
        db.delete(TABLE_NAME, COLUMN_USER_USERNAME + " = ?",
                new String[]{String.valueOf(username)});
    }

    // Constructor for checking a username and password
    // Query for validation - tomorrow.
    public boolean validateLogin(String username, String password){
        // Array of columns
        String[] columns = {
            COLUMN_USER_USERNAME,
            COLUMN_USER_PASSWORD};

        SQLiteDatabase db = this.getReadableDatabase();

        // Selection criteria
        String selection = COLUMN_USER_USERNAME + " = ? " + " AND " + COLUMN_USER_PASSWORD + " = ? ";

        // Selection argument
        String[] selectionArgs = {username, password};

        // Query user table with condition
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order

        // Test this
        int cursorCount = cursor.getCount();
        cursor.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }}