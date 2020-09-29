package com.example.dgwwguscheduler.Entities;

import android.hardware.camera2.CameraConstrainedHighSpeedCaptureSession;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "course_table",
        foreignKeys = @ForeignKey(
                entity = TermEntity.class,
                parentColumns = "term_id",
                childColumns = "term_id_fKey",
                onDelete = CASCADE),
                indices = @Index(value = "term_id_fKey")

)

public class CourseEntity {
    @PrimaryKey(autoGenerate = true)
    private int course_id;
    @ColumnInfo(name = "term_id_fKey")
    private int term_id_fKey;
    @ColumnInfo(name = "courseTitle")
    private String courseTitle;
    @ColumnInfo(name = "courseStatus")
    private String courseStatus;
    @ColumnInfo(name = "courseStartDate")
    private Date courseStartDate;
    @ColumnInfo(name = "courseEndDate")
    private Date courseEndDate;
    @ColumnInfo(name = "courseNote")
    private String courseNote;
    @ColumnInfo(name = "smsNumber")
    private String smsNumber;
    @ColumnInfo(name = "courseAlert")
    private Date courseAlert;

    public String getSmsNumber() {
        return smsNumber;
    }

    public void setSmsNumber(String smsNumber) {
        this.smsNumber = smsNumber;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Date getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(Date courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public Date getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(Date courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public int getTerm_id_fKey() {
        return term_id_fKey;
    }

    public void setTerm_id_fKey(int term_id_fKey) {
        this.term_id_fKey = term_id_fKey;
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }

    public Date getCourseAlert() {
        return courseAlert;
    }

    public void setCourseAlert(Date courseAlert) {
        this.courseAlert = courseAlert;
    }
}
