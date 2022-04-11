package com.example.c196.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Courses {
    @PrimaryKey(autoGenerate = true)
    private  int courseID;

    private String courseName;
    private double courseNumber;

    // Constructors for courses
    public Courses(int courseID, String courseName, double courseNumber) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", courseNumber=" + courseNumber +
                '}';
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(double courseNumber) {
        this.courseNumber = courseNumber;
    }
}
