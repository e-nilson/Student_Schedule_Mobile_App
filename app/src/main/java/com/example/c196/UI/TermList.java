package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.ArrayList;
import java.util.List;

public class TermList extends AppCompatActivity {

    private Repository repository;
    Terms currentTerm;
    private RecyclerView recyclerView;
    private int numTerms;
    private int id;

    // Initializes the Terms homepage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.termsrecyclerview);
        Repository repository = new Repository(getApplication());

        List<Terms> allTerms = repository.getAllTerms();
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);
    }

    // Creates a menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // This adds items to the action bar if it's present
        getMenuInflater().inflate(R.menu.menu_term, menu);
        return true;
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