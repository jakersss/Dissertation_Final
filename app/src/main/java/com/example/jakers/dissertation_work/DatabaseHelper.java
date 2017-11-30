package com.example.jakers.dissertation_work;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

// Create rest of database and test all methods (ONGOING)
// Add username references in all methods.
// Add
// Get references to objects as strings, not as values in memory.
// Then, homework landing pages
// Then, individual homework pages
// Then, views of the database.
// Then, the rest of the pages
// Then, when everything's in place and tested, work on database structure. Make sure it inserts neatly, etc.

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
    private static final String COLUMN_USER_USERNAME = "user_username";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    // Create column statements for subject table
    private static final String COLUMN_SUBJECT_USERNAME = "user_subject_username";
    private static final String COLUMN_SUBJECT_SUBJECT = "subject_description";
    private static final String COLUMN_SUBJECT_TEACHER = "subject_teacher";
    private static final String COLUMN_SUBJECT_ROOM = "subject_room";

    // Create column statements for lesson information
    private static final String COLUMN_LESSON_DESCRIPTION = "lesson_subject_description";
    private static final String COLUMN_LESSON_RESOURCE = "lesson_subject_resource";
    private static final String COLUMN_LESSON_RESOURCEURL = "lesson_subject_resource_url";

    // Create column statements for lesson timetable
    private static final String COLUMN_LESSONTIMETABLE_SUBJECTDESC = "subject_description";
    private static final String COLUMN_LESSONTIMETABLE_ROOMID = "room_id";
    private static final String COLUMN_LESSONTIMETABLE_WEEKOCCURANCE = "week occurance";
    private static final String COLUMN_LESSONTIMETABLE_DAY = "lesson_day";
    private static final String COLUMN_LESSONTIMETABLE_TIME = "lesson_time";

    // Create column statements for notes
    private static final String COLUMN_NOTE_SUBJECT_DESC = "note_subject";
    private static final String COLUMN_NOTE_DESCRIPTION = "note_description";
    private static final String COLUMN_NOTE_TIMESTAMP = "note_timestamp";

    // Create columns for Homework
    private static final String COLUMN_HOMEWORK_ID = "homework_id";
    private static final String COLUMN_HOMEWORK_SUBJECT = "homework_subject";
    private static final String COLUMN_HOMEWORK_DESC = "homework_description";
    private static final String COLUMN_HOMEWORK_TASK  = "homework_task";
    private static final String COLUMN_HOMEWORK_DUEDATE = "homework_due_date";

    // Create columns for homework_resources
    private static final String COLUMN_HOMEWORK_RESOURCE_DESCRIPTION = "Homework_Description";
    private static final String COLUMN_RESOURCE = "Homework_Resource_Desc";
    private static final String COLUMN_RESOURCEURL = "Homework_Resource_URL";
    private static final String COLUMN_RESOURCEINTENT = "Homework_ResourceIntent";
    private static final String COLUMN_RESOURCE_TIMESTAMP = "Homework_Resource_Timestamp";

    // Set table names
    static final String TABLE_NAME = "user";
    private static final String TABLE_SUBJECT = "subject";
    private static final String TABLE_RESOURCE = "resources";
    private static final String TABLE_LESSON_TIMETABLE = "lesson_timetable";
    private static final String TABLE_NOTES = "notes";
    private static final String TABLE_CALENDAR_EVENT = "calendar_event";
    private static final String TABLE_HOMEWORK = "Homework";
    private static final String TABLE_HOMEWORK_RESOURCE = "Homework_resource";

    // Create column for Calendar_Events
    private static final String COLUMN_CALENDAR_EVENT_ID = "calendar_event_id";
    private static final String COLUMN_CALENDAR_EVENT_TITLE = "calendar_event_title";
    private static final String COLUMN_CALENDAR_EVENT_DESCRIPTION = "calendar_event_description";
    private static final String COLUMN_CALENDAR_EVENT_STARTDATE = "calendar_startDate";
    private static final String COLUMN_CALENDAR_EVENT_STARTTIME = "calendar_startTime";
    private static final String COLUMN_CALENDAR_EVENT_ENDTIME = "calendar_endTime";

    // Create table statements for notes
    private static final String CREATE_NOTE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NOTES + "(" +
                    COLUMN_NOTE_DESCRIPTION+ " TEXT NOT NULL UNIQUE, " +
                    COLUMN_NOTE_SUBJECT_DESC + " TEXT, " +
                    COLUMN_NOTE_TIMESTAMP + " DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (" + COLUMN_NOTE_SUBJECT_DESC + ") REFERENCES " +
                    TABLE_SUBJECT + "(" + COLUMN_SUBJECT_SUBJECT + "));";

    // Create table for calendar events
    private static final String CREATE_CALENDAR_EVENT =
            "CREATE TABLE IF NOT EXISTS " + TABLE_CALENDAR_EVENT +  "(" +
                    COLUMN_CALENDAR_EVENT_ID + " INTEGER NOT NULL PRIMARY KEY, " +
                    COLUMN_CALENDAR_EVENT_TITLE + " TEXT, " +
                    COLUMN_CALENDAR_EVENT_DESCRIPTION + " TEXT " +
                    COLUMN_CALENDAR_EVENT_STARTDATE + " DATE, " +
                    COLUMN_CALENDAR_EVENT_STARTTIME + " TIME, " +
                    COLUMN_CALENDAR_EVENT_ENDTIME + " TIME);";

    // Create table statement for homework_resource
    // Validation - else "that doesn't exist"
    private static final String CREATE_HOMEWORK_RESOURCE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_HOMEWORK_RESOURCE + "(" +
                    COLUMN_HOMEWORK_RESOURCE_DESCRIPTION + " TEXT, " +
                    COLUMN_RESOURCE + " TEXT UNIQUE, " +
                    COLUMN_RESOURCEURL + " TEXT UNIQUE, " +
                    COLUMN_RESOURCEINTENT + " TEXT UNIQUE, " +
                    COLUMN_RESOURCE_TIMESTAMP + " DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (" + COLUMN_HOMEWORK_RESOURCE_DESCRIPTION + ") REFERENCES " +
                    TABLE_HOMEWORK + "(" + COLUMN_HOMEWORK_DESC + "));";
    // Create table statement - user
    private static final String CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                    COLUMN_USER_USERNAME + " TEXT PRIMARY KEY, " +
                    COLUMN_USER_NAME + " TEXT, " +
                    COLUMN_USER_PASSWORD + " TEXT, " +
                    "UNIQUE (" + COLUMN_USER_USERNAME + ", " +
                    COLUMN_USER_PASSWORD + "));";

    // Create table statement - subject
    private static final String CREATE_SUBJECT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_SUBJECT + "(" +
                    COLUMN_SUBJECT_USERNAME + " TEXT, " +
                    COLUMN_SUBJECT_SUBJECT + " TEXT, " +
                    COLUMN_SUBJECT_TEACHER + " TEXT, " +
                    COLUMN_SUBJECT_ROOM + " INT, " +
                    " FOREIGN KEY" + "(" + COLUMN_SUBJECT_USERNAME + ")"
                    + " REFERENCES " + TABLE_NAME + "(" + COLUMN_USER_USERNAME + "));";

    // Create table statement - lesson timetable
    private static final String CREATE_LESSON_TIMETABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_LESSON_TIMETABLE + "(" +
                    COLUMN_LESSONTIMETABLE_SUBJECTDESC + " TEXT, " +
                    COLUMN_LESSONTIMETABLE_ROOMID + " INT, " +
                    COLUMN_LESSONTIMETABLE_WEEKOCCURANCE + " INT, " +
                    COLUMN_LESSONTIMETABLE_DAY + " INT, " +
                    COLUMN_LESSONTIMETABLE_TIME + " TIME, " +
                    "FOREIGN KEY (" + COLUMN_LESSONTIMETABLE_SUBJECTDESC + ") REFERENCES " +
                    TABLE_SUBJECT + "(" + COLUMN_SUBJECT_SUBJECT + "));";

    // Create table statement - lesson resources
    private static final String CREATE_LESSON_RESOURCE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_RESOURCE + "(" +
                    COLUMN_LESSON_DESCRIPTION + " TEXT, " +
                    COLUMN_LESSON_RESOURCE + " TEXT, " +
                    COLUMN_LESSON_RESOURCEURL + " TEXT, " +
                    "FOREIGN KEY (" + COLUMN_LESSON_DESCRIPTION + ") REFERENCES " +
                    TABLE_SUBJECT + "(" + COLUMN_SUBJECT_SUBJECT + "));";

    // Create table statement for homework
    // error handlign for this plz
    private static final String CREATE_HOMEWORK_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_HOMEWORK + "(" +
                    COLUMN_HOMEWORK_ID + " INT NOT NULL PRIMARY KEY, " +
                    COLUMN_HOMEWORK_DESC + " TEXT, " +
                    COLUMN_HOMEWORK_SUBJECT + " TEXT, " +
                    COLUMN_HOMEWORK_TASK + " TEXT, " +
                    COLUMN_HOMEWORK_DUEDATE + " DATE, " +
                    "FOREIGN KEY (" + COLUMN_HOMEWORK_SUBJECT + ") REFERENCES " +
                    TABLE_SUBJECT + "(" + COLUMN_SUBJECT_SUBJECT + ")," +
                    "UNIQUE (" + COLUMN_HOMEWORK_DESC + ", " +
                    COLUMN_HOMEWORK_SUBJECT + "));";

    // Implements data to login - how this is managed needs to be changed.
    private static final String SETUP_USER_ACCOUNTS1 =
            "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES " + "(" +
                    "'a.turing'," +  " 'Alan Turing', " +  "'Enigma');";

    private static final String SETUP_USER_ACCOUNTS2 =
            "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES " + "(" +
                    "'p.hastings'," +  " 'Paul Hastings', " +  " 'Incredible');";

    private static final String SETUP_USER_ACCOUNTS3 =
            "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES " + "(" +
                    "'p.andre'," +  " 'Peter Andre', " +  " 'Singing');";

    // Implements data to subject
    private static final String SUBJECT_ENGLISH =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'English', " +
                    "'Mr Ipsum', '902');";

    private static final String SUBJECT_FRENCH =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'French', " +
                    "'Mrs Pote', '902');";

    private static final String SUBJECT_GEOG =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'Geography', " +
                    "'Mr Craig', '902');";

    private static final String SUBJECT_MATHS =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'Maths', " +
                    "'Mr Daniels', '902');";

    private static final String SUBJECT_PE =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'PE', " +
                    "'Mr Chris', '902');";

    private static final String SUBJECT_SCIENCE =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'Science', " +
                    "'Mr Davies', '902');";

    // Insert statements for homework activities

    // Drop table statements
    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String DROP_SUBJECT_TABLE = "DROP TABLE IF EXISTS " + TABLE_SUBJECT;
    private static final String DROP_RESOURCE_TABLE = "DROP TABLE IF EXISTS " + TABLE_RESOURCE;
    private static final String DROP_LESSON_TIMETABLE_TABLE = "DROP TABLE IF EXISTS " + TABLE_LESSON_TIMETABLE;
    private static final String DROP_TABLE_NOTES = "DROP TABLE IF EXISTS " + TABLE_NOTES;
    private static final String DROP_CALENDAR_EVENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_CALENDAR_EVENT;
    private static final String DROP_HOMEWORK_TABLE= "DROP TABLE IF EXISTS " + TABLE_HOMEWORK;
    private static final String DROP_HOMEWORK_RESOURCE_TABLE = "DROP TABLE IF EXISTS " + TABLE_HOMEWORK_RESOURCE;

    //Constructor
    DatabaseHelper(Context context) {
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

    /* General methods */
    // Returns count of any table
    long countRows(String tableName) {
        String dbName = " ";
        switch (tableName) {
            case "TABLE_NOTES":
                dbName = TABLE_NOTES;
                break;
            case "TABLE_CALENDAR_EVENT":
                dbName = TABLE_CALENDAR_EVENT;
                break;
            case "TABLE_NAME":
                dbName = TABLE_NAME;
                break;
            case "TABLE_SUBJECT":
                dbName = TABLE_SUBJECT;
                break;
            case "TABLE_RESOURCE":
                dbName = TABLE_RESOURCE;
                break;
            case "TABLE_HOMEWORK":
                dbName = TABLE_HOMEWORK;
                break;
            case "TABLE_HOMEWORK_RESOURCE":
                dbName = TABLE_HOMEWORK_RESOURCE;
                break;
            case "TABLE_LESSON_TIMETABLE":
                dbName = TABLE_LESSON_TIMETABLE;
                break;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, dbName);
        db.close();
        return count;}

    /* Methods for the note table */
    // Add note
    public void addNote(String noteDescription, String subjectDescription){
        SQLiteDatabase db = this.getWritableDatabase();

        String insertStatement = "INSERT OR ABORT INTO " + TABLE_NOTES +
                " VALUES ( '" + noteDescription + "',  '" + subjectDescription
                + "', " + "CURRENT_TIMESTAMP);";
        db.execSQL(insertStatement);
        db.close();
    }

    // Remove note
    public void removeNote(String noteDescription){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete a row
        db.delete(TABLE_NOTES, COLUMN_NOTE_DESCRIPTION + " = ?",
                new String[]{String.valueOf(noteDescription)});
        db.close();
    }

    // Get all notes (ordered by most recent)
    public ArrayList<NoteObj> getAllNotes(){
        String[] columns = {
                COLUMN_NOTE_DESCRIPTION,
                COLUMN_NOTE_SUBJECT_DESC,
                COLUMN_NOTE_TIMESTAMP};

        ArrayList<NoteObj> allNotes = new ArrayList<NoteObj>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sortOrder = COLUMN_NOTE_TIMESTAMP + " ASC";

        Cursor cursor = db.query(TABLE_NOTES,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (cursor.moveToNext()) {
                NoteObj note = new NoteObj();
                note.setNote_Description(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_DESCRIPTION)));
                note.setNote_Subject_Description(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_SUBJECT_DESC)));
                note.setNote_Time(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TIMESTAMP))));
                // Time stamp
                allNotes.add(note);
            }
        }
        db.close();
        cursor.close();
        return allNotes;}

    // Get all notes for specific subject (ordered by most recent)
    // Not working - getting all subbjects.
    public ArrayList<NoteObj> getNotesForSubject(String Subject){
        String[] columns = {
                COLUMN_NOTE_DESCRIPTION,
                COLUMN_NOTE_SUBJECT_DESC,
                COLUMN_NOTE_TIMESTAMP};

        ArrayList<NoteObj> notesForSubject = new ArrayList<NoteObj>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_NOTE_SUBJECT_DESC + " = ? ";
        String[] selectionArgs = {Subject};
        String sortOrder = COLUMN_NOTE_TIMESTAMP + " ASC";

        Cursor cursor = db.query(TABLE_NOTES,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        if (cursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (cursor.moveToNext()) {
                NoteObj note = new NoteObj();
                note.setNote_Description(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_DESCRIPTION)));
                note.setNote_Subject_Description(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_SUBJECT_DESC)));
                note.setNote_Time(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TIMESTAMP))));
                // Time stamp
                notesForSubject.add(note);
            }
        }
        db.close();
        cursor.close();
        return notesForSubject;
    }

    public NoteObj returnSpecificNote(String noteDescription) {
        String[] columns = {
                COLUMN_NOTE_DESCRIPTION,
                COLUMN_NOTE_SUBJECT_DESC,
                COLUMN_NOTE_TIMESTAMP};

        ArrayList<NoteObj> notesForSubject = new ArrayList<NoteObj>();

        SQLiteDatabase db = this.getReadableDatabase();
        NoteObj note = new NoteObj();
        String selection = COLUMN_NOTE_DESCRIPTION + " = ? ";
        String[] selectionArgs = {noteDescription};
        String sortOrder = COLUMN_NOTE_TIMESTAMP + " ASC";

        Cursor cursor = db.query(TABLE_NOTES,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        if (cursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (cursor.moveToNext()) {
                note.setNote_Description(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_DESCRIPTION)));
                note.setNote_Subject_Description(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_SUBJECT_DESC)));
                note.setNote_Time(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TIMESTAMP))));
                // Time stamp
                notesForSubject.add(note);
            }
        }
        cursor.close();
        db.close();
        return note;}

    // Methods for the calendar event table
    // Adds calendar event
    // Add validation...
    void addCalendarEvent(String title, String Event_Description,  String Event_StartDate,
                          String Event_StartTime, String Event_EndTime) {

        long count = countRows("TABLE_CALENDAR_EVENT");

        count =+ 1;

        SQLiteDatabase db = this.getWritableDatabase();

        String addEvent = "INSERT OR ABORT INTO " + TABLE_CALENDAR_EVENT +
                " VALUES (" + count + ", '" + title + "', '" + Event_Description + "', '" + Event_StartDate + "', '"
                + Event_StartTime + "', '" + Event_EndTime + "');";

        db.execSQL(addEvent);
        db.close();}

    // Remove calendar event
    void removeCalendarEvent(String Event_Description){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete a row
        db.delete(TABLE_CALENDAR_EVENT, COLUMN_CALENDAR_EVENT_DESCRIPTION + " = ?",
                new String[]{String.valueOf(Event_Description)});
        db.close();}

    // Get all calendar events for a specific date
    // Events cannot span dates.
    public ArrayList<calendarObj> getCalendarEventForDate(String date){
        String[] columns = {
                COLUMN_CALENDAR_EVENT_TITLE,
                COLUMN_CALENDAR_EVENT_DESCRIPTION,
                COLUMN_CALENDAR_EVENT_STARTTIME,
                COLUMN_CALENDAR_EVENT_ENDTIME};

        ArrayList<calendarObj> allEvents = new ArrayList<calendarObj>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sortOrder = COLUMN_CALENDAR_EVENT_STARTTIME + " ASC";

        String selection = COLUMN_CALENDAR_EVENT_STARTDATE + " = ? ";

        // Selection argument
        String[] selectionArgs = {date};

        Cursor cursor = db.query(TABLE_CALENDAR_EVENT,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        if (cursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (cursor.moveToNext()){
                calendarObj c = new calendarObj();
                c.setEvent_Title(cursor.getString(cursor.getColumnIndex(COLUMN_CALENDAR_EVENT_ID)));
                c.setEvent_Description(cursor.getString(cursor.getColumnIndex(COLUMN_CALENDAR_EVENT_DESCRIPTION)));
                c.setEvent_startTime(cursor.getString(cursor.getColumnIndex(COLUMN_CALENDAR_EVENT_STARTTIME)));
                c.setEvent_endTime(cursor.getString(cursor.getColumnIndex(COLUMN_CALENDAR_EVENT_DESCRIPTION)));
                allEvents.add(c);
            }
        }
        db.close();
        cursor.close();
        return allEvents;}

    // Get a specific event, stored as a calendar object.
    public ArrayList<calendarObj> getCalendarEventBasedOnTitle(String title){
        String[] columns = {
                COLUMN_CALENDAR_EVENT_TITLE,
                COLUMN_CALENDAR_EVENT_DESCRIPTION,
                COLUMN_CALENDAR_EVENT_STARTTIME,
                COLUMN_CALENDAR_EVENT_ENDTIME};

        ArrayList<calendarObj> allEvents = new ArrayList<calendarObj>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sortOrder = COLUMN_CALENDAR_EVENT_STARTTIME + " ASC";

        String selection = COLUMN_CALENDAR_EVENT_TITLE + " = ? ";

        // Selection argument
        String[] selectionArgs = {title};

        Cursor cursor = db.query(TABLE_CALENDAR_EVENT,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        if (cursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (cursor.moveToNext()){
                calendarObj c = new calendarObj();
                c.setEvent_Title(cursor.getString(cursor.getColumnIndex(COLUMN_CALENDAR_EVENT_TITLE)));
                c.setEvent_Description(cursor.getString(cursor.getColumnIndex(COLUMN_CALENDAR_EVENT_DESCRIPTION)));
                c.setEvent_startTime(cursor.getString(cursor.getColumnIndex(COLUMN_CALENDAR_EVENT_STARTTIME)));
                c.setEvent_endTime(cursor.getString(cursor.getColumnIndex(COLUMN_CALENDAR_EVENT_DESCRIPTION)));
                allEvents.add(c);}}
        cursor.close();
        db.close();
        return allEvents;}

        // Add homework record
        public void addHomework(String Homework_desc, String Homework_Subject, String Homework_Task,
                                String Homework_dueDate) {

            long rowCount = countRows("TABLE_HOMEWORK");

            SQLiteDatabase db = this.getWritableDatabase();

            String insert = "INSERT OR ABORT INTO " + TABLE_HOMEWORK + " VALUES (" + rowCount + ", '" +
                    Homework_desc + "', '" + Homework_Subject + "', '" + Homework_Task + "', '" +
                    Homework_dueDate + "');";
            try{
            db.execSQL(insert);} catch (SQLException e){
                Log.e(DATABASE_NAME, e.toString());}}

    // Remove homework record
    public void removeHomework(String homeworkDescription){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete a row
        db.delete(TABLE_HOMEWORK, COLUMN_HOMEWORK_DESC + " = ?",
            new String[]{String.valueOf(homeworkDescription)});
        db.close();}

    // Update homework table
    public void updateHomework(String Homework_desc, String Homework_Subject, String Homework_Task,
                               String Homework_dueDate){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HOMEWORK_DESC, Homework_desc);
        values.put(COLUMN_HOMEWORK_SUBJECT, Homework_Subject);
        values.put(COLUMN_HOMEWORK_TASK, Homework_Task);
        values.put(COLUMN_HOMEWORK_DUEDATE, Homework_dueDate);

        db.update(TABLE_HOMEWORK, values, COLUMN_HOMEWORK_DESC + " = ?",
                new String[]{String.valueOf(Homework_desc)});

        db.close();}

        public homeworkObj getHomeworkDetails(String homeworkTitle){
            String[] columns = {
                    COLUMN_HOMEWORK_DESC,
                    COLUMN_HOMEWORK_SUBJECT,
                    COLUMN_HOMEWORK_TASK,
                    COLUMN_HOMEWORK_DUEDATE};

            // Will fail if multiple records are implemented.
            homeworkObj homeworkobj = new homeworkObj();
            SQLiteDatabase db = this.getReadableDatabase();

            String selection = COLUMN_HOMEWORK_DESC + " = ? ";
            String[] selectionArgs = {homeworkTitle};

            Cursor cursor = db.query(TABLE_HOMEWORK,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null);

            if (cursor.getCount() > 0){
                while (cursor.moveToNext()) {
                    homeworkobj.setHomework_task(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_TASK)));
                    homeworkobj.setHomework_subject(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_SUBJECT)));
                    homeworkobj.setHomework_desc(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_DESC)));
                    homeworkobj.setHomework_duedate(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_DUEDATE)));
                }

        }return homeworkobj;}

        // Get all homework items
        // Implement flags into this method
        ArrayList<homeworkObj> getAllHomework(){
            String[] columns = {
                    COLUMN_HOMEWORK_DESC,
                    COLUMN_HOMEWORK_SUBJECT,
                    COLUMN_HOMEWORK_DUEDATE};

            ArrayList<homeworkObj> homeworkObjects = new ArrayList<homeworkObj>();
            SQLiteDatabase db = this.getReadableDatabase();

            String sortOrder = COLUMN_HOMEWORK_DUEDATE + " DESC";
            Cursor cursor = db.query(TABLE_HOMEWORK,
                    columns,
                    null,
                    null,
                    null,
                    null,
                    sortOrder);

            if (cursor.getCount() > 0) {
                // ... while there is another value in the DB ...
                while (cursor.moveToNext()) {
                    homeworkObj hwo = new homeworkObj();
                    hwo.setHomework_desc(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_DESC)));
                    hwo.setHomework_duedate(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_DUEDATE)));
                    hwo.setHomework_subject(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_SUBJECT)));
                    homeworkObjects.add(hwo);
                }
            }
            db.close();
            cursor.close();
            return homeworkObjects;}

        // Get all homework for a specific day
        ArrayList<homeworkObj> getHomeworksForDay(String date){
            String[] columns = {
                    COLUMN_HOMEWORK_DESC,
                    COLUMN_HOMEWORK_SUBJECT,
                    COLUMN_HOMEWORK_TASK,
                    COLUMN_HOMEWORK_DUEDATE};

            ArrayList<homeworkObj> homeworkObjects = new ArrayList<homeworkObj>();
            SQLiteDatabase db = this.getReadableDatabase();

            String selection = COLUMN_HOMEWORK_DUEDATE + " = ? ";
            String[] selectionArgs = {date};
            String sortOrder = COLUMN_HOMEWORK_DUEDATE + " DESC";

            Cursor cursor = db.query(TABLE_HOMEWORK,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder);

            if (cursor.getCount() > 0) {
                // ... while there is another value in the DB ...
                while (cursor.moveToNext()) {
                    homeworkObj hwo = new homeworkObj();
                    hwo.setHomework_desc(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_DESC)));
                    hwo.setHomework_duedate(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_DUEDATE)));
                    hwo.setHomework_subject(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_SUBJECT)));
                    hwo.setHomework_task(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_TASK)));
                    homeworkObjects.add(hwo);
                }
            }
            db.close();
            cursor.close();
            return homeworkObjects;}

        public int countHomeworksForSubject(String subject){
            String[]columns = {COLUMN_HOMEWORK_DESC};

            ArrayList<homeworkObj> homeworkCount = new ArrayList<homeworkObj>();
            SQLiteDatabase db = this.getReadableDatabase();

            String selection = COLUMN_HOMEWORK_SUBJECT + " = ? ";
            String[] selectionArgs = {subject};

            Cursor cursor = db.query(TABLE_HOMEWORK,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null);

            return cursor.getCount();}

        // Get all homework for a specific subject
        public ArrayList<homeworkObj> getAllHomeworkForSubject(String subject){

        String[] columns = {
            COLUMN_HOMEWORK_DESC,
            COLUMN_HOMEWORK_SUBJECT,
            COLUMN_HOMEWORK_TASK,
            COLUMN_HOMEWORK_DUEDATE};

        ArrayList<homeworkObj> homeworkObjects = new ArrayList<homeworkObj>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_HOMEWORK_SUBJECT + " = ? ";
        String[] selectionArgs = {subject};
        String sortOrder = COLUMN_HOMEWORK_DUEDATE + " DESC";

        Cursor cursor = db.query(TABLE_HOMEWORK,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

                if (cursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (cursor.moveToNext()) {
                homeworkObj hwo = new homeworkObj();
                hwo.setHomework_desc(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_DESC)));
                hwo.setHomework_duedate(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_DUEDATE)));
                hwo.setHomework_subject(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_SUBJECT)));
                hwo.setHomework_task(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_TASK)));
                homeworkObjects.add(hwo);
            }
        }
                db.close();
                cursor.close();
                return homeworkObjects;}

        /* Methods for the homework resource table */
        public void addHomeworkResource(String AssociatedHomework, String ResourceDesc,
                                        String ResourceURL, String ResourceIntent){

            SQLiteDatabase db = this.getWritableDatabase();

            String insert = "INSERT OR ABORT INTO " + TABLE_HOMEWORK_RESOURCE + " VALUES ('" +
                    AssociatedHomework + "', '" + ResourceDesc + "', '" + ResourceURL + "', '" +
                    ResourceIntent + "', " + "CURRENT_TIMESTAMP );";
            db.execSQL(insert);
            db.close();}

        // Remove a homework resource
        public void removeHomeworkResource(String ResourceDesc){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_HOMEWORK_RESOURCE, COLUMN_HOMEWORK_RESOURCE_DESCRIPTION + " = ?",
                    new String[]{String.valueOf(ResourceDesc)});}

        public String getAllTasksForHomework(String homeworkTask){
        String[] columns = {COLUMN_HOMEWORK_TASK};
        SQLiteDatabase db = this.getReadableDatabase();

        String tasks;
        String selection = COLUMN_HOMEWORK_DESC + " = ? ";
        String[] selectionArgs = {homeworkTask};

        Cursor cursor = db.query(TABLE_HOMEWORK,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        cursor.moveToFirst();
        tasks = cursor.getString(cursor.getColumnIndex(COLUMN_HOMEWORK_TASK));
        return tasks;
        }

        // Get all homework resources for a specific homework task
        public ArrayList<homeworkResourceObj> getHomeworkResourcesForHomework(String homeworkTask){
        String[] columns = {
                    COLUMN_HOMEWORK_RESOURCE_DESCRIPTION,
                    COLUMN_RESOURCE,
                    COLUMN_RESOURCEURL,
                    COLUMN_RESOURCEINTENT,
                    COLUMN_RESOURCE_TIMESTAMP};

        ArrayList<homeworkResourceObj> homeworkResourceObjects = new ArrayList<homeworkResourceObj>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_HOMEWORK_RESOURCE_DESCRIPTION + " = ? ";
        String[] selectionArgs = {homeworkTask};
        String sortOrder = COLUMN_RESOURCE_TIMESTAMP + " DESC";

        Cursor cursor = db.query(TABLE_HOMEWORK_RESOURCE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

                if (cursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (cursor.moveToNext()) {
                homeworkResourceObj hwro = new homeworkResourceObj();
                hwro.setResource_description(cursor.getString(cursor.getColumnIndex(COLUMN_RESOURCE)));
                hwro.setResource_Intent(cursor.getString(cursor.getColumnIndex(COLUMN_RESOURCEINTENT)));
                // Don't think this will work. Need to get timestamp.
                hwro.setResource_Timestamp(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_RESOURCE_TIMESTAMP))));
                hwro.setResource_URL(cursor.getString(cursor.getColumnIndex(COLUMN_RESOURCEURL)));
                homeworkResourceObjects.add(hwro);
            }
        }
                db.close();
                cursor.close();
                return homeworkResourceObjects;}

    /* Methods for lesson table */
    // Get all lessons for a specific date, for a specific week (A or B)
    public ArrayList<LessonTimetableObj> returnAllLessonsForDayBasedOnWeek(String week, String dayOfWeek){

        String[] columns = { COLUMN_LESSONTIMETABLE_SUBJECTDESC,
                COLUMN_LESSONTIMETABLE_ROOMID,
                COLUMN_LESSONTIMETABLE_WEEKOCCURANCE,
                COLUMN_LESSONTIMETABLE_DAY,
                COLUMN_LESSONTIMETABLE_TIME };

        ArrayList<LessonTimetableObj> ltoa = new ArrayList<LessonTimetableObj>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_LESSONTIMETABLE_WEEKOCCURANCE + " = ? " + " AND " + COLUMN_LESSONTIMETABLE_DAY + " = ? ";
        String[] selectionArgs = {week, dayOfWeek};

        Cursor cursor = db.query(TABLE_LESSON_TIMETABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null
                );

        if (cursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (cursor.moveToNext()) {
                LessonTimetableObj lto = new LessonTimetableObj();
                lto.setDay(cursor.getString(cursor.getColumnIndex(COLUMN_LESSONTIMETABLE_DAY)));
                lto.setRoom(cursor.getString(cursor.getColumnIndex(COLUMN_LESSONTIMETABLE_ROOMID)));
                lto.setSubjectDesc(cursor.getString(cursor.getColumnIndex(COLUMN_LESSONTIMETABLE_SUBJECTDESC)));
                lto.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_LESSONTIMETABLE_TIME)));
                ltoa.add(lto);
            }
        } return ltoa;}

    /* Methods for lesson resource table */
    // Add a lesson resource
    public void addLessonResource(String description, String resourceName, String resourceURL){
        SQLiteDatabase db = this.getWritableDatabase();

        String insert = "INSERT INTO " + TABLE_RESOURCE + " VALUES ('" + description + "', '" +
                resourceName + "', '" + resourceURL + ");";

        db.execSQL(insert);}

    public void removeLessonResource(String description){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_RESOURCE, COLUMN_LESSON_RESOURCE + " = ?",
                new String[]{String.valueOf(description)});}

    // Get all resource information for a specific subject
    ArrayList<lessonResourceObj> getAllResourcesForSubject(String subject){
      String[] columns = {
              COLUMN_HOMEWORK_RESOURCE_DESCRIPTION,
              COLUMN_LESSON_RESOURCE,
              COLUMN_LESSON_RESOURCEURL};

      ArrayList<lessonResourceObj> lessonResource = new ArrayList<lessonResourceObj>();
      SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SUBJECT,
            columns,
            null,
            null,
            null,
            null,
            null);

        if (cursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (cursor.moveToNext()) {
                lessonResourceObj lessonobj = new lessonResourceObj();
                lessonobj.setLesson_description(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_DESCRIPTION)));
                lessonobj.setLesson_resource(COLUMN_LESSON_RESOURCE);
                lessonobj.setLesson_resource_URL(COLUMN_LESSON_RESOURCEURL);
                lessonResource.add(lessonobj);
            }
        }
        db.close();
        cursor.close();
        return lessonResource;}

    /* Methods for the subjects table */
    // Gets all subjects
    ArrayList<subjectObj> getAllSubjects(){
        String[] columns = {
                COLUMN_SUBJECT_SUBJECT,
                COLUMN_SUBJECT_TEACHER,
                COLUMN_SUBJECT_ROOM};

        ArrayList<subjectObj> subjectInfo = new ArrayList<subjectObj>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sortOrder = COLUMN_SUBJECT_SUBJECT + " ASC";

        Cursor cursor = db.query(TABLE_SUBJECT,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.getCount() > 0) {
            // ... while there is another value in the DB ...
            while (cursor.moveToNext()) {
                subjectObj subjectobj = new subjectObj();
                String lesson = cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT_SUBJECT));
                subjectobj.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT_SUBJECT)));
                subjectobj.setTeacher(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT_TEACHER)));
                subjectobj.setRoom(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT_ROOM)));
                subjectobj.setNumHomeworks(countHomeworksForSubject(lesson));
                subjectInfo.add(subjectobj);
            }
        }
        db.close();
        cursor.close();
        return subjectInfo;}

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
        db.close();
    }

    HashMap<String, String> getUsernamesAndNames(){
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
        db.close();
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
        db.close();
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
        db.close();
    }

// Deletes a user record
    public void deleteUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete a row
        db.delete(TABLE_NAME, COLUMN_USER_USERNAME + " = ?",
                new String[]{String.valueOf(username)});
        db.close();
    }

    // Constructor for checking a username and password
    // Query for validation - tomorrow.
    boolean validateLogin(String username, String password){
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
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }}