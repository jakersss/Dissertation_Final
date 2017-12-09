package com.example.jakers.dissertation_work;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private List<String> homeworkTask;
    private ListView resourceView, taskView;
    private TextView due_date, completion;
    private Activity activity;
    private View v;

    public homeworkIndividualAdapter(final Context mContext, final String RetrievedTtitle, String DueDate,
                                     final String RetrievedSubject, Activity activity) {
        /* Responsible for adding resources */
        this.title = RetrievedTtitle;
        this.subject = RetrievedSubject;
        this.mContext = mContext;
        this.activity = activity;
        this.dueDate = DueDate;
        final DatabaseHelper dbhelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        resourceTitle = new ArrayList<String>();
        homeworkTitle = new ArrayList<String>();
        homeworkTask = new ArrayList<String>();
        // This returns resources from the DBHelper
        resourceList = dbhelper.getHomeworkResourcesForHomework(title, subject);
        homeworkTasks = dbhelper.getTasksForHomework(title, subject);
        // Sets the title for the page
        for (homeworkResourceObj hwr : resourceList) {
            String resource = hwr.getResource_description();
            resourceTitle.add(resource);}

        for (HWListObj hlo : homeworkTasks) {
            String task = hlo.getTask_desc();
            homeworkTitle.add(task);
            String completed = hlo.getCompleted();
            homeworkTask.add(completed);}

        // Gets references for list views
        taskView = (ListView) activity.findViewById(R.id.task_list);
        // Should add checkboxes
        resourceView = (ListView) activity.findViewById(R.id.resource_list);
        // Gets references for text views
        due_date = (TextView) activity.findViewById(R.id.dueDateTextview);
        completion = (TextView) activity.findViewById(R.id.completionTextview);

        // Sets the due date, and percentage complete
        due_date.setText("Due Date:" + "\n" + dueDate);
        completion.setText("Progress:");

        // Sets the adapters for the list
        final ArrayAdapter<String> taskAdapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_list_item_multiple_choice, homeworkTitle);
        taskView.setAdapter(taskAdapter);
        taskView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        prepareCheck();
        final ArrayAdapter<String> resourceAdapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_list_item_1, resourceTitle);
        resourceView.setAdapter(resourceAdapter);

        // This will set the progress bar and colour
        String progress = "Progress: " + "\n" +
                String.valueOf(dbhelper.determineProgressForIndividualTask(RetrievedTtitle,
                        RetrievedSubject));
        completion.setText(progress);

        // On click listener, for updating database.
        // Sets task true or false in the database, depending on if it's clicked or not.
        // Debugged this to try and get it working. Previously entered fields were overwriting
        // progress being made.
        taskView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Sentinel value of task is null
                String taskId = "";

                if(taskView.isItemChecked(position) == true){
                    taskId = homeworkTitle.get(position);
                    dbhelper.setTaskStatus(RetrievedTtitle, taskId, RetrievedSubject, "1");
                    taskAdapter.notifyDataSetChanged();
                    // This will set the progress bar and colour
                    String progress = "Progress: " + "\n" +
                            String.valueOf(dbhelper.determineProgressForIndividualTask(RetrievedTtitle,
                                    RetrievedSubject));
                    completion.setText(progress);
                } else if(taskView.isItemChecked(position) == false) {
                    taskId = homeworkTitle.get(position);
                    dbhelper.setTaskStatus(RetrievedTtitle, taskId, RetrievedSubject, "0");
                    taskAdapter.notifyDataSetChanged();
                    // This will set the progress bar and colour
                    String progress = "Progress: " + "\n" +
                            String.valueOf(dbhelper.determineProgressForIndividualTask(RetrievedTtitle,
                                    RetrievedSubject));
                    completion.setText(progress);}
            }});}

            // Test 1: print out all homework tasks - works
            public void prepareCheck(){
            int checked = 0;
                for(String s : homeworkTask) {
                    if (s.equals("1")) {
                        taskView.setItemChecked(checked, true);
                        checked += 1;
                    } else {
                        taskView.setItemChecked(checked, false);
                        checked += 1;
                    }}}}