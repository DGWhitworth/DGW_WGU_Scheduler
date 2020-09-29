package com.example.dgwwguscheduler;

import android.content.Intent;
import android.os.Bundle;

import com.example.dgwwguscheduler.Database.ScheduleManagementDatabase;
import com.example.dgwwguscheduler.Entities.MentorEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MentorList extends AppCompatActivity {

    ScheduleManagementDatabase db;
    ListView mentorListView;
    FloatingActionButton fabMentor;
    MentorEntity selectedMentorData;
    int mentorID;
    int courseID;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Mentors");
        setContentView(R.layout.mentor_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_home);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        db = ScheduleManagementDatabase.getInstance(getApplicationContext());                       // Get Instance

        fabMentor = findViewById(R.id.mentorAddFab);                                                // Set Views
        mentorListView = findViewById(R.id.mentorListView);

        intent = getIntent();
        courseID = intent.getIntExtra("courseID", -1);
        selectedMentorData = db.mentorDAO().getSpecificMentor(mentorID);


        // -----------------------------------------------------------------------                  // Clickable ListView
        mentorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int mentorPK, long id) {
                System.out.println("Mentor clicked: " + mentorPK);
                Intent intent = new Intent(getApplicationContext(), MentorDetail.class);
                List<MentorEntity> mentorsList = db.mentorDAO().getMentorList(courseID);
                mentorID = mentorsList.get(mentorPK).getMentorID();
                intent.putExtra("mentor_id", mentorID);
                startActivity(intent);
            }
        });
        updateMentorList();

        // ------------------------------------------------------------                             // Mentor FAB Button
        fabMentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MentorEntity tempMentorData = new MentorEntity();
                tempMentorData.setCourse_id_fKey(courseID);
                tempMentorData.setMentorName("New Mentor Name");
                tempMentorData.setMentorNumber("Phone Number");
                tempMentorData.setMentorEmail("Email Address");

                db.mentorDAO().insertMentor(tempMentorData);
                updateMentorList();
            }
        });

    }

    private void updateMentorList() {
        List<MentorEntity> allMentors = db.mentorDAO().getMentorList(courseID);
        System.out.println("Mentor Table Rows: " + allMentors.size());

        String[] items = new String[allMentors.size()];
        if (!allMentors.isEmpty()) {
            for (int i = 0; i < allMentors.size(); i++) {
                items[i] = allMentors.get(i).getMentorName();
                System.out.println("Mentor PK = " + i + " = " + items[i]);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        mentorListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onSupportNavigateUp() {
        //        onBackPressed(); //------- back one screen
        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intent);
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();
        updateMentorList();
    }

}