package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Courses;
import com.example.c196.R;

import java.util.List;

public class AssessmentDetailList extends AppCompatActivity {

    private Repository repository;
    private int assessmentID;
    private int termID;
    private int courseID;

    Assessments currentAssessment;
    Courses courses;
    RecyclerView recyclerView;
    List<Assessments> allAssessments;
    List<Courses> allCourses;

    // Declare edit text
    EditText editTitle;
    EditText editStartDate;
    EditText editEndDate;
    EditText editType;

    // Initializes the Assessment Details homepage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // methods for entering and saving assessments from assessment details page back to assessments page
        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        repository = new Repository(getApplication());
        allAssessments = repository.getAllAssessments();
        for (Assessments a:allAssessments){
            if (a.getAssessmentID()== assessmentID) currentAssessment = a;
        }
        editType = findViewById(R.id.editAssessmentType);
        editTitle = findViewById(R.id.editAssessmentTitle);
        editStartDate = findViewById(R.id.editAssessmentStartDate);
        editEndDate = findViewById(R.id.editAssessmentEndDate);

        if (currentAssessment != null) {
            editType.setText(currentAssessment.getAssessmentType());
            editTitle.setText(currentAssessment.getAssessmentTitle());
            editStartDate.setText(currentAssessment.getAssessmentStartDate());
            editEndDate.setText(currentAssessment.getAssessmentEndDate());
        }
    }


    // Creates a menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // This adds items to the action bar if it's present
        getMenuInflater().inflate(R.menu.menu_assessment_detail, menu);
        return true;
    }

    // Tells what happens with the created menu functions
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent homeIntent = new Intent(AssessmentDetailList.this, MainActivity.class);
                startActivity(homeIntent);
                return true;

            case R.id.deleteAssessment:
                repository.delete(currentAssessment);
                Toast.makeText(getApplicationContext(), "Assessment deleted successfully", Toast.LENGTH_LONG).show();
                Intent deleteIntent = new Intent(AssessmentDetailList.this, AssessmentList.class);
                startActivity(deleteIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Saves new assessments and/or assessment details
    public void saveAssessmentDetails(View view) {
        Assessments assessments;
        if (assessmentID == -1) {
            courseID = 0;
            for (Assessments assessment : repository.getAllAssessments()) {
                if (assessment.getCourseID() > courseID);
                courseID = ++courseID;
            }
            int newAssessmentID = repository.getAllAssessments().get(repository.getAllAssessments().size() - 1).getAssessmentID() + 1;
            assessments = new Assessments(newAssessmentID, editType.getText().toString(), editTitle.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString(), courseID);
            repository.insert(assessments);
        } else {
            assessments = new Assessments(assessmentID, editType.getText().toString(), editTitle.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString(), courseID);
            repository.update(assessments);
        }
        Intent intent = new Intent(AssessmentDetailList.this, AssessmentList.class);
        startActivity(intent);
    }

        // adds details about the selected assessment
    public void addAssessmentDetails(View view) {
    }

}