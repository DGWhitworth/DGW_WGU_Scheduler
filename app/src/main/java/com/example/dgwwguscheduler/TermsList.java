package com.example.dgwwguscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.dgwwguscheduler.Database.ScheduleManagementDatabase;
import com.example.dgwwguscheduler.Entities.TermEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

public class TermsList extends AppCompatActivity {

    ScheduleManagementDatabase db;
    ListView termListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Terms");
        setContentView(R.layout.terms_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_home);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        db = ScheduleManagementDatabase.getInstance(getApplicationContext());                       // Get Instance

        // ------------------------------------------------------------------                       // View and List
        termListView = findViewById(R.id.termListView);

        termListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Click Position: " + position);
                Intent intent = new Intent(getApplicationContext(), TermsDetail.class);
                int termID;
                List<TermEntity> termsList = db.termDAO().getTermList();
                for (TermEntity term: termsList) {
                    System.out.println("Term ID: " + String.valueOf(term.getTerm_id()));
                }
                termID = termsList.get(position).getTerm_id();
                intent.putExtra("termID", termID);
                System.out.println("Selected Term ID = " + String.valueOf(termID));
                startActivity(intent);

            }
        });
        updateTermList();

        FloatingActionButton fabTerm = findViewById(R.id.fabTermAdd);                                   // Floating Action Button
        fabTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                TermEntity tempTermData = new TermEntity();
                tempTermData.setTermTitle("New Term");
                tempTermData.setTermStartDate(calendar.getTime());
                calendar.add(Calendar.DAY_OF_YEAR, 7);
                tempTermData.setTermEndDate(calendar.getTime());

                db.termDAO().insertTerm(tempTermData);
                updateTermList();
                System.out.println("Add Term FAB Clicked");

            }
        });

    }

    private void updateTermList() {
        List<TermEntity> allTerms = db.termDAO().getTermList();
        System.out.println("Term Table Rows: " + allTerms.size());

        String[] items = new String[allTerms.size()];
        if (!allTerms.isEmpty()) {
            for (int i = 0; i < allTerms.size(); i++) {
                items[i] = allTerms.get(i).getTermTitle();
                System.out.println("Term Primary Key = " + i + " and term title " + items[i]);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        termListView.setAdapter(adapter);

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
        updateTermList();
    }

}