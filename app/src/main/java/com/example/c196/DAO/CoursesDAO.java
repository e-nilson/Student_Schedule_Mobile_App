package com.example.c196.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196.Entity.Courses;

import java.util.List;

// Inserts courses into the database
@Dao
public interface CoursesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCourse(Courses courses);

    @Update
    void updateCourse(Courses courses);

    @Delete
    void deleteCourse(Courses courses);

    @Query("SELECT * FROM courses_table ORDER BY courseID ASC")
    List<Courses> getAllCourses();

    @Query("DELETE FROM courses_table")
    void deleteAllCourses();
}
