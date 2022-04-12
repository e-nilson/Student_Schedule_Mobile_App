package com.example.c196.Database;

import android.app.Application;

import com.example.c196.DAO.AssessmentsDAO;
import com.example.c196.DAO.CoursesDAO;
import com.example.c196.DAO.TermsDAO;
import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Courses;
import com.example.c196.Entity.Terms;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// All the methods that call the methods in the DAO
public class Repository {
    private CoursesDAO mCoursesDAO;
    private TermsDAO mTermsDAO;
    private AssessmentsDAO mAssessmentsDAO;
    private List<Terms> mAllTerms;
    private List<Courses> mAllCourses;
    private List<Assessments> mAllAssessments;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        StudentScheduleBuilder db=StudentScheduleBuilder.getDatabase(application);
        mTermsDAO=db.termsDAO();
        mCoursesDAO=db.coursesDAO();
        mAssessmentsDAO=db.assessmentsDAO();
    }

    public void insert(Terms terms){
        mTermsDAO.insertTerm(terms);
    }

}
