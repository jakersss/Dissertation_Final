package com.example.jakers.dissertation_work;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;

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
        // Get teacher name nad subject appearing (DONE)
        // Add pointer (DONE)
        // Use glide to add image (DONE)
        // Get room number (DONE)
        // Get homeworks (DONE)
        // Add click to redirect

public class subject extends AppCompatActivity {

        private RecyclerView recyclerView;
        private subjectCardAdapter adapter;
        private ArrayList<subjectObj> subjectList;
        DatabaseHelper dbhelper;
        SQLiteDatabase db;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.subject_landing);
            dbhelper = new DatabaseHelper(this);
            db = dbhelper.getReadableDatabase();
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            //subjectList = dbhelper.getAllSubjects();
            adapter = new subjectCardAdapter(this);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
            //prepareSubjects();
        }

    // private void prepareSubjects(){}

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