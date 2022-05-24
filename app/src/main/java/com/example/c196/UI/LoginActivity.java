package com.example.c196.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Users;
import com.example.c196.R;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private String username;
    private String password;
    private EditText editUserName;
    private EditText editPassword;
    private List<Users> allUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUserName = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);

        // Using "test" for username and password login
        Repository repository = new Repository(getApplication());
        Users usersEntity = new Users(1, "test", "test");
        repository.insert(usersEntity);

    }
    // Sends user to the main activity screen
    public void enterButton(View view) {
        Users currentUser;
        username = editUserName.getText().toString();
        password = editPassword.getText().toString();

        // Checks if text fields are empty
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Fill out required fields", Toast.LENGTH_LONG).show();
        } else {
            // Compare user entered text fields to users_table stored username and passwords
            Repository user_repository = new Repository(getApplication());
            allUsers = user_repository.getAllUsers();
            for (int i = 0; i < allUsers.size(); i++) {
                currentUser = allUsers.get(i);

                if (currentUser.getUserName().equals(username) && currentUser.getPassword().equals(password)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, "Username and/or password are incorrect", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
