package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Courses;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

public class MainActivity extends AppCompatActivity {

    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Repository repository = new Repository(getApplication());
        Terms termsEntity = new Terms(1,"Spring Term", "03/01/2022","06/30/2022" );
        repository.insert(termsEntity);
        Courses coursesEntity = new Courses(1, "C196", "01/01/2022","03/31/2022","In-progress","Carolyn","123-456-7890","c@wgu.edu", "test notes", 1);
        repository.insert(coursesEntity);
        Assessments assessmentsEntity = new Assessments(1,"Performance Assessment", "Mobile App Dev", "01/01/2022",  "03/31/2022",1);
        repository.insert(assessmentsEntity);
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