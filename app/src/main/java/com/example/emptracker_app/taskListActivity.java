package com.example.emptracker_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class taskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
    }

    public void taskOne(View view){
        //Intent startNewActivity = new Intent(this, taskOneActivity.class);
        //startActivity(startNewActivity);
    }
}
