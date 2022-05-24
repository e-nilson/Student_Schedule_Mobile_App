package com.example.c196.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "terms_table")
public class Terms {
    @PrimaryKey(autoGenerate = true)
    private int termID;

    private String termTitle;
    private String termStartDate;
    private String termEndDate;
    private String termCreateDate;

    // Constructors for terms
    public Terms(int termID, String termTitle, String termStartDate, String termEndDate, String termCreateDate) {
        this.termID = termID;
        this.termTitle = termTitle;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
        this.termCreateDate = termCreateDate;
    }

    // Setter and getters for terms
    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public String getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(String termStartDate) {
        this.termStartDate = termStartDate;
    }

    public String getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(String termEndDate) {
        this.termEndDate = termEndDate;
    }

    public String getTermCreateDate() {
        return termCreateDate;
    }

    public void setTermCreateDate(String createDate) {
        this.termCreateDate = termCreateDate;
    }

    @Override
    public String toString() {
        return "Terms{" +
                "termID=" + termID +
                ", termTitle='" + termTitle + '\'' +
                ", termStartDate=" + termStartDate +
                ", termEndDate=" + termEndDate +
                '}';
    }
}
