package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Courses;
import com.example.c196.R;

import java.util.List;

public class AssessmentDetailList extends AppCompatActivity {

    // Declare edit text
    private Repository repository;
    private int assessmentID;
    private int termID;
    Assessments currentAssessment;
    RecyclerView recyclerView;
    List<Assessments> allAssessments;

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

        Repository repository = new Repository(getApplication());

        allAssessments = repository.getAllAssessments();
        for (Assessments a:allAssessments){
            if (a.getAssessmentID()== assessmentID) currentAssessment = a;
        }
        editTitle = findViewById(R.id.editAssessmentTitle);
        editStartDate = findViewById(R.id.editAssessmentStartDate);
        editEndDate = findViewById(R.id.editAssessmentEndDate);
        editType = findViewById(R.id.editAssessmentType);

        if (currentAssessment != null) {
            editTitle.setText(currentAssessment.getAssessmentTitle());
            editStartDate.setText(currentAssessment.getAssessmentStartDate());
            editEndDate.setText(currentAssessment.getAssessmentEndDate());
            editType.setText(currentAssessment.getAssessmentType());
        }
    }


    // Creates a menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // This adds items to the action bar if it's present
        getMenuInflater().inflate(R.menu.menu_assessment, menu);
        return true;
    }

    // Tells what happens with the created menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void saveAssessmentDetails(View view) {
    }

        // adds details about the selected assessment
    public void addAssessmentDetails(View view) {
    }

}