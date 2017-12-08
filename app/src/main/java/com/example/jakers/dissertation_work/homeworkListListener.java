package com.example.jakers.dissertation_work;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Jakers on 03/12/2017.
 */

public class homeworkListListener implements View.OnClickListener{

    String title, subject, dueDate;
    Context context;

    public homeworkListListener(Context c, String dueDate, String title, String subject){
        this.title = title;
        this.dueDate = dueDate;
        this.subject = subject;
        this.context = c;}

    // Gets and stores the title. You can now redirect to pages.
    // TEST to see if this worked
    // Didn't work as didn't initially define in the android manifest.
    // TEST 2 (after adding): intent didn't work.
    @Override
    public void onClick(View v){
        //Starts the intent. homeworkViewIndividual will obtain the data,
        // and change how it processes the page.
        Intent intent = new Intent(context,
                homeworkViewIndividual.class);
        intent.putExtra("title", title);
        intent.putExtra("subject", subject);
        intent.putExtra("date", dueDate);
        context.startActivity(intent);
    }}