package com.example.c196.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196.Entity.Users;

import java.util.List;

@Dao
public interface UsersDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(Users user);

    @Update
    void updateUser(Users user);

    @Delete
    void deleteUser(Users user);

    @Query("SELECT * FROM users_table ORDER BY userID ASC")
    List<Users> getAllUsers();

    @Query("DELETE FROM users_table")
    void deleteAllUsers();



}
