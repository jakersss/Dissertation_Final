package com.example.jakers.dissertation_work;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import org.w3c.dom.Text;

import java.io.ObjectInputValidation;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jakers on 18/11/2017.
 * @version 1.1 - temporarily took out validation to get the database working.
 * @Note 'back button' leads back to login page. This needs to be changed.
 *
 * For version 2 - data doesn't
 */

public class login extends AppCompatActivity {

    // Local instance of the database
    DatabaseHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;
    private inputValidation validate;
    final Activity activity = this;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hide action bar
        ActionBar ab = getSupportActionBar();
        ab.hide();

        // get references for edit text columns
        final TextInputEditText txtUsername = (TextInputEditText) findViewById(R.id.usernameEntry_input);
        final TextInputEditText txtPassword = (TextInputEditText) findViewById(R.id.passwordEntry_input);
        final TextInputLayout txtUsernameLayout = (TextInputLayout) findViewById(R.id.usernameEntry_layout);
        final TextInputLayout txtPasswordLayout = (TextInputLayout) findViewById(R.id.passwordEntry_layout);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        // Initiates database communication
        dbhelper = new DatabaseHelper(this);
        db = dbhelper.getReadableDatabase();

        // Program isn't referencing tables correctly...
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // verifyFromSQLite();
                // Hides soft keyboard from view
                InputMethodManager manager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                // Get references to input data fields.
                String EnteredUsername = txtUsername.getText().toString();
                String EnteredPassword = txtPassword.getText().toString();
                // Validates the entry
                Boolean validated = dbhelper.validateLogin(EnteredUsername, EnteredPassword);
                // Controls validation to go to the new activity. Directs to view if working.
                if(validated == true){
                    Intent proceed = new Intent(activity, MainActivity.class);
                    startActivity(proceed);
                    user = EnteredUsername;
                } else {
                    Snackbar.make(view, R.string.login_failed, Snackbar.LENGTH_LONG).show();}}
});}

// Feeble attempt at getting around DOM. DOM to be implemented in V2.
public String getUsername(){
    return user;
}

public String getName() {
    int i = 0;
    String name = " ";
    HashMap<String, String> names = dbhelper.getUsernamesAndNames();
    for (Map.Entry<String, String> entry : names.entrySet()) {
        if (entry.getKey().equals(user)) {
            name = entry.getValue();}}
    return name;
}}