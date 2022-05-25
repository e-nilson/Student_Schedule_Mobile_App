package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Courses;
import com.example.c196.Entity.Terms;
import com.example.c196.Entity.Users;
import com.example.c196.R;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Date currentDateTime = Calendar.getInstance().getTime();
        String termCreateDate = currentDateTime.toString();

        // Adds sample data
        Repository repository = new Repository(getApplication());
        Terms termsEntity = new Terms(1,"Spring Term", "03/01/2022","06/30/2022", termCreateDate);
        repository.insert(termsEntity);
        Terms termsEntity2 = new Terms(2,"Fall Term", "09/01/2022","12/31/2022", termCreateDate );
        repository.insert(termsEntity2);
        Courses coursesEntity = new Courses(1, "Mobile App Development", "03/01/2022","06/30/2022","In-progress","Carolyn","123-456-7890","c@wgu.edu", "test notes", 1);
        repository.insert(coursesEntity);
        Courses coursesEntity2 = new Courses(2, "Software Engineering", "09/01/2022","12/31/2022","In-progress","Ben","123-456-7890","b@wgu.edu", "test notes", 2);
        repository.insert(coursesEntity2);
        Assessments assessmentsEntity = new Assessments(1,"Performance Assessment", "Mobile App", "03/01/2022",  "06/30/2022",1);
        repository.insert(assessmentsEntity);
        Assessments assessmentsEntity2 = new Assessments(2,"Objective Assessment", "Desktop App", "09/01/2022",  "12/31/2022",2);
        repository.insert(assessmentsEntity2);
    }

    // navigates to the terms homepage
    public void Terms(View view) {
        Intent intent = new Intent(MainActivity.this, TermList.class);
        startActivity(intent);
    }

    // navigates to the courses homepage
    public void Courses(View view) {
        Intent intent = new Intent(MainActivity.this, CourseList.class);
        startActivity(intent);
    }

    // navigates to the assessments homepage
    public void Assessments(View view) {
        Intent intent = new Intent(MainActivity.this, AssessmentList.class);
        startActivity(intent);
    }
}