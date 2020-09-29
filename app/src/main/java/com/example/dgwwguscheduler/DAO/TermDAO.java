package com.example.dgwwguscheduler.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dgwwguscheduler.Entities.TermEntity;

import java.util.List;

@Dao
public interface TermDAO {
    @Query("SELECT * FROM term_table ORDER BY term_id")
    List<TermEntity> getTermList();

    @Query("SELECT * FROM term_table WHERE term_id = :term_ID ORDER BY term_id")
    TermEntity getSpecifiedTerm(int term_ID);

    @Query("SELECT * FROM term_table")
    List<TermEntity> getAllTerms();

    @Insert
    void insertTerm(TermEntity term);

    @Insert
    void insertSampleTermData(TermEntity... term);

    @Delete
    void deleteTerm(TermEntity term);

    @Update
    void updateTerm(TermEntity term);

    @Query("DELETE FROM term_table")
    public void wipeData();
}
