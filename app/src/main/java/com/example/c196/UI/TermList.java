package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Courses;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TermList extends AppCompatActivity {

    private Repository repository;
    Terms currentTerm;
    private RecyclerView recyclerView;
    private int numTerms;
    private ArrayList<Terms> termsArrayList;
    private Context context;

    // Initializes the Terms homepage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.termsrecyclerview);
        Repository repository = new Repository(getApplication());

        List<Terms> allTerms = repository.getAllTerms();
        TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);
    }

    // Creates a menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // This adds items to the action bar if it's present
        getMenuInflater().inflate(R.menu.menu_search, menu);

        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_search, menu);

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

    // Tells what happens with the created menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent homeIntent = new Intent(TermList.this, MainActivity.class);
                startActivity(homeIntent);
                return true;

            case R.id.refresh:
                refreshTermList();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    // Refreshes the term list from the menu
    private void refreshTermList() {
        recyclerView = findViewById(R.id.termsrecyclerview);
        final TermAdapter adapter = new TermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Terms> filteredTerms = new ArrayList<>();
        List<Terms> allTerms = repository.getAllTerms();

        numTerms = filteredTerms.size();
        adapter.setTerms(allTerms);
    }


    // Enters the term detail page
    public void enterTermDetail(View view) {
        Intent intent = new Intent(TermList.this, TermDetailList.class);
        if(currentTerm != null) intent.putExtra("termID", currentTerm.getTermID());
        startActivity(intent);
    }
}