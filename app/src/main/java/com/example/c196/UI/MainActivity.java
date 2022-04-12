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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Repository repository = new Repository(getApplication());
        Terms termsEntity = new Terms(1,"Winter", "01/01/22","03/31/22" );
        repository.insert(termsEntity);
        Courses coursesEntity = new Courses(1, "C196", "01/01/22","03/31/22","in-progress","Carolyn","123-456-7890","c@wgu.edu", "test notes", 1);
        repository.insert(coursesEntity);
        Assessments assessmentsEntity = new Assessments(1,"Performance Assessment", "Mobile App", "01/01/2022",  "03/31/22",1);
        repository.insert(assessmentsEntity);
    }

    public void Terms(View view) {
        Intent intent = new Intent(MainActivity.this, TermsList.class);
        startActivity(intent);
    }

    public void Courses(View view) {
        Intent intent = new Intent(MainActivity.this, CoursesList.class);
        startActivity(intent);
    }

    public void Assessments(View view) {
        Intent intent = new Intent(MainActivity.this, AssessmentsList.class);
        startActivity(intent);
    }
}