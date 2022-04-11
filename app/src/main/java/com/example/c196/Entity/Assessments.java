package com.example.c196.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments")
public class Assessments {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;

    private String assessmentName;
    private Double assessmentNumber;

    // Constructors for assessments
    public Assessments(int assessmentID, String assessmentName, Double assessmentNumber) {
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
        this.assessmentNumber = assessmentNumber;
    }

    @Override
    public String toString() {
        return "Assessments{" +
                "assessmentID=" + assessmentID +
                ", assessmentName='" + assessmentName + '\'' +
                ", assessmentNumber=" + assessmentNumber +
                '}';
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public Double getAssessmentNumber() {
        return assessmentNumber;
    }

    public void setAssessmentNumber(Double assessmentNumber) {
        this.assessmentNumber = assessmentNumber;
    }
}
