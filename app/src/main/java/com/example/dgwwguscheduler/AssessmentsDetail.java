package com.example.dgwwguscheduler;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.dgwwguscheduler.Database.ScheduleManagementDatabase;
import com.example.dgwwguscheduler.Entities.AssessmentEntity;
import com.example.dgwwguscheduler.UI.AlertReminder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AssessmentsDetail extends AppCompatActivity {
    public static final String LOG_INFO = "AssessmentDetail";
    ScheduleManagementDatabase db;
    Button saveAssessmentBtn;
    Button deleteAssessmentBtn;
    Button setGoalAlert;
    EditText editAssessName;
    EditText editAssessType;
    EditText editAssessDue;
    EditText editAssessGoal;
    EditText editAlertTitle;
    Date assessDueDate;
    Date assessGoalDate;
    AssessmentEntity selectedAssessmentData;
    int assessID;
    int NOTIFY_ID = 500;
    Intent intent;
    SimpleDateFormat convertDate;
    final Calendar goalDate = Calendar.getInstance();

    int notificationId = new Random().nextInt();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Assessment Details");
        setContentView(R.layout.assessments_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_home);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        notifyChannel();                                                                            // Call notification channel

        convertDate = new SimpleDateFormat("MM/dd/yy hh:mm a");                             // Date format

        db = ScheduleManagementDatabase.getInstance(getApplicationContext());                       // Get instance




        intent = getIntent();
        assessID = intent.getIntExtra("assessmentID", -1);
        selectedAssessmentData = db.assessmentDAO().getSpecifiedAssessment(assessID);


        // -----------------------------------------------------------                              // Set View to field
        editAssessName = findViewById(R.id.editAssessName);
        editAssessType = findViewById(R.id.editAssessType);
        editAssessDue = findViewById(R.id.editAssessDueDate);
        editAssessGoal = findViewById(R.id.editAssessGoalDate);
        saveAssessmentBtn = findViewById(R.id.saveAssessBtn);
        deleteAssessmentBtn = findViewById(R.id.deleteAssessBtn);
        editAlertTitle = findViewById(R.id.editAlertTitle);
            editAlertTitle.setVisibility(View.INVISIBLE);                                           // Invisible EditText
        setGoalAlert = findViewById(R.id.AssessGoalBtn);


        assessDueDate = db.assessmentDAO().getSpecifiedAssessment(assessID).getAssessDueDate();
        assessGoalDate = db.assessmentDAO().getSpecifiedAssessment(assessID).getAssessAlertDate();


        // -----------------------------------------------------------                              // Set Fields
        editAssessName.setText(selectedAssessmentData.getAssessTitle());
        editAssessType.setText(selectedAssessmentData.getAssessType());
        editAssessDue.setText(convertDate.format(assessDueDate));
        editAssessGoal.setText(convertDate.format(assessGoalDate));

        // -------------------------------------------------------------                            // Save Button
        saveAssessmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAssessmentData.setAssessTitle(editAssessName.getText().toString());
                selectedAssessmentData.setAssessType(editAssessType.getText().toString());
                try {
                    Date tempDDate = convertDate.parse(editAssessDue.getText().toString());
                    selectedAssessmentData.setAssessDueDate(tempDDate);
                    Date tempGDate = convertDate.parse(editAssessGoal.getText().toString());
                    selectedAssessmentData.setAssessAlertDate(tempGDate);
                } catch (Exception e) {System.out.println("Assessment Date Error");}

                db.assessmentDAO().updateAssessment(selectedAssessmentData);
                finish();
            }
        });

        // -------------------------------------------------------------                            // Delete Button
        deleteAssessmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.assessmentDAO().deleteAssessment(selectedAssessmentData);
                finish();
            }
        });

        // ---------------------------------------------------------------                          // Set Alert Button
        setGoalAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_INFO, "Set Alert (click)");

                Calendar calDate = Calendar.getInstance();
                calDate.add(Calendar.DAY_OF_YEAR, -1);
                Date alertDate = calDate.getTime();
                try {
                    alertDate = convertDate.parse(editAssessGoal.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.d(LOG_INFO, "Set Alert Date Problem");
                }
                setAlert(alertDate);
                saveAlertInfo();
                finish();

            }
        });
    }
    private void setAlert(Date calDateProvided) {
        Calendar calDateNow = Calendar.getInstance();
        if (calDateProvided.getTime() > calDateNow.getTime().getTime()) {
            Log.d(LOG_INFO, convertDate.format(calDateProvided) + " Notify Date");
            Intent sendIntent = new Intent(getApplicationContext(), AlertReminder.class);
            sendIntent.putExtra("my_title", "Important Date");
            sendIntent.putExtra("my_message", "Assessment:  " +  editAssessName.getText().toString() + " is Due\n"
                        + " Date and Time: " + convertDate.format(calDateProvided));
            sendIntent.putExtra("notify_id", NOTIFY_ID);
            PendingIntent thisPendIntent = PendingIntent.getBroadcast(getApplicationContext(),
                    NOTIFY_ID, sendIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alertManage = (AlarmManager) getSystemService(ALARM_SERVICE);
            alertManage.set(AlarmManager.RTC, calDateProvided.getTime(), thisPendIntent);
            Toast.makeText(getApplicationContext(), "Alert set for: " + editAssessName
                    .getText().toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), convertDate.format(calDateProvided)
                    + " is a PAST DATE or TIME", Toast.LENGTH_LONG).show();
        }
    }


    public void saveAlertInfo() {
        selectedAssessmentData.setAssessAlertTitle(editAlertTitle.getText().toString());

        try {
            selectedAssessmentData.setAssessAlertDate(convertDate.parse(editAssessGoal.getText().toString()));
            db.assessmentDAO().updateAssessment(selectedAssessmentData);
            Log.d(LOG_INFO, "Adjusted Goal Date");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        db.assessmentDAO().updateAssessment(selectedAssessmentData);
    }

    private void notifyChannel() {                                                                  // Create Notification Channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            String CHANNEL_ID = "DGW Tracker ID";
//            String CHANNEL_NAME = "DGW Tracker NAME";
//            String CHANNEL_DESC = "DGW Tracker DESC";

            CharSequence dgw_channel_name = "DGW Tracker NAME";
            String dgw_channel_description = "DGW Tracker DESC";
            int dgw_channel_importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notifyChannel = new NotificationChannel("dgwChanId", dgw_channel_name,
                    dgw_channel_importance);
            notifyChannel.setDescription(dgw_channel_description);

            NotificationManager notifyManager = getSystemService(NotificationManager.class);
            notifyManager.createNotificationChannel(notifyChannel);
            Log.d(LOG_INFO, "Notification Channel Set");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        //        onBackPressed(); //------- back one screen
        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intent);
        return true;
    }


}