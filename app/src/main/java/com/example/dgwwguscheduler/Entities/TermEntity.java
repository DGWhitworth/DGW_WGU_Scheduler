package com.example.dgwwguscheduler.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "term_table")

public class TermEntity {
    @PrimaryKey(autoGenerate = true)
    private int term_id;

    @ColumnInfo(name = "termTitle")
    private String termTitle;
    @ColumnInfo(name = "termStartDate")
    private Date termStartDate;
    @ColumnInfo(name = "termEndDate")
    private Date termEndDate;


    public int getTerm_id() {
        return term_id;
    }

    public void setTerm_id(int term_id) {
        this.term_id = term_id;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public Date getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(Date termStartDate) {
        this.termStartDate = termStartDate;
    }

    public Date getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(Date termEndDate) {
        this.termEndDate = termEndDate;
    }

}
