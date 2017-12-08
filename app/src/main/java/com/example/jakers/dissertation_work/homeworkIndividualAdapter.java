package com.example.jakers.dissertation_work;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakers on 03/12/2017.
 *
 * Things to do:
 * Add dynamic isertion of new homework records
 * Add 'checkboxes' to individual views
 * Add progress, dynamically update
 * Allow users to edit fields; i.e. remove homework, add homework
 *
 */

public class homeworkIndividualAdapter {
    private Context mContext;
    private String title;
    private String subject;
    private String dueDate;
    private ArrayList<homeworkResourceObj> resourceList;
    private ArrayList<HWListObj> homeworkTasks;
    private List<String> resourceTitle;
    private List<String> homeworkTitle;
    private ListView resourceView, taskView;
    private TextView due_date, completion;
    private Activity activity;
    private View v;

    public homeworkIndividualAdapter(Context mContext, String RetrievedTtitle, String DueDate,
                                     String RetrievedSubject, Activity activity) {
        /* Responsible for adding resources */
        this.title = RetrievedTtitle;
        this.subject = RetrievedSubject;
        this.mContext = mContext;
        this.activity = activity;
        this.dueDate = DueDate;
        DatabaseHelper dbhelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        resourceTitle = new ArrayList<String>();
        homeworkTitle = new ArrayList<String>();
        // This returns resources from the DBHelper
        resourceList = dbhelper.getHomeworkResourcesForHomework(title, subject);
        homeworkTasks = dbhelper.getTasksForHomework(title, subject);
        // Sets the title for the page

        for (homeworkResourceObj hwr : resourceList) {
            String resource = hwr.getResource_description();
            resourceTitle.add(resource);}
        for (HWListObj hlo : homeworkTasks) {
            String task = hlo.getTask_desc();
            homeworkTitle.add(task);}
        // Gets references for list views
        taskView = (ListView) activity.findViewById(R.id.task_list);
        resourceView = (ListView) activity.findViewById(R.id.resource_list);
        // Gets references for text views
        due_date = (TextView) activity.findViewById(R.id.dueDateTextview);
        completion = (TextView) activity.findViewById(R.id.completionTextview);

        // Sets the due date, and percentage complete
        due_date.setText("Due Date:" + "\n" + dueDate);
        completion.setText("Progress:");

        // Sets the adapters for the list
        ArrayAdapter<String> taskAdapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_list_item_1, homeworkTitle);
        taskView.setAdapter(taskAdapter);
        ArrayAdapter<String> resourceAdapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_list_item_1, resourceTitle);
        resourceView.setAdapter(resourceAdapter);}
    }