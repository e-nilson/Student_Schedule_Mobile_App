package com.example.c196.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Courses;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailList extends AppCompatActivity {

    public static int numAssessments;

    private Repository repository;
    private int courseID;
    private int termID;
    private int assessmentID;
    private Assessments currentAssessment;


    Courses currentCourse;
    Terms currentTerm;
    RecyclerView recyclerView;
    List<Courses> allCourses;
    List<Terms> allTerms;

    // Declare edit text
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
        repository = new Repository(getApplication());
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
        //editTermID = findViewById(R.id.editTermID);

        if (currentCourse != null) {
            editTitle.setText(currentCourse.getCourseTitle());
            editStartDate.setText(currentCourse.getCourseStartDate());
            editEndDate.setText(currentCourse.getCourseEndDate());
            editStatus.setText(currentCourse.getCourseStatus());
            editName.setText(currentCourse.getInstructorName());
            editPhone.setText(currentCourse.getInstructorPhone());
            editEmail.setText(currentCourse.getInstructorEmail());
            editNotes.setText(currentCourse.getCourseNotes());
            //editTermID.setText(currentCourse.getTermID);
        }


        // Adds the assessment recycler view to the course detail page based on the term ID
        RecyclerView recyclerView = findViewById(R.id.assessmentrecyclerview);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Assessments> filteredAssessments = new ArrayList<>();
        for(Assessments a: repository.getAllAssessments()){
            if(a.getCourseID() == courseID)filteredAssessments.add(a);
        }
        numAssessments=filteredAssessments.size();
        assessmentAdapter.setAssessments(filteredAssessments);


        /*
        RecyclerView recyclerView = findViewById(R.id.assessmentrecyclerview);
        //Repository repository = new Repository(getApplication());

        List<Assessments> allAssessments = repository.getAllAssessments();
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(allAssessments);

         */

    }

    // Saves new courses and/or assessment details
    public void saveCourseDetails(View view) {
        Courses courses;
        if (courseID == -1) {
            termID = 0;
            for (Courses course : repository.getAllCourses()) {
                if (course.getTermID() > termID);
                termID = ++termID;
            }
            int newCourseID = repository.getAllCourses().get(repository.getAllCourses().size() - 1).getCourseID() + 1;
            courses = new Courses(newCourseID, editTitle.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString(), editStatus.getText().toString(), editName.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString(), editNotes.getText().toString(), termID);
            repository.insert(courses);
        } else {
            courses = new Courses(courseID, editTitle.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString(), editStatus.getText().toString(), editName.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString(), editNotes.getText().toString(), termID);
            repository.update(courses);
        }
        Intent intent = new Intent(CourseDetailList.this, CourseList.class);
        startActivity(intent);
    }


    // Inflates the menu and adds items to the action bar if present
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course_detail, menu);
        return true;
    }

    // Tells what happens with the created menu functions
    public boolean onOptionsItemSelected (MenuItem item) {
        switch(item.getItemId()) {
            case R.id.home:
                Intent homeIntent = new Intent(CourseDetailList.this, MainActivity.class);
                startActivity(homeIntent);
                return true;

            case R.id.deleteCourse:
                    repository.delete(currentCourse);
                    Toast.makeText(getApplicationContext(), "Course deleted successfully", Toast.LENGTH_LONG).show();
                    Intent deleteIntent = new Intent(CourseDetailList.this, CourseList.class);
                    startActivity(deleteIntent);
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

    // Enters the assessment page
    public void enterAssessmentDetail (View view) {
        Intent intent = new Intent(CourseDetailList.this, AssessmentDetailList.class);
        if(currentAssessment != null) intent.putExtra("assessmentID", currentAssessment.getCourseID());
        intent.putExtra("courseID", courseID);
        startActivity(intent);
    }
}
