package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessments;
import com.example.c196.R;

import java.util.List;

public class AssessmentList extends AppCompatActivity {

    private Repository repository;
    Assessments currentAssessments;
    private RecyclerView recyclerView;
    private int numAssessments;
    private int id;

    // Initializes the Assessments homepage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.assessmentrecyclerview);
        Repository repository = new Repository(getApplication());

        List<Assessments> allAssessments = repository.getAllAssessments();
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(allAssessments);
    }

    public void DetailedAssessmentView(View view) {
        Intent intent = new Intent(AssessmentList.this, AssessmentDetailList.class);
        startActivity(intent);
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

    // Adds an assessment to the assessment list
    public void enterAssessmentDetail(View view) {
        Intent intent = new Intent(AssessmentList.this, AssessmentDetailList.class);
        if(currentAssessments != null) intent.putExtra("assessmentID", currentAssessments.getAssessmentID());
        startActivity(intent);
    }
}