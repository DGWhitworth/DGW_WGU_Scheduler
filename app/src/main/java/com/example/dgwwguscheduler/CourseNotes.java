package com.example.dgwwguscheduler;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.example.dgwwguscheduler.Database.ScheduleManagementDatabase;
import com.example.dgwwguscheduler.Entities.CourseEntity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CourseNotes extends AppCompatActivity {
    ScheduleManagementDatabase db;
    CourseEntity selectedNote;
    EditText editNotes;
    EditText editNoteNumber;
    Button smsNoteBtn;
    Button saveNoteBtn;
    int courseID;
    Intent intent;
    String noteNumber = "number";

    final int sms_permission_code = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Course Notes");
        setContentView(R.layout.course_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_home);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        db = ScheduleManagementDatabase.getInstance(getApplicationContext());                       // Get instance

        // -----------------------------------------------------------------                        // Set Views
        editNotes = findViewById(R.id.editCourseNotes);
        editNoteNumber = findViewById(R.id.editNoteNumber);
        saveNoteBtn = findViewById(R.id.saveNoteBtn);
        smsNoteBtn = findViewById(R.id.sendNoteBtn);

        smsNoteBtn.setEnabled(true);                                                               //  sms -----------
        if(checkPermission(Manifest.permission.SEND_SMS)) {
            smsNoteBtn.setEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.SEND_SMS}, sms_permission_code);
        }

        // -----------------------------------------------------------------
        intent = getIntent();
        courseID = intent.getIntExtra("course_id", -1);
        selectedNote = db.courseDAO().getSpecifiedCourse(courseID);


        editNotes.setText(selectedNote.getCourseNote());                                            // Set Fields
        editNoteNumber.setText(selectedNote.getSmsNumber());



        // ------------------------------------------------------------------                       // Save Button
        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedNote.setCourseNote(editNotes.getText().toString());
                db.courseDAO().updateCourse(selectedNote);
                finish();
            }
        });




        // -----------------------------------------------------------------                        // Send Note Button
        smsNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sendMessage(editNoteNumber.getText().toString(), editNotes.getText().toString());
                sendSMS(view);
            }
        });

    }


    public void sendSMS(View view) {
        String noteNumber = editNoteNumber.getText().toString();
        String noteMessage = editNotes.getText().toString();

        if (noteNumber == null || noteNumber.length() == 0 ||
            noteMessage == null || noteMessage.length() == 0) {
            return;
        }

        if (checkPermission(Manifest.permission.SEND_SMS)) {
            SmsManager thisSmsManager = SmsManager.getDefault();
            thisSmsManager.sendTextMessage(noteNumber, null, noteMessage, null, null);
            Toast.makeText(this, "Message sent to: " + noteNumber, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Message failure", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        //        onBackPressed(); //------- back one screen
        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intent);
        return true;
    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

}