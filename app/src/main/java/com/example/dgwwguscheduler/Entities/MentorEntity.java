package com.example.dgwwguscheduler.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "mentor_table",
        foreignKeys = @ForeignKey(
                entity = CourseEntity.class,
                parentColumns = "course_id",
                childColumns = "course_id_fKey",
                onDelete = CASCADE),
                indices = @Index(value = "course_id_fKey")

)

public class MentorEntity {
    @PrimaryKey(autoGenerate = true)
    private int mentorID;

    @ColumnInfo(name = "mentorName")
    private String mentorName;
    @ColumnInfo(name = "mentorNumber")
    private String mentorNumber;
    @ColumnInfo(name = "mentorEmail")
    private String mentorEmail;
    @ColumnInfo(name = "course_id_fKey")
    private int course_id_fKey;


    public int getMentorID() {
        return mentorID;
    }

    public void setMentorID(int mentorID) {
        this.mentorID = mentorID;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorNumber() {
        return mentorNumber;
    }

    public void setMentorNumber(String mentorNumber) {
        this.mentorNumber = mentorNumber;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    public int getCourse_id_fKey() {
        return course_id_fKey;
    }

    public void setCourse_id_fKey(int course_id_fKey) {
        this.course_id_fKey = course_id_fKey;
    }
}
