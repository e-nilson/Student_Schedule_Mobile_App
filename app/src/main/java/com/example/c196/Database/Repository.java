package com.example.c196.Database;

import android.app.Application;

import com.example.c196.DAO.AssessmentsDAO;
import com.example.c196.DAO.CoursesDAO;
import com.example.c196.DAO.TermsDAO;
import com.example.c196.DAO.UsersDAO;
import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Courses;
import com.example.c196.Entity.Terms;
import com.example.c196.Entity.Users;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// All the methods that call the methods in the DAO
public class Repository {
    private CoursesDAO mCoursesDAO;
    private TermsDAO mTermsDAO;
    private AssessmentsDAO mAssessmentsDAO;
    private UsersDAO mUsersDAO;
    private List<Terms> mAllTerms;
    private List<Courses> mAllCourses;
    private List<Assessments> mAllAssessments;
    private List<Users> mAllUsers;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        StudentScheduleBuilder db=StudentScheduleBuilder.getDatabase(application);
        mTermsDAO=db.termsDAO();
        mCoursesDAO=db.coursesDAO();
        mAssessmentsDAO=db.assessmentsDAO();
        mUsersDAO = db.usersDAO();
    }

    // Gets all terms from the database
    public List<Terms> getAllTerms(){
        databaseExecutor.execute(()->{
            mAllTerms= mTermsDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public void insert(Terms terms) {
        databaseExecutor.execute(()->{
            mTermsDAO.insertTerm(terms);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Terms terms){
        databaseExecutor.execute(()->{
            mTermsDAO.updateTerm(terms);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Terms terms){
        databaseExecutor.execute(()->{
            mTermsDAO.deleteTerm(terms);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Gets all courses from the database
    public List<Courses> getAllCourses(){
        databaseExecutor.execute(()->{
            mAllCourses= mCoursesDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public void insert(Courses courses){
        databaseExecutor.execute(()->{
            mCoursesDAO.insertCourse(courses);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Courses courses){
        databaseExecutor.execute(()->{
            mCoursesDAO.updateCourse(courses);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Courses courses){
        databaseExecutor.execute(()->{
            mCoursesDAO.deleteCourse(courses);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Gets all assessments from the database
    public List<Assessments> getAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments= mAssessmentsDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public void insert(Assessments assessments){
        databaseExecutor.execute(()->{
            mAssessmentsDAO.insertAssessment(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Assessments assessments){
        databaseExecutor.execute(()->{
            mAssessmentsDAO.updateAssessment(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Assessments assessments){
        databaseExecutor.execute(()->{
            mAssessmentsDAO.deleteAssessment(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Users> getAllUsers() {
        databaseExecutor.execute(() -> {
            mAllUsers = mUsersDAO.getAllUsers();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllUsers;
    }

    public void insert(Users user) {
        databaseExecutor.execute(() -> {
            mUsersDAO.insertUser(user);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Users user) {
        databaseExecutor.execute(() -> {
            mUsersDAO.updateUser(user);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Users user) {
        databaseExecutor.execute(() -> {
            mUsersDAO.deleteUser(user);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
