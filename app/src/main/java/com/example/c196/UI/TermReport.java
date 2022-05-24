package com.example.c196.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.List;

public class TermReport extends AppCompatActivity {

    Repository repository;
    private TermReportAdapter termReportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_report);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        repository = new Repository(getApplication());
        List<Terms> allTerms = repository.getAllTerms();

        RecyclerView recyclerView = findViewById(R.id.termsReportRecyclerView);
        termReportAdapter = new TermReportAdapter(this, allTerms);
        recyclerView.setAdapter(termReportAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    // Tells what happens with the created menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent homeIntent = new Intent(TermReport.this, TermList.class);
                startActivity(homeIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
