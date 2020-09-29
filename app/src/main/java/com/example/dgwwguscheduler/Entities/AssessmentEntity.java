package com.example.dgwwguscheduler.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "assessment_table",
        foreignKeys = @ForeignKey(
                entity = CourseEntity.class,
                parentColumns = "course_id",
                childColumns = "course_id_fKey",
                onDelete = CASCADE),
                indices = @Index(value = "course_id_fKey")

)

public class AssessmentEntity {
    @PrimaryKey(autoGenerate = true)
    private int assessID;

    @ColumnInfo(name = "assessTitle")
    private String assessTitle;
    @ColumnInfo(name = "assessType")
    private String assessType;
    @ColumnInfo(name = "assessDate")
    private Date assessDueDate;
    @ColumnInfo(name = "assessPassFail")
    private String assessPassFail;
    @ColumnInfo(name = "course_id_fKey")
    private int course_id_fKey;
    @ColumnInfo(name = "assessAlertTitle")
    private String assessAlertTitle;
    @ColumnInfo(name = "assessAlertDate")
    private Date assessAlertDate;
    @ColumnInfo(name = "assessDetail")
    private String assessDetail;


    public int getAssessID() {
        return assessID;
    }

    public void setAssessID(int assessID) {
        this.assessID = assessID;
    }

    public String getAssessTitle() {
        return assessTitle;
    }

    public void setAssessTitle(String assessTitle) {
        this.assessTitle = assessTitle;
    }

    public Date getAssessDueDate() {
        return assessDueDate;
    }

    public void setAssessDueDate(Date assessDueDate) {
        this.assessDueDate = assessDueDate;
    }

    public String getAssessType() {
        return assessType;
    }

    public void setAssessType(String assessType) {
        this.assessType = assessType;
    }

    public int getCourse_id_fKey() {
        return course_id_fKey;
    }

    public void setCourse_id_fKey(int course_id_fKey) {
        this.course_id_fKey = course_id_fKey;
    }

    public String getAssessPassFail() {
        return assessPassFail;
    }

    public void setAssessPassFail(String assessPassFail) {
        this.assessPassFail = assessPassFail;
    }

    public String getAssessAlertTitle() {
        return assessAlertTitle;
    }

    public void setAssessAlertTitle(String assessAlertTitle) {
        this.assessAlertTitle = assessAlertTitle;
    }

    public Date getAssessAlertDate() {
        return assessAlertDate;
    }

    public void setAssessAlertDate(Date assessAlertDate) {
        this.assessAlertDate = assessAlertDate;
    }

    public String getAssessDetail() {
        return assessDetail;
    }

    public void setAssessDetail(String assessDetail) {
        this.assessDetail = assessDetail;
    }
}
