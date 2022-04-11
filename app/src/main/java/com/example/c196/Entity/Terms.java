package com.example.c196.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "terms")
public class Terms {
    @PrimaryKey(autoGenerate = true)
    private int termID;

    private String termName;
    private Double termNumber;

    // Constructors for terms
    public Terms(int termID, String termName, Double termNumber) {
        this.termID = termID;
        this.termName = termName;
        this.termNumber = termNumber;
    }

    @Override
    public String toString() {
        return "Terms{" +
                "termID=" + termID +
                ", termName='" + termName + '\'' +
                ", termNumber=" + termNumber +
                '}';
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Double getTermNumber() {
        return termNumber;
    }

    public void setTermNumber(Double termNumber) {
        this.termNumber = termNumber;
    }
}
