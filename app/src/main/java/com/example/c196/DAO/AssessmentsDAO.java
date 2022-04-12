package com.example.c196.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196.Entity.Assessments;

import java.util.List;

// Inserts assessments into the DB
@Dao
public interface AssessmentsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAssessment(Assessments assessments);

    @Update
    void updateAssessment(Assessments assessments);

    @Delete
    void deleteAssessment(Assessments assessments);

    @Query("SELECT * FROM assessments_table ORDER BY assessmentID ASC")
    List<Assessments> getAllAssessments();

    @Query("DELETE FROM assessments_table")
    void deleteAllAssessments();
}
