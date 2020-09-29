package com.example.dgwwguscheduler;

import android.content.Intent;
import android.os.Bundle;

import com.example.dgwwguscheduler.Database.ScheduleManagementDatabase;
import com.example.dgwwguscheduler.Entities.MentorEntity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MentorDetail extends AppCompatActivity {
    ScheduleManagementDatabase db;
    Button saveMentorBtn;
    Button deleteMentorBtn;
    MentorEntity selectedMentorData;
    EditText editMentorName;
    EditText editMentorNumber;
    EditText editMentorEmail;
    int mentorID;
    int courseID;
    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Mentor Details");
        setContentView(R.layout.mentor_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_home);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        db = ScheduleManagementDatabase.getInstance(getApplicationContext());                       // Get instance

        // ----------------------------------------------------------------                         // Set Views
        editMentorName = findViewById(R.id.editMentorName);
        editMentorNumber = findViewById(R.id.editMentorNumber);
        editMentorEmail = findViewById(R.id.editMentorEmail);
        saveMentorBtn = findViewById(R.id.saveMentorBtn);
        deleteMentorBtn = findViewById(R.id.deleteMentorBtn);

        intent = getIntent();
        mentorID = intent.getIntExtra("mentor_id", -1);
        selectedMentorData = db.mentorDAO().getSpecificMentor(mentorID);

        // ----------------------------------------------------------------                         // Set Fields
        editMentorName.setText(selectedMentorData.getMentorName());
        editMentorNumber.setText(selectedMentorData.getMentorNumber());
        editMentorEmail.setText(selectedMentorData.getMentorEmail());

        // ----------------------------------------------------------------                         // Save Button
        saveMentorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedMentorData.setMentorName(editMentorName.getText().toString());
                selectedMentorData.setMentorNumber(editMentorNumber.getText().toString());
                selectedMentorData.setMentorEmail(editMentorEmail.getText().toString());
                db.mentorDAO().updateMentor(selectedMentorData);
                finish();
            }
        });

        // ----------------------------------------------------------------                         // Delete Button
        deleteMentorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.mentorDAO().deleteMentor(selectedMentorData);
                finish();
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        //        onBackPressed(); //------- back one screen
        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intent);
        return true;
    }
}