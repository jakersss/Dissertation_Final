package com.example.jakers.dissertation_work;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Jakers on 27/11/2017.
 *
 * Originally, this was a fragment - but it isn't now. It's a list, which can be searched.
 *
 * /

 /**
 * Card adapter
 * List is obtained correctly, but this doesn't interface with the arrayList of subject objects.
 */
public class homeworkAdapter extends RecyclerView.Adapter<homeworkAdapter.ViewHolder> {

    // Gets global variables, to be referenced later on.
    private Context mContext;
    private ArrayList<homeworkObj> homeworkList;
    private ArrayList<String> homeworkTaskList;
    private ArrayList<Double> progress;
    private ArrayList<String> subjectList;
    private OnItemClicked onClick;

    public interface OnItemClicked{
        void onItemClick(int position);
    }

    // Calls self, to initialise.
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Create a way for storing all teacher names and subjects as base values.
        public TextView homeworkName, subjectandprogress;
        private View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            homeworkName = (TextView) view.findViewById(R.id.homeworkName);
            subjectandprogress = (TextView) view.findViewById(R.id.SubjectAndProgress);
        }

        public void setOnClickListener(View.OnClickListener listener){
            view.setOnClickListener(listener);
        }
    }

    public homeworkAdapter(Context mContext) {
        this.mContext = mContext;
        DatabaseHelper dbhelper = new DatabaseHelper(mContext);
        homeworkList = dbhelper.getAllHomework();
    }

    // Had trouble with this part - duplicate homeworks would update. Subject parameter added to
    // data being obtained from the DB; now working fine.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.homework_card, parent, false);

        // gets progress.
        DatabaseHelper dbhelper = new DatabaseHelper(mContext);
        progress = new ArrayList<Double>();
        subjectList = new ArrayList<String>();
        homeworkTaskList = new ArrayList<String>();
        for(homeworkObj hwo : homeworkList){
            homeworkTaskList.add(hwo.getHomework_desc());
            subjectList.add(hwo.getHomework_subject());}
            // subject list is determined, and progress is determined.
            int x = 0;
            for(String s : homeworkTaskList){
                progress.add(dbhelper.determineProgressForIndividualTask(s, subjectList.get(x)));
                x += 1;
            }
        return new ViewHolder(itemView);}

    // Working on getting a title from a click.
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        homeworkObj homeworkobj = homeworkList.get(position);
        final String homeworkTitle = homeworkobj.getHomework_desc();
        final String subjectTitle = homeworkobj.getHomework_subject();
        final String dueDate = homeworkobj.getHomework_duedate();
        // Generate a list of homework progress, then get values.
        holder.homeworkName.setText(homeworkobj.getHomework_desc());
        holder.subjectandprogress.setText(homeworkobj.getHomework_subject()
                + " | Percentage complete: " + progress.get(position) + "%");
        holder.setOnClickListener(new homeworkListListener(mContext, dueDate,
                homeworkTitle, subjectTitle));}

    @Override
    public int getItemCount() {
        return homeworkList.size();
    }}