package com.example.dgwwguscheduler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import com.example.dgwwguscheduler.Database.ScheduleManagementDatabase;
import com.example.dgwwguscheduler.Entities.AssessmentEntity;
import com.example.dgwwguscheduler.Entities.CourseEntity;
import com.example.dgwwguscheduler.UI.AlertReminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CoursesDetail extends AppCompatActivity {
    public static final String LOG_INFO = "CourseDetail";

    ScheduleManagementDatabase db;
    ListView assessmentListView;
    EditText eCourseName;
    Button deleteCourseBtn;
    Button saveCourseBtn;
    Button addAssessmentBtn;
    Button courseMentorBtn;
    Button courseNoteBtn;
    Button setAlertSD;
    Button setAlertED;
    CourseEntity selectedCourseData;
    int termID;
    int courseID;
    int NOTIFY_ID_SD = 600;
    int NOTIFY_ID_ED = 700;
    Intent intent;
    SimpleDateFormat convertDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Course Details");
        setContentView(R.layout.courses_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_home);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        db = ScheduleManagementDatabase.getInstance(getApplicationContext());                       // Get instance

        intent = getIntent();
        termID = intent.getIntExtra("termID", -1);
        courseID = intent.getIntExtra("courseID", -1);
//        selectedCourseData = db.courseDAO().getSpecifiedCourse(termID, courseID);
        selectedCourseData = db.courseDAO().getSpecifiedCourse(courseID);
        convertDate = new SimpleDateFormat("MM/dd/yy");

        final EditText editCourseName = findViewById(R.id.editCourseName);                          // Set Views and Fields
        editCourseName.setText(selectedCourseData.getCourseTitle());
        final EditText editCourseStart = findViewById(R.id.editCourseStart);
        Date courseSD = db.courseDAO().getSpecifiedCourse(courseID).getCourseStartDate();
        editCourseStart.setText(convertDate.format(courseSD));
        final EditText editCourseEnd = findViewById(R.id.editCourseEnd);
        Date courseED = db.courseDAO().getSpecifiedCourse(courseID).getCourseEndDate();
        editCourseEnd.setText(convertDate.format(courseED));
        final EditText editStatus = findViewById(R.id.editCourseStatus);
        editStatus.setText(selectedCourseData.getCourseStatus());

        setAlertSD = findViewById(R.id.courseAlertBtnSD);                                           // Set Buttons
        setAlertED = findViewById(R.id.courseAlertBtnED);

        assessmentListView = findViewById(R.id.assessmentListView);                                 // List

        assessmentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Click Position: " + position);
                Intent intent = new Intent(getApplicationContext(), AssessmentsDetail.class);
                int assessmentID;
                List<AssessmentEntity> assessmentList = db.assessmentDAO().getAssessmentList(courseID);
                for (AssessmentEntity assessment: assessmentList) {
                    System.out.println("Assessment ID: " + String.valueOf(assessment.getAssessID()));
                }
                assessmentID = assessmentList.get(position).getAssessID();
                intent.putExtra("assessmentID", assessmentID);
                System.out.println("Selected Assessment ID = " + String.valueOf(assessmentID));
                startActivity(intent);

            }
        });
        updateAssessmentList();

        // -------------------------------------------------                                        // Course Mentor Button
        courseMentorBtn = findViewById(R.id.mentorDetailBtn);
        courseMentorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MentorList.class);
                intent.putExtra("termID", termID);
                intent.putExtra("courseID", courseID);
                startActivity(intent);
            }
        });

        // -------------------------------------------------                                        // Course Note Button
        courseNoteBtn = findViewById(R.id.notesBtn);
        courseNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CourseNotes.class);
                intent.putExtra("course_id", courseID);
                startActivity(intent);
            }
        });


        // -------------------------------------------------                                        // Save button
        saveCourseBtn = findViewById(R.id.saveCourseBtn);
        saveCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCourseData.setCourseTitle(editCourseName.getText().toString());
                selectedCourseData.setCourseStatus(editStatus.getText().toString());
                try {
                    Date tempSDate = convertDate.parse(editCourseStart.getText().toString());
                    selectedCourseData.setCourseStartDate(tempSDate);
                    Date tempEDate = convertDate.parse(editCourseEnd.getText().toString());
                    selectedCourseData.setCourseEndDate(tempEDate);
                } catch (Exception e) {System.out.println("Update Error ( Course Dates )");}

                db.courseDAO().updateCourse(selectedCourseData);
                finish();
            }
        });

        // ----------------------------------------------                                           // Delete button
        deleteCourseBtn = findViewById(R.id.deleteCourseBtn);
        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.courseDAO().deleteCourse(selectedCourseData);
                finish();
            }
        });

        // -----------------------------------------------                                          // Add Assessment button
        addAssessmentBtn = findViewById(R.id.addAssessmentBtn);
        addAssessmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                AssessmentEntity tempAssessData = new AssessmentEntity();
                tempAssessData.setAssessTitle("New Assessment");
                tempAssessData.setAssessDueDate(calendar.getTime());
                calendar.add(Calendar.DAY_OF_YEAR, 3);
                tempAssessData.setAssessAlertDate(calendar.getTime());
                tempAssessData.setAssessType("Type");
                tempAssessData.setCourse_id_fKey(courseID);

                db.assessmentDAO().insertAssessment(tempAssessData);
                updateAssessmentList();
                System.out.println("Add Assessment Clicked");
            }
        });

        // ---------------------------------------------------------------                          // Set Alert Start Date Button
        setAlertSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_INFO, "Start Date Alert (click)");

                Calendar calDateSD = Calendar.getInstance();
                calDateSD.add(Calendar.DAY_OF_YEAR, -1);
                Date alertDateSD = calDateSD.getTime();
                try {
                    alertDateSD = convertDate.parse(editCourseStart.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.d(LOG_INFO, "Set Alert Date Problem");
                }
                setAlert1(alertDateSD);
//                saveAlertInfo();
                finish();

            }
        });
        // ---------------------------------------------------------------                          // Set Alert End Date Button
        setAlertED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_INFO, "End Date Alert (click)");

                Calendar calDateED = Calendar.getInstance();
                calDateED.add(Calendar.DAY_OF_YEAR, -1);
                Date alertDateED = calDateED.getTime();
                try {
                    alertDateED = convertDate.parse(editCourseEnd.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.d(LOG_INFO, "Set Alert Date Problem");
                }
                setAlert2(alertDateED);
//                saveAlertInfo();
                finish();

            }
        });



    }

    private void setAlert1(Date calDateProvided) {
        Calendar calDateNow = Calendar.getInstance();
        eCourseName = findViewById(R.id.editCourseName);
        if (calDateProvided.getTime() > calDateNow.getTime().getTime()) {
            Log.d(LOG_INFO, convertDate.format(calDateProvided) + " Notify Date");
            Intent sendIntent = new Intent(getApplicationContext(), AlertReminder.class);
            sendIntent.putExtra("my_title", "Important Date");
            sendIntent.putExtra("my_message", "Course:  " + eCourseName.getText().toString() + " is Starting\n"
                    + " Date: " + convertDate.format(calDateProvided));
            sendIntent.putExtra("notify_id", NOTIFY_ID_SD);
            PendingIntent thisPendIntent = PendingIntent.getBroadcast(getApplicationContext(),
                    NOTIFY_ID_SD, sendIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alertManage = (AlarmManager) getSystemService(ALARM_SERVICE);
            alertManage.set(AlarmManager.RTC, calDateProvided.getTime(), thisPendIntent);
            Toast.makeText(getApplicationContext(), "Start Date Alert set for: " + eCourseName
                    .getText().toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), convertDate.format(calDateProvided)
                    + " is a PAST DATE", Toast.LENGTH_LONG).show();
        }
    }
    private void setAlert2(Date calDateProvided) {
        Calendar calDateNow = Calendar.getInstance();
        eCourseName = findViewById(R.id.editCourseName);
        if (calDateProvided.getTime() > calDateNow.getTime().getTime()) {
            Log.d(LOG_INFO, convertDate.format(calDateProvided) + " Notify Date");
            Intent sendIntent = new Intent(getApplicationContext(), AlertReminder.class);
            sendIntent.putExtra("my_title", "Important Date");
            sendIntent.putExtra("my_message", "Course:  " + eCourseName.getText().toString() + " is Ending\n"
                    + " Date: " + convertDate.format(calDateProvided));
            sendIntent.putExtra("notify_id", NOTIFY_ID_ED);
            PendingIntent thisPendIntent = PendingIntent.getBroadcast(getApplicationContext(),
                    NOTIFY_ID_ED, sendIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alertManage = (AlarmManager) getSystemService(ALARM_SERVICE);
            alertManage.set(AlarmManager.RTC, calDateProvided.getTime(), thisPendIntent);
            Toast.makeText(getApplicationContext(), " End Date Alert set for: " + eCourseName
                    .getText().toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), convertDate.format(calDateProvided)
                    + " is a PAST DATE ", Toast.LENGTH_LONG).show();
        }
    }

    private void updateAssessmentList() {
        List<AssessmentEntity> allAssessments = new ArrayList<>();

        try {
            allAssessments = db.assessmentDAO().getAssessmentList(courseID);
            System.out.println("Assessment Table Rows: " + allAssessments.size());
        } catch (Exception e) {System.out.println("Assessment Error");}

        String[] items = new String[allAssessments.size()];
        if (!allAssessments.isEmpty()) {
            for (int i = 0; i < allAssessments.size(); i++) {
                items[i] = allAssessments.get(i).getAssessTitle();
                System.out.println("Assessment PK = " + i + " and Title is " + items[i]);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        assessmentListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAssessmentList();
    }



    @Override
    public boolean onSupportNavigateUp() {
        //        onBackPressed(); //------- back one screen
        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intent);
        return true;
    }
}