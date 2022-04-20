package com.example.c196.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Courses;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.List;

public class CourseDetailList extends AppCompatActivity {

    // Declare edit text
    private Repository repository;
    private int courseID;
    private int termID;
    Courses currentCourse;
    RecyclerView recyclerView;
    List<Courses> allCourses;

    EditText editTitle;
    EditText editStartDate;
    EditText editEndDate;
    EditText editStatus;
    EditText editName;
    EditText editPhone;
    EditText editEmail;
    EditText editNotes;

    // Initializes the Course Details page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // methods for entering and saving course from course details page back to courses page
        courseID = getIntent().getIntExtra("courseID", -1);

        Repository repository = new Repository(getApplication());

        allCourses = repository.getAllCourses();
        for (Courses c:allCourses){
            if (c.getCourseID()== courseID) currentCourse = c;
        }
        editTitle = findViewById(R.id.editCourseTitle);
        editStartDate = findViewById(R.id.editCourseStartDate);
        editEndDate = findViewById(R.id.editCourseEndDate);
        editStatus = findViewById(R.id.editCourseStatus);
        editName = findViewById(R.id.editCourseInstructorName);
        editPhone = findViewById(R.id.editCourseInstructorPhone);
        editEmail = findViewById(R.id.editCourseInstructorEmail);
        editNotes = findViewById(R.id.editCourseNotes);

        if (currentCourse != null) {
            editTitle.setText(currentCourse.getCourseTitle());
            editStartDate.setText(currentCourse.getCourseStartDate());
            editEndDate.setText(currentCourse.getCourseEndDate());
            editStatus.setText(currentCourse.getCourseStatus());
            editName.setText(currentCourse.getInstructorName());
            editPhone.setText(currentCourse.getInstructorPhone());
            editEmail.setText(currentCourse.getInstructorEmail());
            editNotes.setText(currentCourse.getCourseNotes());
        }


        RecyclerView recyclerView = findViewById(R.id.assessmentrecyclerview);
        //Repository repository = new Repository(getApplication());

        List<Assessments> allAssessments = repository.getAllAssessments();
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(allAssessments);

    }

    // Saves new courses and/or assessment details
    public void saveCourseDetails(View view) {
        Courses courses;
        if (courseID == -1) {
            int newCourseID = repository.getAllCourses().get(repository.getAllCourses().size() - 1).getCourseID() + 1;
            courses = new Courses(newCourseID, editTitle.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString(), editStatus.getText().toString(), editName.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString(), editNotes.getText().toString(), termID);
            repository.insert(courses);
        } else {
            courses = new Courses(courseID, editTitle.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString(), editStatus.getText().toString(), editName.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString(), editNotes.getText().toString(), termID);
            repository.update(courses);
        }
    }


    // Inflates the menu and adds items to the action bar if present
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"Text from note field");
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Message title");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;

            case R.id.notifystart:
                return true;

            case R.id.notifyend:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
