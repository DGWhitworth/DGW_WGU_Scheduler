package com.example.dgwwguscheduler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.dgwwguscheduler.Database.SampleData;
import com.example.dgwwguscheduler.Database.ScheduleManagementDatabase;
import com.example.dgwwguscheduler.Entities.AssessmentEntity;
import com.example.dgwwguscheduler.Entities.CourseEntity;
import com.example.dgwwguscheduler.Entities.TermEntity;

import java.util.List;

public class DataManagement extends AppCompatActivity {
    public static String LOG_INFO = "DataManage";
    Button sampleDBButton;
    Button wipeDBButton;
    TextView termCountTV;
    TextView courseCountTV;
    TextView assessmentCountTV;
    ScheduleManagementDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_management);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        setTitle("Data Management");

        db = ScheduleManagementDatabase.getInstance(getApplicationContext());                       // get Database instance

        termCountTV = findViewById(R.id.termCount);                                                 // set views
        courseCountTV = findViewById(R.id.courseCount);
        assessmentCountTV = findViewById(R.id.assessmentCount);

        updateDataManageView();





        // ---------------Program Sample Data Button                                                // Program Sample Data Button
        ConstraintLayout btnLayout = findViewById(R.id.dataManagementConstraintLayout);
        ConstraintSet set = new ConstraintSet();

        sampleDBButton = new Button(getApplicationContext());
        sampleDBButton.setText("Add Sample Data");
        sampleDBButton.setId(R.id.sampleDBbutton);                                                  // in id.xml

        set.constrainHeight(sampleDBButton.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(sampleDBButton.getId(), ConstraintSet.WRAP_CONTENT);
        set.connect(sampleDBButton.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 10);
        set.connect(sampleDBButton.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 10);

        btnLayout.addView(sampleDBButton);
        setContentView(btnLayout);
        set.applyTo(btnLayout);

        sampleDBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_INFO, "Sample button pressed");
                SampleData sampleData = new SampleData();
                sampleData.fillData(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Sample information has been added to Database.", Toast.LENGTH_LONG).show();
                updateDataManageView();

            }
        });

        // -------------------------------------------------------                                  // Program Wipe Data Button
        wipeDBButton = new Button(getApplicationContext());
        wipeDBButton.setText("Wipe Data");
        wipeDBButton.setId(R.id.wipeData);

        set.constrainHeight(wipeDBButton.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(wipeDBButton.getId(), ConstraintSet.WRAP_CONTENT);
        set.connect(wipeDBButton.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 10);
        set.connect(wipeDBButton.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 10);

        btnLayout.addView(wipeDBButton);
        setContentView(btnLayout);
        set.applyTo(btnLayout);

        wipeDBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_INFO, "Wipe button pressed");
                db.clearAllTables();
                Toast.makeText(getApplicationContext(), "Entire Database has been wiped out.", Toast.LENGTH_LONG).show();
                updateDataManageView();

            }
        });

//        // -----------------------------------------------------------------------------------------// Text View programmed----id.xml
//        ConstraintLayout tView = findViewById(R.id.dataManagementConstraintLayout);
//        ConstraintSet set2 = new ConstraintSet();
//
//        termCountTV = new TextView(getApplicationContext());
//        termCountTV.setText("Term count");
//        termCountTV.setId(R.id.termCount);
//
//        set.constrainHeight(termCountTV.getId(), ConstraintSet.WRAP_CONTENT);
//        set.constrainWidth(termCountTV.getId(), ConstraintSet.WRAP_CONTENT);
//        set.connect(termCountTV.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 10);
//        set.connect(termCountTV.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 10);
//
//
//        tView.addView(termCountTV);
//        setContentView(tView);
//        set.applyTo(tView);

    }

    private void updateDataManageView() {

        int termCount = 0;
        int courseCount = 0;
        int assessCount = 0;

        try {
            List<TermEntity>  termList = db.termDAO().getAllTerms();
            List<CourseEntity> courseList = db.courseDAO().getAllCourses();
            List<AssessmentEntity> assessList = db.assessmentDAO().getAllAssessments();

            try {
                for (int i = 0; i < termList.size(); i++){
                    if (termList.get(i).getTermTitle().contains(""))termCount++;

                }
                for (int i = 0; i < courseList.size(); i++){
                    if (courseList.get(i).getCourseTitle().contains(""))courseCount++;
                }
                for (int i = 0; i < assessList.size(); i++){
                    if (assessList.get(i).getAssessTitle().contains(""))assessCount++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        termCountTV.setText(String.valueOf(termCount));
        courseCountTV.setText(String.valueOf(courseCount));
        assessmentCountTV.setText(String.valueOf(assessCount));
    }
}