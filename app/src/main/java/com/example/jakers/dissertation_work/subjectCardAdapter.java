package com.example.jakers.dissertation_work;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Jakers on 27/11/2017.
 * Not fully working yet - doesn't have images or room names.
 *
 * Note: database collection hasn't be initiated
 *
 * Things to do:
 * - Get cards of teacher objects working (DONE)
 * - Get images loaded (DONE)
 * - Create room tables, get rooms working (DONE)
 * - Create homework tables, get homeworks working (DONE)
 * - Get onclicklistner working for redirection.
 * /

/**
 * Card adapter
 * List is obtained correctly, but this doesn't interface with the arrayList of subject objects.
 */
public class subjectCardAdapter extends RecyclerView.Adapter<subjectCardAdapter.ViewHolder> {

    // Gets global variables, to be referenced later on.
    private Context mContext;
    private ArrayList<subjectObj> subjectList;

    // Calls self, to initialise.
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Create a way for storing all teacher names and subjects as base values.
        public TextView title_subject, title_teacherName, title_homework;
        public ImageView subject_photo;

        public ViewHolder(View view) {
            super(view);
            title_subject = (TextView) view.findViewById(R.id.homeworkName);
            title_teacherName = (TextView) view.findViewById(R.id.SubjectAndProgress);
            title_homework = (TextView) view.findViewById(R.id.title_homeworksDue);
            subject_photo = (ImageView) view.findViewById(R.id.subject_photo);

/*            view.setOnClickListener();*/
        }}

    public subjectCardAdapter(Context mContext) {
        this.mContext = mContext;
        DatabaseHelper dbhelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        subjectList = dbhelper.getAllSubjects();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_landing_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        subjectObj subjectobj = subjectList.get(position);
        String subject = subjectobj.getDescription().toLowerCase();
        holder.title_subject.setText(subjectobj.getDescription() + " | " + subjectobj.getRoom());
        holder.title_teacherName.setText(subjectobj.getTeacher());
        holder.title_homework.setText(subjectobj.getNumHomeworks() + " homeworks are due for this subject");
        // Adds number of homeworks due
        switch (subject) {
            case "english":
                subjectobj.setPicture(R.drawable.english_teacher);
                break;
            case "french":
                subjectobj.setPicture(R.drawable.french_teacher);
                break;
            case "geography":
                subjectobj.setPicture(R.drawable.geog_teacher);
                break;
            case "maths":
                subjectobj.setPicture(R.drawable.maths_teacher);
                break;
            case "pe":
                subjectobj.setPicture(R.drawable.pe_teacher);
                break;
            case "science":
                subjectobj.setPicture(R.drawable.science_teacher);
                break;
        }

        Glide.with(mContext).load(subjectobj.getPicture()).into(holder.subject_photo);
    }
        /*
        * Add an on click listener later...
        * */

    @Override
    public int getItemCount() {
        return subjectList.size();
    }
}