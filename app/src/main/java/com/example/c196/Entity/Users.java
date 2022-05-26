package com.example.c196.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users_table")
public class Users {
    @PrimaryKey(autoGenerate = true)
    private final int userID;
    private String userName;
    private String password;

    public Users(int userID, String userName, String password) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password=" + password +
                '}';
    }

    public boolean validUserInput(int userID, String username, String password) {
        int MAX_LENGTH = 20;

        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        /*
        if (username.length() > MAX_LENGTH) {
            return false;
        }
        if (password.length() > MAX_LENGTH) {
            return false;
        }

         */
        return true;
    }
}
