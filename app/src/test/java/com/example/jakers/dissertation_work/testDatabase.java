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
    public void setup(){
        context = RuntimeEnvironment.application;
        // Provides temporary context
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        // Changes are going to take place here because of the sorting order. It's important to note that
        // Comparing users by indexes shouldn't take place in the actual program.
    }

    @After
    public void close(){
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
    @Test
    public void testAllSubjects(){
        ArrayList<subjectObj> hmso = helper.getAllSubjects();
        for(subjectObj s : hmso) {
        System.out.println(s);
    }}

    /*
    * Test ID = 8
    * Tests to see if notes can be added correctly.
    * Test 1: syntax of entry was incorrect
    * Test 2: changed syntax to literal "CURRENT_TIMESTAMP". Now works.
     */
    @Test
    public void testAddNote(){
        helper.addNote("This is a test note", "English");
    }

    /*
    * Test ID = 9
    * Tests to see if notes can be removed correctly
    * Test 1: worked fine.
     */
    @Test
    public void testRemoveNote(){
        helper.addNote("This is a test note", "English");
        helper.removeNote("This is a test note");}

    /*
    * Test ID = 10
    * Tests to see if notes commit correctly
    *
    * Test 1: worked fine
     */
    @Test
    public void testCommitsOfAllnotes(){
        helper.addNote("This is a test note", "English");
        ArrayList<NoteObj> note = helper.getAllNotes();
        for(NoteObj n : note){
            System.out.println(n);}}

    /*
    * Test ID = 11
    * Tests to make sure that notes are unique for specific subjects, and that data of all notes can
    * be gathered on a specific subject.
    * Test 1: "note descriptions are unique"
    * Test 2: not working - values are not unique error
    * Test 3: not working - values are not unique to subject
    * Test 4: Changed parameters on database helper, working fine.
     */
    @Test
    public void testAllCommitsForNotesOfASpecificSubject(){
        helper.addNote("This is a test note", "English");
        helper.addNote("This is a 2nd note", "English");
        helper.addNote("Nothing", "French");
        ArrayList<NoteObj> note = helper.getNotesForSubject("English");
        for(NoteObj n : note){
            System.out.println(n);}}

    @Test
    /*
    * Test ID = 12
    * Tests to see if notes can be obtained, based on a specific note description.
    * Works fine
     */
    public void testCommitsForNotesOfSpecificSubjects(){
        helper.addNote("This is a test note", "English");
        helper.addNote("This is a 2nd test note", "English");
        helper.returnSpecificNote("This is a test note");
    }

}