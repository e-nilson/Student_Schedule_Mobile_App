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
import com.example.c196.Entity.Courses;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.ArrayList;
import java.util.List;

public class TermDetailList extends AppCompatActivity {

    // Declare edit text
    public static int numCourses;

    private Repository repository;
    private int termID;
    private Courses currentCourses;
    private int numTerms;
    
    Terms currentTerm;
    RecyclerView recyclerView;
    List<Terms> allTerms;

    EditText editTitle;
    EditText editStartDate;
    EditText editEndDate;

    // Initializes the Terms Details page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // methods for entering and saving term from terms details page back to terms page
        termID = getIntent().getIntExtra("termID", -1);
        repository = new Repository(getApplication());
        allTerms = repository.getAllTerms();
        for (Terms t:allTerms){
            if (t.getTermID()== termID) currentTerm = t;
        }
        editTitle = findViewById(R.id.editTermTitle);
        editStartDate = findViewById(R.id.editTermStartDate);
        editEndDate = findViewById(R.id.editTermEndDate);
        if (currentTerm != null) {
            editTitle.setText(currentTerm.getTermTitle());
            editStartDate.setText(currentTerm.getTermStartDate());
            editEndDate.setText(currentTerm.getTermEndDate());
        }

        // Adds the course recycler view to the terms detail page based on the term ID
        RecyclerView recyclerView = findViewById(R.id.courserecyclerview);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Courses> filteredCourses = new ArrayList<>();
        for(Courses c: repository.getAllCourses()){
            if(c.getTermID() == termID)filteredCourses.add(c);
        }
        numCourses=filteredCourses.size();
        courseAdapter.setCourses(filteredCourses);

    }


    // Saves new term and/or course details
    public void saveTermDetails(View view) {
        Terms terms;
        if (termID == -1) {
            int newTermID = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermID() + 1;
            terms = new Terms(newTermID, editTitle.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
            repository.insert(terms);
        } else {
            terms = new Terms(termID, editTitle.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
            repository.update(terms);
        }
        Intent intent = new Intent(TermDetailList.this, TermList.class);
        startActivity(intent);
    }

    // Creates a menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // This adds items to the action bar if it's present
        getMenuInflater().inflate(R.menu.menu_term_detail, menu);
        return true;
    }

    // Tells what happens with the created menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent homeIntent = new Intent(TermDetailList.this, MainActivity.class);
                startActivity(homeIntent);
                return true;

            case R.id.deleteTerm:
                if (numCourses == 0) {
                    repository.delete(currentTerm);
                    Toast.makeText(getApplicationContext(), "Term deleted successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(TermDetailList.this, TermList.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot delete a Term that contains courses", Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Enters the detailed course page
    public void enterCourseDetail (View view) {
        Intent intent = new Intent(TermDetailList.this, CourseDetailList.class);
        if(currentCourses != null) intent.putExtra("courseID", currentCourses.getTermID());
        intent.putExtra("termID", termID);
        startActivity(intent);
    }
}
