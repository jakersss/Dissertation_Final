package com.example.jakers.dissertation_work;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Contacts;
import android.text.TextUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
// Plan the rest of the database
    // First thing - subject cards.
        // Create database for subjects, test
        // Add rooms table and references, test
        // Create cards, test
        // Add images programatically using glide, test
    // Then, individual subject pages
    // Then homework table, integrating 'homework tasks' into subject page.
// When everything's in place and tested, work on different views.

// V2:
// Implement cipher, and work on seperate data entry file.


/**
 * Created by Jakers on 18/11/2017.
 * Version 1.0 - first iteration - getting login functionality to work with SQLCrypt
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
    public static final String COLUMN_SUBJECT_DESCRIPTION = "subject_description";
    public static final String COLUMN_SUBJECT_TEACHER = "subject_teacher";
    public static final String COLUMN_SUBJECT_TEACHERIMAGE = "subject_image";

    // Set table names
    public static final String TABLE_NAME = "user";
    public static final String TABLE_SUBJECT = "subject";

    // Create table statement - user
    public static final String CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                    COLUMN_USER_USERNAME + " TEXT PRIMARY KEY, " +
                    COLUMN_USER_NAME + " TEXT, " +
                    COLUMN_USER_PASSWORD + " TEXT, " +
                    "UNIQUE (" + COLUMN_USER_USERNAME + ", " +
                    COLUMN_USER_PASSWORD + "));";

    // Create table statement
    // Room is controlled through the room table. Rooms will change depending on the week.
    // Changes made by anyone else but admin is not permitted.
    // Images are added programatically, based on the subject ID.
    public static final String CREATE_SUBJECT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_SUBJECT + "(" +
                    COLUMN_SUBJECT_USERNAME + " TEXT, " +
                    COLUMN_SUBJECT_DESCRIPTION + " TEXT, " +
                    COLUMN_SUBJECT_TEACHER + " TEXT, " +
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
                    "'Mr Ipsum');";

    public static final String SUBJECT_FRENCH =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'French', " +
                    "'Mrs Pote');";

    public static final String SUBJECT_GEOG =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'Geography', " +
                    "'Mr Craig');";

    public static final String SUBJECT_MATHS =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'Maths', " +
                    "'Mr Daniels');";

    public static final String SUBJECT_PE =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'PE', " +
                    "'Mr Chris');";

    public static final String SUBJECT_SCIENCE =
            "INSERT OR IGNORE INTO " + TABLE_SUBJECT + " VALUES ('a.turing', 'Science', " +
                    "'Mr Davies');";

    // Drop table statements
    public static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String DROP_SUBJECT_TABLE = "DROP TABLE IF EXISTS " + TABLE_SUBJECT;

    //Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Sets up the users
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_SUBJECT_TABLE);
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
        // Create tables again
        onCreate(db);
    }

    /* Methods for the subjects table */
    public ArrayList<subjectObj> getAllSubjects(){
        String[] columns = {
                COLUMN_SUBJECT_DESCRIPTION,
                COLUMN_SUBJECT_TEACHER};

        ArrayList<subjectObj> subjectInfo = new ArrayList<subjectObj>();

        SQLiteDatabase dbSubject = this.getReadableDatabase();

        String sortOrder = COLUMN_SUBJECT_DESCRIPTION + " ASC";

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
                subjectobj.setDescription(subjCursor.getString(subjCursor.getColumnIndex(COLUMN_SUBJECT_DESCRIPTION)));
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