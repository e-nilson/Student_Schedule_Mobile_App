package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.c196.R;

public class AssessmentsDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Creates a menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // This adds items to the action bar if it's present
        getMenuInflater().inflate(R.menu.menu_detailedassessmentview, menu);
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

    // adds details about the selected assessment
    public void addAssessmentDetails(View view) {
    }
}