package com.example.c196.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.ArrayList;
import java.util.List;

public class TermReportList extends AppCompatActivity {

    private Repository repository;

    private TermReportAdapter termReportAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Terms> termsArrayList;
    private Context context;

    // Initializes the terms report page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_report);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        repository = new Repository(getApplication());
        List<Terms> allTerms = repository.getAllTerms();

        recyclerView = findViewById(R.id.termsReportRecyclerView);
        TermReportAdapter termReportAdapter = new TermReportAdapter(this, allTerms);
        recyclerView.setAdapter(termReportAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    // Tells what happens with the created menu
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent homeIntent = new Intent(TermReportList.this, TermList.class);
                startActivity(homeIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

     */

    // Creates a menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Adds items to the action bar if it's present
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search Terms");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // calling method to filter recyclerview
                filter(newText);
                return false;
            }
        });
        return true;
    }


    // added search term functionality
    private void filter(String text) {
        ArrayList<Terms> filteredList = new ArrayList<>();
        TermSearchAdapter adapter = new TermSearchAdapter(termsArrayList, context);
        recyclerView = findViewById(R.id.termsReportRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository = new Repository(getApplication());

        for (Terms term : repository.getAllTerms()) {
            if (term.getTermTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(term);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "Term Not Found...", Toast.LENGTH_SHORT).show();
        }
        adapter.filterList(filteredList);
    }

}
