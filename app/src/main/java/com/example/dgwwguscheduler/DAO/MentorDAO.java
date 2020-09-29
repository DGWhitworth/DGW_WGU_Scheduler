package com.example.dgwwguscheduler.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dgwwguscheduler.Entities.MentorEntity;

import java.util.List;

@Dao
public interface MentorDAO {
    @Query("SELECT * FROM mentor_table WHERE course_id_fKey = :courseID ORDER BY mentorID")
    List<MentorEntity> getMentorList(int courseID);

    @Query("SELECT * FROM mentor_table WHERE mentorID = :courseMentorID")
    MentorEntity getSpecificMentor(int courseMentorID);
//    @Query("SELECT * FROM mentor_table WHERE course_id_fKey = :courseID AND mentorID = :courseMentorID")
//    MentorEntity getSpecificMentor(int courseID, int courseMentorID);

    @Insert
    void insertMentor(MentorEntity mentor);

    @Insert
    void insertSampleMentorData(MentorEntity... mentor);

    @Delete
    void deleteMentor(MentorEntity mentor);

    @Update
    void updateMentor(MentorEntity mentor);

    @Query("DELETE FROM mentor_table")
    public void wipeData();
}
