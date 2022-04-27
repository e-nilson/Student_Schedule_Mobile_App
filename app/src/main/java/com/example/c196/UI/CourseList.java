package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Courses;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.ArrayList;
import java.util.List;

public class CourseList extends AppCompatActivity {

    private Repository repository;
    Courses currentCourses;
    private RecyclerView recyclerView;
    private int numCourses;
    private int id;


    // Initializes the Courses homepage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.courserecyclerview);
        Repository repository = new Repository(getApplication());

        List<Courses> allCourses = repository.getAllCourses();
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(allCourses);
    }

    // Creates a menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // This adds items to the action bar if it's present
        getMenuInflater().inflate(R.menu.menu_course, menu);
        return true;
    }

    // Tells what happens with the created menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent homeIntent = new Intent(CourseList.this, MainActivity.class);
                startActivity(homeIntent);
                return true;

            case R.id.refresh:
                refreshCourseList();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshCourseList() {
        recyclerView = findViewById(R.id.courserecyclerview);
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Courses> filteredCourses = new ArrayList<>();
        List<Courses> allCourses = repository.getAllCourses();

        numCourses = filteredCourses.size();
        adapter.setCourses(allCourses);
    }

    // Enters the course detail page
    public void enterCourseDetail(View view) {
        Intent intent = new Intent(CourseList.this, CourseDetailList.class);
        if(currentCourses != null) intent.putExtra("courseID", currentCourses.getCourseID());
        startActivity(intent);
    }
}