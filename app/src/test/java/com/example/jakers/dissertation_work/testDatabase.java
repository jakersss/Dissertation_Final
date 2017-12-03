package com.example.jakers.dissertation_work;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.media.MediaMetadataCompat;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dalvik.annotation.TestTarget;

import static com.example.jakers.dissertation_work.DatabaseHelper.TABLE_NAME;

/**
 * Created by Jakers on 19/11/2017.
 */

@RunWith(RobolectricTestRunner.class)

public class testDatabase {

    private Context context;
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    // Tests the add user function.
    @Before
    public void setup() {
        context = RuntimeEnvironment.application;
        // Provides temporary context
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        // Changes are going to take place here because of the sorting order. It's important to note that
        // Comparing users by indexes shouldn't take place in the actual program.
    }

    @After
    public void close() {
        db.close();
    }

/*    @Test
    // Test ID = 2
    // Tests creation and direct entry of data to the database.
    // Test 1: database only writing password field as username, other fields are null
    // Test 1 fix: Was writing to the same 'username' field 3 times. Now fixed.
    // Test 2: addition now working fine. Test demonstrates adding and reading from a database.
    public void Input3UsersAndDisplayThemUsingTheGetAllUsersMethod() {
        HashMap<String, String> users = helper.getAllUserNamesAndPasswords();
        assertEquals(users.keySet().toString(), "[a.turing;, p.andre;, p.hastings;]");
        // Checks to make sure the size is correct. If it's 3, after 3 records have been added, then the records have been added correctly.
        assertEquals(users.size(), 3);
        //System.out.println("expected size of array after initial addition: 3, actual result: " + users.size());
    }

    *//*Test ID = 3 -- needs to be fixed
    NOTE: THIS TEST SHOULD FAIL. IT'S DESIGNED TO FAIL IN ADDING ROWS.
     Tests creation of entry of a repeated field into the database, which should fail
     Test 1: Duplication occurs. Not working.
     Test 2: Added HashMap and tested keyset. Not working.
     Test 3: Validation of username and password occurs before data entry, instead of comparing all values of the
     database. Not working. Duplicate records added.
     Test 4: Altered insert statement to include unique fields. Now not adding!
    *//*@Test
    public void addRepeatUserToNotAdd() {
        helper.addUser("a.turing", "Alan Turing", "Enigma");
        HashMap<String, String> users = helper.getAllUserNamesAndPasswords();
        System.out.println(users.size());
        //System.out.println(users);
        // Gets a new instance of all users
        // Checks to see if the size is the same as it was when the initial 3 records were entered.
        //assertEquals(users.size(), 3);
        //System.out.println("expected size of array after addition of same records twice: 3, actual result: " + users.size());
    }

    *//** Test ID = 4
     // Demonstrates updating a user's password
     // Test 1: Worked first time
     *//*
    @Test
    public void updateUserPasswordCheck(){
        HashMap<String, String> users = helper.getAllUserNamesAndPasswords();
        System.out.println(users);
        // Changing password
        helper.updateUserPassword("a.turing", "boom");
        // Displaying the new records. Notice that they've changed.
        HashMap<String, String> users2 = helper.getAllUserNamesAndPasswords();
        System.out.println(users2);
    }

    *//** Test ID = 5
     // Demonstrates deleting a user
     // Test 1: Worked first time
     *//*
    @Test
    public void deleteUserTestAndDisplayResult(){
        HashMap<String, String> users = helper.getAllUserNamesAndPasswords();
        helper.deleteUser("p.andre");
        HashMap<String, String> users2 = helper.getAllUserNamesAndPasswords();
        System.out.println(users2);
    }

    *//**
     *Test ID = 6
     * Tests to see if validation works.
     * Test 1: Not working, index out of range
     * Fix: Add argument parameter to database helper
     * Test 2: Not working, returning false - not correctly searching the database.
     * Thoughts - are tests persistent?
     * Fix: Updated fields aren't persistent amongst tests.
     * When populated in DatabaseHelper, no longer working.
     *//*
     @Test
    public void loginFailAndSuccess(){
        HashMap<String, String> users = helper.getAllUserNamesAndPasswords();
        boolean shouldFailAsIsntInDatabase = helper.validateLogin("Adrian Charles", "Onions");
        // Changed in previous login. Tests amongst unit tests do not persist.
        boolean shouldntFailAsIsInDatabase = helper.validateLogin("a.turing", "Enigma");
        assertFalse(shouldFailAsIsntInDatabase);
        assertTrue(shouldntFailAsIsInDatabase);
        System.out.println("Record not in database should return false. Returned: " + shouldFailAsIsntInDatabase);
        System.out.println("Record in database should return true. Returned: " + shouldntFailAsIsInDatabase);
    }*/

    // Test ID = 7
    // Tests to see if the subjects returns correctly.
    // Test 1: didn't work because of syntax errors in SQL create statements
    // Test 2: didn't work because of the get all subjects method
    // Didn't work as parameters weren't updated
    // Test 3: didn't print strings, printed references (which is fine).
/*    @Test
    public void testAllSubjects(){
        ArrayList<subjectObj> hmso = helper.getAllSubjects();
        for(subjectObj s : hmso) {
        System.out.println(s);
    }}

    *//*
    * Test ID = 8
    * Tests to see if notes can be added correctly.
    * Test 1: syntax of entry was incorrect
    * Test 2: changed syntax to literal "CURRENT_TIMESTAMP". Now works.
     *//*
    @Test
    public void testAddNote(){
        helper.addNote("This is a test note", "English");
    }

    *//*
    * Test ID = 9
    * Tests to see if notes can be removed correctly
    * Test 1: worked fine.
     *//*
    @Test
    public void testRemoveNote(){
        helper.addNote("This is a test note", "English");
        helper.removeNote("This is a test note");}

    *//*
    * Test ID = 10
    * Tests to see if notes commit correctly
    *
    * Test 1: worked fine
     *//*
    @Test
    public void testCommitsOfAllnotes(){
        helper.addNote("This is a test note", "English");
        ArrayList<NoteObj> note = helper.getAllNotes();
        for(NoteObj n : note){
            System.out.println(n);}}

    *//*
    * Test ID = 11
    * Tests to make sure that notes are unique for specific subjects, and that data of all notes can
    * be gathered on a specific subject.
    * Test 1: "note descriptions are unique"
    * Test 2: not working - values are not unique error
    * Test 3: not working - values are not unique to subject
    * Test 4: Changed parameters on database helper, working fine.
     *//*
    @Test
    public void testAllCommitsForNotesOfASpecificSubject(){
        helper.addNote("This is a test note", "English");
        helper.addNote("This is a 2nd note", "English");
        helper.addNote("Nothing", "French");
        ArrayList<NoteObj> note = helper.getNotesForSubject("English");
        for(NoteObj n : note){
            System.out.println(n);}}

    @Test
    *//*
    * Test ID = 12
    * Tests to see if notes can be obtained, based on a specific note description.
    * Works fine
     *//*
    public void testCommitsForNotesOfSpecificSubjects(){
        helper.addNote("This is a test note", "English");
        helper.addNote("This is a 2nd test note", "English");
        helper.returnSpecificNote("This is a test note");
    }*/


/*    @Test
*//*
    * Test ID = 13
    * Tests to see if counting all values in a specific table works
    * Test 1: Doesn't work - "no such table"
    * Test 2: Works perfectly.
    * Test 3: Added more parameters to the countRows function, all of a sudden not working.
    * Test 4: Added breaks, now working perfectly.
*//*
    public void addRowsAndCountToMakeSureTheRowsHaveCommited(){
        helper.addNote("This is a test note", "English");
        helper.addNote("This is a 2nd note", "English");
        helper.addNote("Nothing", "French");
        long profilecount = helper.countRows("TABLE_NOTES");
        System.out.println(profilecount);
    }*/

/*
    @Test
  * Test ID = 14
    * Tests to see if data can be inserted as a calender event correctly.
    * TEST 1: need to find the correct syntax for inserting times.
    * TEST 2: Done. Not adding still.
    * TEST 3: Dates need quotes.
    * TEST 4: UPDATED CALENDAR ADDING PARAMETERS, SUDDENLY NOT WORKING. HAVE TO UPDATE THE CREATE DATABASE STATEMENT.
    * I THINK THIS SHOULD BE HANDLED BETTER, AS SEPERATING METHODS AND SQL STATEMENTS BECOMES CONFUSING...
    * *//*

/*    public void testAddingCalendarEvents(){
        helper.addCalendarEvent("Test event", "This is a test event", "21/03/2017",
                "21:00:00", "22:00:00");
        System.out.println(helper.countRows("TABLE_CALENDAR_EVENT"));
    }

    *//*Test ID = 15
    * Tests to see if data is removed.
    * Test 1: data is removed effectively. Works.
    * *//*
    @Test
    public void testRemovingCalendarEvents(){
        helper.addCalendarEvent("Test event", "This is a test event", "21/03/2017",
                "21:00:00", "22:00:00");
        System.out.println(helper.countRows("TABLE_CALENDAR_EVENT"));
        helper.removeCalendarEvent("This is a test event");
        System.out.println(helper.countRows("TABLE_CALENDAR_EVENT"));
        }

    *//* Test ID = 16
    *  Tests to see if you can get data for all events on a specific date.
    *  Test 1: works.
     *//*
    @Test
    public void testGetCalendarForSpecificDate(){
        helper.addCalendarEvent("Test event", "This is a test event", "21/03/2017",
                "21:00:00", "22:00:00");
        ArrayList<calendarObj> ObjCalendar = helper.getCalendarEventForDate("15/03/2016");
        for(calendarObj p : ObjCalendar){
            System.out.println(p);
        }
    }

    @Test
    public void testGetCalendarBasedOnTitle(){
        helper.addCalendarEvent("Test event", "This is a test event", "21/03/2017",
                "21:00:00", "22:00:00");
        helper.getCalendarEventBasedOnTitle("Test event");
        ArrayList<calendarObj> ObjCalendar = helper.getCalendarEventForDate("15/03/2016");
        for(calendarObj p : ObjCalendar) {
            System.out.println(p);
        }}*/

 /*   *//**
     * Test ID = 19
     * Tests getting details for a specific homework resource.
     * Changed syntax of command, now works.
     *//*
    @Test
    public void testGettingHomeworkDetails(){
        helper.addHomework("This is a new homework", "English","Hi, There, I," +
                "have, a, task", "19/10/2016");
        helper.getHomeworkDetails("This is a new homework");
    }*/

/*    *//**
     * Test ID = 20
     * Tests removing a homework object
     * Changed syntax of getHomeworkDetails, now works.
     *//*
    @Test
    public void testRemovingHomework(){
        helper.addHomework("This is a new homework", "English","Hi, There, I," +
                "have, a, task", "19/10/2016");
        helper.getHomeworkDetails("This is a new homework");
        helper.removeHomework("This is a new homework");
        helper.getHomeworkDetails("This is a new homework");
    }*/

/*    *//**
     * Test ID = 21
     * Tests updating a homework object
     *//*
    @Test
    public void testUpdatingHomework(){
        helper.addHomework("This is a new homework", "English","Hi, There, I," +
                "have, a, task", "19/10/2016");
        helper.getHomeworkDetails("This is a new homework");
        helper.updateHomework("This is a newish homework", "French", "My, set, of, new, tasks",
                "20/10/2016");
        helper.getHomeworkDetails("This is a newish homework");
    }*/

/*    *//**
     * Test ID = 22
     * Gets all homework for a specific day
     * Test worked first time
     *//*
    @Test
    public void getAllHomeworkForDay(){
        helper.addHomework("This is a new homework", "English","Hi, There, I," +
                "have, a, task", "19/10/2016");
        ArrayList<homeworkObj> homeworks = helper.getHomeworksForDay("19/10/2016");
        for(homeworkObj homework : homeworks){
            String hwd = homework.getHomework_desc();
            String due = homework.getHomework_duedate();
            String sub = homework.getHomework_subject();
            String tasks = homework.getHomework_task();
            System.out.println(hwd + " " + due + " " + sub + " " + tasks);
        }}*/

/*    *//**
     * Test ID = 23
     * Gets all homework for a specific subject
     * Worked first time
     *//*
    @Test
    public void getAllHomeworkForSubject() {
        helper.addHomework("This is a new homework", "English", "Hi, There, I," +
                "have, a, task", "19/10/2016");
        ArrayList<homeworkObj> homeworks = helper.getAllHomeworkForSubject("English");
        for (homeworkObj homework : homeworks) {
            String hwd = homework.getHomework_desc();
            String due = homework.getHomework_duedate();
            String sub = homework.getHomework_subject();
            String tasks = homework.getHomework_task();
            System.out.println(hwd + " " + due + " " + sub + " " + tasks);}}*/

/*    *//**
     * Test ID = 24
     * Gets all homework resources
     * Worked after correcting syntax errors
     *//*
    @Test
    public void addHomeworkResource(){
        helper.addHomework("This is a new homework", "English", "Hi, There, I," +
                "have, a, task", "19/10/2016");
        helper.addHomeworkResource("This is a new homework", "Google drive",
                "https://www.drive.google.com", null);
    }*/

/*    *//**
     * Test ID = 25
     * Gets all homework resources and homework tasks (i.e. information)
     * Had trouble getting this to work - cursorOutOfBounds...
     * Updated getHomeworkResourcesForHomework to include cursor.moveToFirst. Now works.
     *//*
    @Test
    public void getHomeworkTasksForHomework(){
        helper.addHomework("This is a new homework", "English", "Hi, There, I," +
                "have, a, task", "19/10/2016");
        helper.addHomeworkResource("This is a new homework", "Google drive",
                "https://www.drive.google.com", null);
        helper.getAllTasksForHomework("This is a new homework");
        helper.getHomeworkResourcesForHomework("This is a new homework");
    }*/

/*
    */

    /**
     * Test ID = 26
     * Gets all homework details for a specific homework task.
     * Changed syntax of getHomeworkDetails, now works.
     *//*

    @Test
    public void getHomeworkDetailsForHomework(){
        helper.addHomework("This is a new homework", "English", "Hi, There, I," +
                "have, a, task", "19/10/2016");
        homeworkObj homework = helper.getHomeworkDetails("This is a new homework");
        System.out.println(homework);
    }
*/

/*    // Task ID = 27
    // Add homework resouce to homework that exists
    // Failed on homework task - could not insert new task. Syntax was wrong.
    // New tasks are inserted, information for new tasks isn't being obtained.
    // Failed - initial value for homework task was not set correctly.
    // Now working - had to change the value of the task homework object to String.
    @Test
    public void addHomeworkToTask() {
        helper.addHomework("This is a test", "English", "10/12/2017");
        helper.addHomeworkTask("This is a test", "This");
        helper.addHomeworkTask("This is a test", "Is");
        helper.addHomeworkTask("This is a test", "A");
        helper.addHomeworkTask("This is a test", "Test");
        helper.getTasksForHomework("This is a test");
    }

*//*    @Test
    // Add duplicate homework tasks
    // Works - crashes the program. Needs to be sorted out.
    public void testUniqueHomeworkTasks(){
        helper.addHomework("This is a test", "English", "10/12/2017");
        helper.addHomeworkTask("This is a test", "This");
        helper.addHomeworkTask("This is a test", "This");
    }*//*

    // Update homework flags, and test.
    // Works
    @Test
    public void testFlagChange() {
        helper.addHomework("This is a test", "English", "10/12/2017");
        helper.addHomeworkTask("This is a test", "This");
        helper.setTaskStatus("This is a test", "This", "1");
    }


    // Remove homework tasks individually
    @Test
    public void removeHomework() {
        helper.addHomework("This is a test", "English", "10/12/2017");
        helper.addHomeworkTask("This is a test", "This");
        helper.removeHomework("This is a test");
    }*/

/*    // Test ID = 31
    // Did not work straight away - select statement syntax was wrong - couldn't query string variable
    // Fixed - now returning incorrect values.
    // Tasks are being set correctly, but there is a problem in getting these values back. The program is ignoring the values.
    // Changing the 'determine progress' method to include a flag parameter, which streamlines the code - one method is used
    // to query the DB instead of 2.
    // I know adding 'stuff' works.
    // Adding things and querying things works.
    // Needed to cast as a double - that was the issue.
    // Works fine now.

    // YOU HAVE TO UPDATE THE PROGRAM SO THE COUNTERS ARE CLOSED.
    @Test
    public void testHomeworkProgress() {
        helper.addHomework("This is a test", "English", "10/12/2017");
        helper.addHomeworkTask("This is a test", "This");
        helper.addHomeworkTask("This is a test", "Is");
        helper.addHomeworkTask("This is a test", "A");
        helper.addHomeworkTask("This is a test", "Test");
        helper.setTaskStatus("This is a test", "This", "1");
        helper.setTaskStatus("This is a test", "Is", "0");
        helper.setTaskStatus("This is a test", "A", "1");
        helper.setTaskStatus("This is a test", "Test", "0");
        double x = helper.determineProgress("This is a test");
        System.out.println(x);*/

/*Test ID = 32
* Tests to see if an arraylist can be obtained for all homework elements.
 */
    }