package com.example.jakers.dissertation_work;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jakers on 17/11/2017.
 * Currently doesn't include progress, but really should.
 */

// No validation of URL adding - controlled by some browser.

public class homework extends AppCompatActivity {

    private RecyclerView recyclerView;
    private homeworkAdapter adapter;
    DatabaseHelper dbhelper;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework_layout);
        Intent passedFromHomework = getIntent();
        Bundle homeworkAdded = passedFromHomework.getExtras();
        FloatingActionButton addHomework = (FloatingActionButton) findViewById(R.id.btn_add_homework);
        addHomework.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent redirectToHomework = new Intent(getApplicationContext(), homeworkEntry.class);
                startActivity(redirectToHomework);
            }
        });
        dbhelper = new DatabaseHelper(this);
        db = dbhelper.getReadableDatabase();
        // Not adding correctly.
        dbhelper.addHomework("this is a test", "maths",
                "16/04/2018");
        dbhelper.addHomework("this is a test", "english",
                "16/04/2018");
        dbhelper.addHomework("this isn't a test", "science",
                "16/04/2018");
        dbhelper.addHomeworkResource("this is a test", "Google Drive",
                "maths", "http://www.google.com", null);
        dbhelper.addHomeworkResource("this is a test", "MyMaths",
                "maths", "http://www.mymaths.com", null);
        dbhelper.addHomeworkTask("this is a test", "maths", "wow");
        dbhelper.addHomeworkTask("this is a test", "maths", "great");
        dbhelper.addHomeworkTask("this is a test", "maths", "fantastic");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_homework);

        // Processes the data obtained from the intent on callback
        if(homeworkAdded != null){
            final String hwAddition = (String) homeworkAdded.get("homeworkName");
            final String subject = (String) homeworkAdded.get("homeworkSubject");
            String display = "homework " + hwAddition + " added.";
            Snackbar sb = Snackbar.make(recyclerView, display, Snackbar.LENGTH_LONG).
                    // Remove homework addition
                            setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dbhelper.removeHomework(hwAddition, subject);
                            String removed = "homework " + hwAddition + " removed";
                            Snackbar remove = Snackbar.make(recyclerView, removed, Snackbar.LENGTH_SHORT);
                            remove.show();
                            // Refreshes the screen.
                            Intent startAgain = new Intent(getApplicationContext(), homework.class);
                            startActivity(startAgain);
                        }});sb.show();}

        // Shows snackbar in case homework has been added
        ArrayList<homeworkObj> homeworkgettest = dbhelper.getAllHomework();
        adapter = new homeworkAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);}

        public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration{
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge){
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }}

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }}