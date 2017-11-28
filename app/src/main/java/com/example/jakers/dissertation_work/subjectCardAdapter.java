package com.example.jakers.dissertation_work;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakers on 27/11/2017.
 * Not fully working yet - doesn't have images or room names.
 *
 * Note: database collection hasn't be initiated
 *
 * Things to do:
 * - Get cards of teacher objects working
 * - Get images loaded
 * - Create room tables, get rooms working
 * - Create homework tables, get homeworks working
 */

public class subjectCardAdapter extends RecyclerView.Adapter<subjectCardAdapter.ViewHolder> {

    private Context context;
    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private ArrayList<subjectObj> subjectObjectList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView teacher, lesson;
        public ImageView teacherImg;

        public ViewHolder(View view) {
            super(view);
            // Obtains references to text views and image views
            teacherImg = (ImageView) view.findViewById(R.id.subject_image);
            teacher = (TextView) view.findViewById(R.id.teacher_title);
            lesson = (TextView) view.findViewById(R.id.subject_title);
        }
    }

    public subjectCardAdapter(Context context, ArrayList<subjectObj> subjectObjectList){
        this.context = context;
        this.subjectObjectList = subjectObjectList;
        // Come back to this.
        /*helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        // Test this
        ArrayList<subjectObj> subjectObj = subjectObjectList;*/
    }

    // Returns the subject view (card view)
    @Override
    public subjectCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View subjectView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_card, parent, false);

        return new ViewHolder(subjectView);
    }

    @Override
    public void onBindViewHolder(subjectCardAdapter.ViewHolder holder, int position) {
        // Gets a list of all of the subject and teacher name
        subjectObj subjectData = subjectObjectList.get(position);
        // sets text, based on arrayList of subject data
        holder.teacher.setText(subjectData.getTeacher());
        holder.lesson.setText(subjectData.getDescription());
        // Glide goes here
       // On click method goes here
    }

    // On item click

    @Override
    public int getItemCount() {
        return subjectObjectList.size();
    }
}