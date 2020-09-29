package com.example.dgwwguscheduler.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.dgwwguscheduler.DAO.AssessmentDAO;
import com.example.dgwwguscheduler.DAO.CourseDAO;
import com.example.dgwwguscheduler.DAO.MentorDAO;
import com.example.dgwwguscheduler.DAO.TermDAO;
import com.example.dgwwguscheduler.Entities.AssessmentEntity;
import com.example.dgwwguscheduler.Entities.CourseEntity;
import com.example.dgwwguscheduler.Entities.MentorEntity;
import com.example.dgwwguscheduler.Entities.TermEntity;
import com.example.dgwwguscheduler.UI.Conversions;

@Database(entities = {AssessmentEntity.class, CourseEntity.class, TermEntity.class, MentorEntity.class}, exportSchema = false, version = 1)
@TypeConverters({Conversions.class})

public abstract class ScheduleManagementDatabase extends RoomDatabase {

    private static final String DB_NAME = "DGW_DB.db";
    private static ScheduleManagementDatabase instance;


    public static synchronized ScheduleManagementDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ScheduleManagementDatabase.class, DB_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract AssessmentDAO assessmentDAO();
    public abstract CourseDAO courseDAO();
    public abstract MentorDAO mentorDAO();
    public abstract TermDAO termDAO();

}

