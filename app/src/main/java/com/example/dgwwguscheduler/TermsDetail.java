package com.example.dgwwguscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.dgwwguscheduler.Database.ScheduleManagementDatabase;
import com.example.dgwwguscheduler.Entities.CourseEntity;
import com.example.dgwwguscheduler.Entities.TermEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TermsDetail extends AppCompatActivity {
    ListView courseListView;
    ScheduleManagementDatabase db;
    Button deleteTermBtn;
    Button saveTermBtn;
    Button addCourseBtn;
    TermEntity selectedTermData;
    int termID;
    Intent intent;
    SimpleDateFormat convertDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_detail);
        setTitle("Term Details");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_home);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        db = ScheduleManagementDatabase.getInstance(getApplicationContext());                       // Get instance

        intent = getIntent();
        termID = intent.getIntExtra("termID", -1);
        selectedTermData = db.termDAO().getSpecifiedTerm(termID);
        convertDate = new SimpleDateFormat("MM/dd/yy");
        // -------------------------------------------------------------                            // Set Fields
        final EditText editTermTitle = findViewById(R.id.editTermTitle);
        editTermTitle.setText(selectedTermData.getTermTitle());
        final EditText editTermStart = findViewById(R.id.editTermStart);
        Date termSD = db.termDAO().getSpecifiedTerm(termID).getTermStartDate();
        editTermStart.setText(convertDate.format(termSD));
        final EditText editTermEnd = findViewById(R.id.editTermEnd);
        Date termED = db.termDAO().getSpecifiedTerm(termID).getTermEndDate();
        editTermEnd.setText(convertDate.format(termED));

        courseListView = findViewById(R.id.courseListView);                                         // List

        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Click Position: " + position);
                Intent intent = new Intent(getApplicationContext(), CoursesDetail.class);
                int courseID;
                List<CourseEntity> courseList = db.courseDAO().getCourseList(termID);
                for (CourseEntity course: courseList) {
                    System.out.println("Course ID: " + String.valueOf(course.getCourse_id()));
                }
                courseID = courseList.get(position).getCourse_id();
                intent.putExtra("courseID", courseID);
                System.out.println("Selected Course ID = " + String.valueOf(courseID));
                startActivity(intent);

            }
        });
        updateCourseList();





        // -------------------------------------------------                                        // Save button
        saveTermBtn = findViewById(R.id.saveTermBtn);
        saveTermBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTermData.setTermTitle(editTermTitle.getText().toString());
                try {
                    Date tempSDate = convertDate.parse(editTermStart.getText().toString());
                    selectedTermData.setTermStartDate(tempSDate);
                    Date tempEDate = convertDate.parse(editTermEnd.getText().toString());
                    selectedTermData.setTermEndDate(tempEDate);
                } catch (Exception e) {System.out.println("Update Error ( Term Dates )");}

                db.termDAO().updateTerm(selectedTermData);
                finish();
            }
        });

        // ----------------------------------------------                                           // Delete button ( Verify empty Course list )

        deleteTermBtn = findViewById(R.id.deleteTermBtn);
        deleteTermBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!db.courseDAO().getCourseList(termID).isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Can't delete term with courses", Toast.LENGTH_SHORT).show();
                } else {
                    db.termDAO().deleteTerm(selectedTermData);
                    finish();
                }
            }
        });

        // -----------------------------------------------                                          // Add Course button
        addCourseBtn = findViewById(R.id.addCourseBtn);
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                CourseEntity tempCourseData = new CourseEntity();
                tempCourseData.setCourseTitle("New Course");
                tempCourseData.setCourseStartDate(calendar.getTime());
                calendar.add(Calendar.DAY_OF_YEAR, 7);
                tempCourseData.setCourseEndDate(calendar.getTime());
                tempCourseData.setCourseStatus("Status");
                tempCourseData.setCourseNote("New Course Notes");
                tempCourseData.setTerm_id_fKey(termID);

                db.courseDAO().insertCourse(tempCourseData);
                updateCourseList();
                System.out.println("Add Course Button Clicked");
            }
        });
    }

    private void updateCourseList() {
        List<CourseEntity> allCourses = new ArrayList<>();

        try {
            allCourses = db.courseDAO().getCourseList(termID);
            System.out.println("Course Table Rows: " + allCourses.size());
        } catch (Exception e) {System.out.println("Course Error");}

        String[] items = new String[allCourses.size()];
        if (!allCourses.isEmpty()) {
            for (int i = 0; i < allCourses.size(); i++) {
                items[i] = allCourses.get(i).getCourseTitle();
                System.out.println("Course PK = " + i + " term Title is " + items[i]);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        courseListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCourseList();
    }


    @Override                                                                                       // Back button toolbar
    public boolean onSupportNavigateUp() {
//        onBackPressed(); //------- back one screen
        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intent);
        return true;
    }


 }