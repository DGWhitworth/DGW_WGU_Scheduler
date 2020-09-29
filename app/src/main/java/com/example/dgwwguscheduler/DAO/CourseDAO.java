package com.example.dgwwguscheduler.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dgwwguscheduler.Entities.CourseEntity;

import java.util.List;

@Dao
public interface CourseDAO {
    @Query("SELECT * FROM course_table WHERE term_id_fKey = :termID ORDER BY course_id")
    List<CourseEntity> getCourseList(int termID);

//    @Query("SELECT * FROM course_table WHERE term_id_fKey = :termID AND course_id = :courseID")
//    CourseEntity getSpecifiedCourse(int termID, int courseID);
    @Query("SELECT * FROM course_table WHERE course_id = :courseID")
    CourseEntity getSpecifiedCourse(int courseID);

    @Query("SELECT * FROM course_table")
    List<CourseEntity> getAllCourses();

    @Insert
    void insertCourse(CourseEntity course);

    @Insert
    void insertSampleCourseData(CourseEntity... course);

    @Delete
    void deleteCourse(CourseEntity course);

    @Update
    void updateCourse(CourseEntity course);

    @Query("DELETE FROM course_table")
    public void wipeData();
}
