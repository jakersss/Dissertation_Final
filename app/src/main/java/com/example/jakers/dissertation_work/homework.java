package com.example.jakers.dissertation_work;

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

/**
 * Created by Jakers on 17/11/2017.
 * Currently doesn't include progress, but really should.
 */

// No validation of URL adding - controlled by some browser.

public class homework extends AppCompatActivity {

    private RecyclerView recyclerView;
    private homeworkAdapter adapter;
    private ArrayList<homeworkObj> homeworkList;
    DatabaseHelper dbhelper;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework_layout);
        dbhelper = new DatabaseHelper(this);
        db = dbhelper.getReadableDatabase();
        dbhelper.addHomework("this is a test", "english",
                "this, is, a, test", "16/04/2018");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_homework);
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