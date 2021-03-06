package com.example.c196.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c196.DAO.AssessmentsDAO;
import com.example.c196.DAO.CoursesDAO;
import com.example.c196.DAO.TermsDAO;
import com.example.c196.DAO.UsersDAO;
import com.example.c196.Entity.Terms;
import com.example.c196.Entity.Courses;
import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Users;

// Database with terms, courses, and assessments tables
@Database(entities = {Terms.class, Courses.class, Assessments.class, Users.class}, version = 3, exportSchema = false)
public abstract class StudentScheduleBuilder extends RoomDatabase {
    public abstract TermsDAO termsDAO();
    public abstract CoursesDAO coursesDAO();
    public abstract AssessmentsDAO assessmentsDAO();
    public abstract UsersDAO usersDAO();

    // Making instance in DB
    private static volatile StudentScheduleBuilder INSTANCE;

    // basic part of android. Tells you where you are in the flow of the program
    static StudentScheduleBuilder getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (StudentScheduleBuilder.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),StudentScheduleBuilder.class, "myStudentDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
