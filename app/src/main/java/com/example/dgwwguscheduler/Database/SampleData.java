package com.example.dgwwguscheduler.Database;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dgwwguscheduler.Entities.AssessmentEntity;
import com.example.dgwwguscheduler.Entities.CourseEntity;
import com.example.dgwwguscheduler.Entities.MentorEntity;
import com.example.dgwwguscheduler.Entities.TermEntity;

import java.util.Calendar;
import java.util.List;

public class SampleData extends AppCompatActivity {
    public static String LogText = "SampleData";

    TermEntity sampleTerm1 = new TermEntity();
    TermEntity sampleTerm2 = new TermEntity();
    TermEntity sampleTerm3 = new TermEntity();
    // ---------------------------------------------------------------------------------------------// Term 1
    CourseEntity sampleCourse1 = new CourseEntity();
    CourseEntity sampleCourse2= new CourseEntity();
    CourseEntity sampleCourse3 = new CourseEntity();
    AssessmentEntity sampleAssessment1 = new AssessmentEntity();
    AssessmentEntity sampleAssessment2 = new AssessmentEntity();
    AssessmentEntity sampleAssessment3 = new AssessmentEntity();
    MentorEntity sampleMentor1 = new MentorEntity();
    MentorEntity sampleMentor2 = new MentorEntity();
    MentorEntity sampleMentor3 = new MentorEntity();

    // ---------------------------------------------------------------------------------------------// Term 2
    CourseEntity sampleCourse4 = new CourseEntity();
    CourseEntity sampleCourse5= new CourseEntity();
    CourseEntity sampleCourse6 = new CourseEntity();
    AssessmentEntity sampleAssessment4 = new AssessmentEntity();
    AssessmentEntity sampleAssessment5 = new AssessmentEntity();
    AssessmentEntity sampleAssessment6 = new AssessmentEntity();
    MentorEntity sampleMentor4 = new MentorEntity();
    MentorEntity sampleMentor5 = new MentorEntity();
    MentorEntity sampleMentor6 = new MentorEntity();

    // ---------------------------------------------------------------------------------------------// Term 3
    CourseEntity sampleCourse7 = new CourseEntity();
    CourseEntity sampleCourse8= new CourseEntity();
    CourseEntity sampleCourse9 = new CourseEntity();
    AssessmentEntity sampleAssessment7 = new AssessmentEntity();
    AssessmentEntity sampleAssessment8 = new AssessmentEntity();
    AssessmentEntity sampleAssessment9 = new AssessmentEntity();
    MentorEntity sampleMentor7 = new MentorEntity();
    MentorEntity sampleMentor8 = new MentorEntity();
    MentorEntity sampleMentor9 = new MentorEntity();

    ScheduleManagementDatabase db;

    public void fillData(Context context) {
        db = ScheduleManagementDatabase.getInstance(context);
        try {
            insertTermData();
            insertCourseData();
            insertAssessmentData();
            insertMentorData();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(LogText, "Sample Database Fail");
        }
    }

    private void insertTermData() {
        Calendar termStartDate;
        Calendar termEndDate;

        termStartDate = Calendar.getInstance();
        termEndDate = Calendar.getInstance();
        termStartDate.add(Calendar.MONTH, 1);
        termEndDate.add(Calendar.MONTH, 4);
        sampleTerm1.setTermTitle("Term 1");
        sampleTerm1.setTermStartDate(termStartDate.getTime());
        sampleTerm1.setTermEndDate(termEndDate.getTime());

        termStartDate = Calendar.getInstance();
        termEndDate = Calendar.getInstance();
        termStartDate.add(Calendar.MONTH, 5);
        termEndDate.add(Calendar.MONTH, 8);
        sampleTerm2.setTermTitle("Term 2");
        sampleTerm2.setTermStartDate(termStartDate.getTime());
        sampleTerm2.setTermEndDate(termEndDate.getTime());

        termStartDate = Calendar.getInstance();
        termEndDate = Calendar.getInstance();
        termStartDate.add(Calendar.MONTH, 9);
        termEndDate.add(Calendar.MONTH, 12);
        sampleTerm3.setTermTitle("Term 3");
        sampleTerm3.setTermStartDate(termStartDate.getTime());
        sampleTerm3.setTermEndDate(termEndDate.getTime());

        db.termDAO().insertSampleTermData(sampleTerm1, sampleTerm2, sampleTerm3);
    }

    private void insertCourseData() {
        Calendar courseStartDate;
        Calendar courseEndDate;
        List<TermEntity> termList = db.termDAO().getTermList();
        if (termList == null) return;

        // -----------------------------------------------------------------------------------------// Term 1 - Courses
        courseStartDate = Calendar.getInstance();
        courseEndDate = Calendar.getInstance();
        courseStartDate.add(Calendar.MONTH, 1);
        courseEndDate.add(Calendar.MONTH, 4);
        sampleCourse1.setCourseTitle("Term 1 - Course 1");
        sampleCourse1.setCourseStartDate(courseStartDate.getTime());
        sampleCourse1.setCourseEndDate(courseEndDate.getTime());
        sampleCourse1.setCourseStatus("Failed");                                                    // Pass, Fail, In Progress, Complete
        sampleCourse1.setCourseNote("Term 1 - Course 1 Notes");
        sampleCourse1.setSmsNumber("5551236789");
        sampleCourse1.setTerm_id_fKey(termList.get(0).getTerm_id());                                // Term (0, 1, 2)

        courseStartDate = Calendar.getInstance();
        courseEndDate = Calendar.getInstance();
        courseStartDate.add(Calendar.MONTH, 5);
        courseEndDate.add(Calendar.MONTH, 8);
        sampleCourse2.setCourseTitle("Term 1 - Course 2");
        sampleCourse2.setCourseStartDate(courseStartDate.getTime());
        sampleCourse2.setCourseEndDate(courseEndDate.getTime());
        sampleCourse2.setCourseStatus("Completed");
        sampleCourse2.setCourseNote("Term 1 - Course 2 Notes");
        sampleCourse2.setSmsNumber("5551236789");
        sampleCourse2.setTerm_id_fKey(termList.get(0).getTerm_id());

        courseStartDate = Calendar.getInstance();
        courseEndDate = Calendar.getInstance();
        courseStartDate.add(Calendar.MONTH, 9);
        courseEndDate.add(Calendar.MONTH, 12);
        sampleCourse3.setCourseTitle("Term 1 - Course 3");
        sampleCourse3.setCourseStartDate(courseStartDate.getTime());
        sampleCourse3.setCourseEndDate(courseEndDate.getTime());
        sampleCourse3.setCourseStatus("In Progress");
        sampleCourse3.setCourseNote("Term 1 - Course 3 Notes");
        sampleCourse3.setSmsNumber("5551236789");
        sampleCourse3.setTerm_id_fKey(termList.get(0).getTerm_id());

        // -----------------------------------------------------------------------------------------// Term 2 - Courses
        courseStartDate = Calendar.getInstance();
        courseEndDate = Calendar.getInstance();
        courseStartDate.add(Calendar.MONTH, 1);
        courseEndDate.add(Calendar.MONTH, 4);
        sampleCourse4.setCourseTitle("Term 2 - Course 1");
        sampleCourse4.setCourseStartDate(courseStartDate.getTime());
        sampleCourse4.setCourseEndDate(courseEndDate.getTime());
        sampleCourse4.setCourseStatus("Failed");                                                    // Pass, Fail, In Progress, Complete
        sampleCourse4.setCourseNote("Term 2 - Course 1 Notes");
        sampleCourse4.setSmsNumber("5551236789");
        sampleCourse4.setTerm_id_fKey(termList.get(1).getTerm_id());                                // Term (0, 1, 2)

        courseStartDate = Calendar.getInstance();
        courseEndDate = Calendar.getInstance();
        courseStartDate.add(Calendar.MONTH, 5);
        courseEndDate.add(Calendar.MONTH, 8);
        sampleCourse5.setCourseTitle("Term 2 - Course 2");
        sampleCourse5.setCourseStartDate(courseStartDate.getTime());
        sampleCourse5.setCourseEndDate(courseEndDate.getTime());
        sampleCourse5.setCourseStatus("Completed");
        sampleCourse5.setCourseNote("Term 2 - Course 2 Notes");
        sampleCourse5.setSmsNumber("5551236789");
        sampleCourse5.setTerm_id_fKey(termList.get(1).getTerm_id());

        courseStartDate = Calendar.getInstance();
        courseEndDate = Calendar.getInstance();
        courseStartDate.add(Calendar.MONTH, 9);
        courseEndDate.add(Calendar.MONTH, 12);
        sampleCourse6.setCourseTitle("Term 2 - Course 3");
        sampleCourse6.setCourseStartDate(courseStartDate.getTime());
        sampleCourse6.setCourseEndDate(courseEndDate.getTime());
        sampleCourse6.setCourseStatus("In Progress");
        sampleCourse6.setCourseNote("Term 2 - Course 3 Notes");
        sampleCourse6.setSmsNumber("5551236789");
        sampleCourse6.setTerm_id_fKey(termList.get(1).getTerm_id());

        // -----------------------------------------------------------------------------------------// Term 3 - Courses
        courseStartDate = Calendar.getInstance();
        courseEndDate = Calendar.getInstance();
        courseStartDate.add(Calendar.MONTH, 1);
        courseEndDate.add(Calendar.MONTH, 4);
        sampleCourse7.setCourseTitle("Term 3 - Course 1");
        sampleCourse7.setCourseStartDate(courseStartDate.getTime());
        sampleCourse7.setCourseEndDate(courseEndDate.getTime());
        sampleCourse7.setCourseStatus("Failed");                                                    // Pass, Fail, In Progress, Complete
        sampleCourse7.setCourseNote("Term 3 - Course 1 Notes");
        sampleCourse7.setSmsNumber("5551236789");
        sampleCourse7.setTerm_id_fKey(termList.get(2).getTerm_id());                                // Term (0, 1, 2)

        courseStartDate = Calendar.getInstance();
        courseEndDate = Calendar.getInstance();
        courseStartDate.add(Calendar.MONTH, 5);
        courseEndDate.add(Calendar.MONTH, 8);
        sampleCourse8.setCourseTitle("Term 3 - Course 2");
        sampleCourse8.setCourseStartDate(courseStartDate.getTime());
        sampleCourse8.setCourseEndDate(courseEndDate.getTime());
        sampleCourse8.setCourseStatus("Completed");
        sampleCourse8.setCourseNote("Term 3 - Course 2 Notes");
        sampleCourse8.setSmsNumber("5551236789");
        sampleCourse8.setTerm_id_fKey(termList.get(2).getTerm_id());

        courseStartDate = Calendar.getInstance();
        courseEndDate = Calendar.getInstance();
        courseStartDate.add(Calendar.MONTH, 9);
        courseEndDate.add(Calendar.MONTH, 12);
        sampleCourse9.setCourseTitle("Term 3 - Course 3");
        sampleCourse9.setCourseStartDate(courseStartDate.getTime());
        sampleCourse9.setCourseEndDate(courseEndDate.getTime());
        sampleCourse9.setCourseStatus("In Progress");
        sampleCourse9.setCourseNote("Term 3 - Course 3 Notes");
        sampleCourse9.setSmsNumber("5551236789");
        sampleCourse9.setTerm_id_fKey(termList.get(2).getTerm_id());

        db.courseDAO().insertSampleCourseData(sampleCourse1, sampleCourse2, sampleCourse3);
        db.courseDAO().insertSampleCourseData(sampleCourse4, sampleCourse5, sampleCourse6);
        db.courseDAO().insertSampleCourseData(sampleCourse7, sampleCourse8, sampleCourse9);
    }

    private void insertAssessmentData() {
        List<TermEntity> listTerms;
        List<CourseEntity> listCourses;
        listTerms = db.termDAO().getTermList();
        Calendar tempCal;
        Calendar tempDay = Calendar.getInstance();
        tempDay.add(Calendar.DAY_OF_YEAR, -5);

        //------------------------------------------------------------------------------------------// Term 1 - Assessments
        // Term 1 - Course 1
        listCourses = db.courseDAO().getCourseList(listTerms.get(0).getTerm_id());                  // Term (0, 1, 2)
        sampleAssessment1.setCourse_id_fKey(listCourses.get(0).getCourse_id());                     // Course (0, 1, 2)
        sampleAssessment1.setAssessTitle("Assessment 1 - T1.C1 - P");
        sampleAssessment1.setAssessType("Performance");                                             // Performance or Objective
        sampleAssessment1.setAssessDetail("Assessment Details");
        tempCal = Calendar.getInstance();
        tempCal.add(Calendar.MONTH, -4);
        tempCal.add(Calendar.WEEK_OF_MONTH, 1);
        sampleAssessment1.setAssessDueDate(tempCal.getTime());
        sampleAssessment1.setAssessPassFail("Pass");                                                // Pending, Pass, or Fail
        sampleAssessment1.setAssessAlertTitle(sampleAssessment1.getAssessTitle() + " is Due");
        sampleAssessment1.setAssessAlertDate(tempDay.getTime());

        // Term 1 - Course 2
        listCourses = db.courseDAO().getCourseList(listTerms.get(0).getTerm_id());
        sampleAssessment2.setCourse_id_fKey(listCourses.get(1).getCourse_id());
        sampleAssessment2.setAssessTitle("Assessment 1 - T1.C2 - O");
        sampleAssessment2.setAssessType("Objective");
        sampleAssessment2.setAssessDetail("Assessment Details");
        tempCal = Calendar.getInstance();
        tempCal.add(Calendar.MONTH, -4);
        tempCal.add(Calendar.WEEK_OF_MONTH, 1);
        sampleAssessment2.setAssessDueDate(tempCal.getTime());
        sampleAssessment2.setAssessPassFail("Fail");
        sampleAssessment2.setAssessAlertTitle(sampleAssessment2.getAssessTitle() + " is Due");
        sampleAssessment2.setAssessAlertDate(tempDay.getTime());

        // Term - Course 3
        listCourses = db.courseDAO().getCourseList(listTerms.get(0).getTerm_id());
        sampleAssessment3.setCourse_id_fKey(listCourses.get(2).getCourse_id());
        sampleAssessment3.setAssessTitle("Assessment 1 - T1.C3 - P");
        sampleAssessment3.setAssessType("Performance");
        sampleAssessment3.setAssessDetail("Assessment Details");
        tempCal = Calendar.getInstance();
        tempCal.add(Calendar.MONTH, -4);
        tempCal.add(Calendar.WEEK_OF_MONTH, 1);
        sampleAssessment3.setAssessDueDate(tempCal.getTime());
        sampleAssessment3.setAssessPassFail("Pending");
        sampleAssessment3.setAssessAlertTitle(sampleAssessment3.getAssessTitle() + " is Due");
        sampleAssessment3.setAssessAlertDate(tempDay.getTime());

        db.assessmentDAO().insertAssessment(sampleAssessment1);
        db.assessmentDAO().insertAssessment(sampleAssessment2);
        db.assessmentDAO().insertAssessment(sampleAssessment3);

        //------------------------------------------------------------------------------------------// Term 2 - Assessments
        // Term 2 - Course 1
        listCourses = db.courseDAO().getCourseList(listTerms.get(1).getTerm_id());
        sampleAssessment4.setCourse_id_fKey(listCourses.get(0).getCourse_id());
        sampleAssessment4.setAssessTitle("Assessment 1 - T2.C1 - P");
        sampleAssessment4.setAssessType("Performance");
        sampleAssessment4.setAssessDetail("Assessment Details");
        tempCal = Calendar.getInstance();
        tempCal.add(Calendar.MONTH, -4);
        tempCal.add(Calendar.WEEK_OF_MONTH, 1);
        sampleAssessment4.setAssessDueDate(tempCal.getTime());
        sampleAssessment4.setAssessPassFail("Pass");
        sampleAssessment4.setAssessAlertTitle(sampleAssessment4.getAssessTitle() + " is Due");
        sampleAssessment4.setAssessAlertDate(tempDay.getTime());

        // Term 2 - Course 2
        listCourses = db.courseDAO().getCourseList(listTerms.get(1).getTerm_id());
        sampleAssessment5.setCourse_id_fKey(listCourses.get(1).getCourse_id());
        sampleAssessment5.setAssessTitle("Assessment 1 - T2.C2 - O");
        sampleAssessment5.setAssessType("Objective");
        sampleAssessment5.setAssessDetail("Assessment Details");
        tempCal = Calendar.getInstance();
        tempCal.add(Calendar.MONTH, -4);
        tempCal.add(Calendar.WEEK_OF_MONTH, 1);
        sampleAssessment5.setAssessDueDate(tempCal.getTime());
        sampleAssessment5.setAssessPassFail("Fail");
        sampleAssessment5.setAssessAlertTitle(sampleAssessment5.getAssessTitle() + " is Due");
        sampleAssessment5.setAssessAlertDate(tempDay.getTime());

        // Term 2 - Course 3
        listCourses = db.courseDAO().getCourseList(listTerms.get(1).getTerm_id());
        sampleAssessment6.setCourse_id_fKey(listCourses.get(2).getCourse_id());
        sampleAssessment6.setAssessTitle("Assessment 1 - T2.C3 - P");
        sampleAssessment6.setAssessType("Performance");
        sampleAssessment6.setAssessDetail("Assessment Details");
        tempCal = Calendar.getInstance();
        tempCal.add(Calendar.MONTH, -4);
        tempCal.add(Calendar.WEEK_OF_MONTH, 1);
        sampleAssessment6.setAssessDueDate(tempCal.getTime());
        sampleAssessment6.setAssessPassFail("Pending");
        sampleAssessment6.setAssessAlertTitle(sampleAssessment6.getAssessTitle() + " is Due");
        sampleAssessment6.setAssessAlertDate(tempDay.getTime());

        db.assessmentDAO().insertAssessment(sampleAssessment4);
        db.assessmentDAO().insertAssessment(sampleAssessment5);
        db.assessmentDAO().insertAssessment(sampleAssessment6);

        //------------------------------------------------------------------------------------------// Term 3 - Assessments
        // Term 3 - Course 1
        listCourses = db.courseDAO().getCourseList(listTerms.get(2).getTerm_id());
        sampleAssessment7.setCourse_id_fKey(listCourses.get(0).getCourse_id());
        sampleAssessment7.setAssessTitle("Assessment 1 - T3.C1 - P");
        sampleAssessment7.setAssessType("Performance");
        sampleAssessment7.setAssessDetail("Assessment Details");
        tempCal = Calendar.getInstance();
        tempCal.add(Calendar.MONTH, -4);
        tempCal.add(Calendar.WEEK_OF_MONTH, 1);
        sampleAssessment7.setAssessDueDate(tempCal.getTime());
        sampleAssessment7.setAssessPassFail("Pass");
        sampleAssessment7.setAssessAlertTitle(sampleAssessment7.getAssessTitle() + " is Due");
        sampleAssessment7.setAssessAlertDate(tempDay.getTime());

        // Term 3 - Course 2
        listCourses = db.courseDAO().getCourseList(listTerms.get(2).getTerm_id());
        sampleAssessment8.setCourse_id_fKey(listCourses.get(1).getCourse_id());
        sampleAssessment8.setAssessTitle("Assessment 1 - T3.C2 - O");
        sampleAssessment8.setAssessType("Objective");
        sampleAssessment8.setAssessDetail("Assessment Details");
        tempCal = Calendar.getInstance();
        tempCal.add(Calendar.MONTH, -4);
        tempCal.add(Calendar.WEEK_OF_MONTH, 1);
        sampleAssessment8.setAssessDueDate(tempCal.getTime());
        sampleAssessment8.setAssessPassFail("Fail");
        sampleAssessment8.setAssessAlertTitle(sampleAssessment8.getAssessTitle() + " is Due");
        sampleAssessment8.setAssessAlertDate(tempDay.getTime());

        // Term 3 - Course 3
        listCourses = db.courseDAO().getCourseList(listTerms.get(2).getTerm_id());
        sampleAssessment9.setCourse_id_fKey(listCourses.get(2).getCourse_id());
        sampleAssessment9.setAssessTitle("Assessment 1 - T3.C3 - P");
        sampleAssessment9.setAssessType("Performance");
        sampleAssessment9.setAssessDetail("Assessment Details");
        tempCal = Calendar.getInstance();
        tempCal.add(Calendar.MONTH, -4);
        tempCal.add(Calendar.WEEK_OF_MONTH, 1);
        sampleAssessment9.setAssessDueDate(tempCal.getTime());
        sampleAssessment9.setAssessPassFail("Pending");
        sampleAssessment9.setAssessAlertTitle(sampleAssessment9.getAssessTitle() + " is Due");
        sampleAssessment9.setAssessAlertDate(tempDay.getTime());

        db.assessmentDAO().insertAssessment(sampleAssessment7);
        db.assessmentDAO().insertAssessment(sampleAssessment8);
        db.assessmentDAO().insertAssessment(sampleAssessment9);

    }

    private void insertMentorData() {
        List<TermEntity> listTerms;
        List<CourseEntity> listCourses;
        listTerms = db.termDAO().getTermList();

        // -----------------------------------------------------------------------------------------// Term 1 - Mentors
        listCourses = db.courseDAO().getCourseList(listTerms.get(0).getTerm_id());
        sampleMentor1.setCourse_id_fKey(listCourses.get(0).getCourse_id());
        sampleMentor1.setMentorName("Jeff");
        sampleMentor1.setMentorNumber("123-555-2345");
        sampleMentor1.setMentorEmail("Jeff@School.edu");
        db.mentorDAO().insertMentor(sampleMentor1);

        listCourses = db.courseDAO().getCourseList(listTerms.get(0).getTerm_id());
        sampleMentor2.setCourse_id_fKey(listCourses.get(1).getCourse_id());
        sampleMentor2.setMentorName("Mary");
        sampleMentor2.setMentorNumber("123-555-4567");
        sampleMentor2.setMentorEmail("Mary@School.edu");
        db.mentorDAO().insertMentor(sampleMentor2);

        listCourses = db.courseDAO().getCourseList(listTerms.get(0).getTerm_id());
        sampleMentor3.setCourse_id_fKey(listCourses.get(2).getCourse_id());
        sampleMentor3.setMentorName("Joe");
        sampleMentor3.setMentorNumber("123-555-6789");
        sampleMentor3.setMentorEmail("Joe@School.edu");
        db.mentorDAO().insertMentor(sampleMentor3);

        // -----------------------------------------------------------------------------------------// Term 2 - Mentors
        listCourses = db.courseDAO().getCourseList(listTerms.get(1).getTerm_id());
        sampleMentor4.setCourse_id_fKey(listCourses.get(0).getCourse_id());
        sampleMentor4.setMentorName("Jane");
        sampleMentor4.setMentorNumber("123-555-2345");
        sampleMentor4.setMentorEmail("Jane@School.edu");
        db.mentorDAO().insertMentor(sampleMentor4);

        listCourses = db.courseDAO().getCourseList(listTerms.get(1).getTerm_id());
        sampleMentor5.setCourse_id_fKey(listCourses.get(1).getCourse_id());
        sampleMentor5.setMentorName("Beth");
        sampleMentor5.setMentorNumber("123-555-4567");
        sampleMentor5.setMentorEmail("Beth@School.edu");
        db.mentorDAO().insertMentor(sampleMentor5);

        listCourses = db.courseDAO().getCourseList(listTerms.get(1).getTerm_id());
        sampleMentor6.setCourse_id_fKey(listCourses.get(2).getCourse_id());
        sampleMentor6.setMentorName("Todd");
        sampleMentor6.setMentorNumber("123-555-6789");
        sampleMentor6.setMentorEmail("Todd@School.edu");
        db.mentorDAO().insertMentor(sampleMentor6);

        // -----------------------------------------------------------------------------------------// Term 3 - Mentors
        listCourses = db.courseDAO().getCourseList(listTerms.get(2).getTerm_id());
        sampleMentor7.setCourse_id_fKey(listCourses.get(0).getCourse_id());
        sampleMentor7.setMentorName("Kelly");
        sampleMentor7.setMentorNumber("123-555-2345");
        sampleMentor7.setMentorEmail("Kelly@School.edu");
        db.mentorDAO().insertMentor(sampleMentor7);

        listCourses = db.courseDAO().getCourseList(listTerms.get(2).getTerm_id());
        sampleMentor8.setCourse_id_fKey(listCourses.get(1).getCourse_id());
        sampleMentor8.setMentorName("Tim");
        sampleMentor8.setMentorNumber("123-555-4567");
        sampleMentor8.setMentorEmail("Tim@School.edu");
        db.mentorDAO().insertMentor(sampleMentor8);

        listCourses = db.courseDAO().getCourseList(listTerms.get(2).getTerm_id());
        sampleMentor9.setCourse_id_fKey(listCourses.get(2).getCourse_id());
        sampleMentor9.setMentorName("Rick");
        sampleMentor9.setMentorNumber("123-555-6789");
        sampleMentor9.setMentorEmail("Rick@School.edu");
        db.mentorDAO().insertMentor(sampleMentor9);


    }

}
