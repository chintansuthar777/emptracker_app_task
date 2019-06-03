package com.example.emptracker_app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rellayout1, rellayout2;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellayout1.setVisibility(View.VISIBLE);
            rellayout2.setVisibility(View.VISIBLE);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rellayout1 = (RelativeLayout) findViewById(R.id.rellayout1);
        rellayout2 = (RelativeLayout) findViewById(R.id.rellayout2);

        handler.postDelayed(runnable, 3000); // timeout for splash screen
    }

    public void testTask (View view){
        Intent intent = new Intent(this, taskListActivity.class);
        startActivity(intent);
    }
}
