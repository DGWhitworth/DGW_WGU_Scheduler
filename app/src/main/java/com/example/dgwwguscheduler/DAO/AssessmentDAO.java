package com.example.dgwwguscheduler.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dgwwguscheduler.Entities.AssessmentEntity;
import com.example.dgwwguscheduler.Entities.TermEntity;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Query("SELECT * FROM assessment_table WHERE course_id_fKey = :courseID ORDER BY assessID")
    List<AssessmentEntity> getAssessmentList(int courseID);

    @Query("SELECT * FROM assessment_table WHERE assessID = :assessID")
    AssessmentEntity getSpecifiedAssessment(int assessID);

    @Query("SELECT * FROM assessment_table")
    List<AssessmentEntity> getAllAssessments();

    @Insert
    void insertAssessment(AssessmentEntity assessment);

    @Insert
    void insertSampleAssessmentData(AssessmentEntity... assessment);

    @Delete
    void deleteAssessment(AssessmentEntity assessment);

    @Update
    void updateAssessment(AssessmentEntity assessment);

    @Query("DELETE FROM assessment_table")
    public void wipeData();
}
