package com.example.jakers.dissertation_work;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakers on 17/11/2017.
 */

// Cards are obtained as followed:
    // Titles Teachers are obtained from subject table
    // Homework pages
    // Images are obtained depending on the teacher name (nested if adds image to card)

    // Status:
        // Recycler view and subject layout are not being obtained
        // Do this next.
        // Current values are not being obtained

public class subject extends AppCompatActivity {

        private RecyclerView recyclerView;
        private subjectCardAdapter adapter;
        private ArrayList<subjectObj> subjectList;
        private Context context;
        private DatabaseHelper dbhelper;
        private SQLiteDatabase db;

        protected void onCreate(Bundle savedInstanceState) {
            dbhelper = new DatabaseHelper(this);
            db = dbhelper.getReadableDatabase();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.subject_layout);
            // Don't know if this will work
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            // Creates a new arrayList, containing data which is to be queried.
            subjectList = dbhelper.getAllSubjects();
            adapter = new subjectCardAdapter(this, subjectList);
            // Sets a grid layout manager on the recyclerView
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
            // Sets up the layout manager for the recycler view
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

            prepareSubjects();

            // Glide with subjects for images
        }

        private void prepareSubjects(){

        }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration{
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge){
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }}

    // Conversion for the copy paste features - converts DP to PX, because you can't use PX in Java, only XML.
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
}}