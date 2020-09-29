package com.example.dgwwguscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.dgwwguscheduler.Database.ScheduleManagementDatabase;

public class HomeScreen extends AppCompatActivity {
    public static final int ACTIVITY_CODE = -1;
    public static String LOG_INFO = "Homepage";
    ScheduleManagementDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        setTitle("WGU Tracker");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        Button termBtn = findViewById(R.id.TermBtn);
        termBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, TermsList.class);
                startActivity(intent);
            }
        });

        Button dataBtn = findViewById(R.id.DataBtn);
        dataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, DataManagement.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onResume(){
        super.onResume();
//        updateViews();
    }
}