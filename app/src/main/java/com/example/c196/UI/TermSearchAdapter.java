package com.example.c196.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.ArrayList;
import java.util.List;

public class TermSearchAdapter extends RecyclerView.Adapter<TermSearchAdapter.TermSearchViewHolder> {

    private ArrayList<Terms> termsArrayList;
    private Context context;

    public TermSearchAdapter (ArrayList<Terms> termsArrayList, Context context) {
        this.termsArrayList = termsArrayList;
        this.context = context;
    }

    public void filterList (ArrayList<Terms> filterTerm) {
        termsArrayList = filterTerm;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TermSearchAdapter.TermSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.term_list, parent, false);
        return new TermSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermSearchAdapter.TermSearchViewHolder holder, int position) {
        Terms term = termsArrayList.get(position);
        holder.termTitle.setText(term.getTermTitle());
        holder.termStartDate.setText(term.getTermStartDate());
        holder.termEndDate.setText(term.getTermEndDate());
    }

    @Override
    public int getItemCount() {
        return termsArrayList.size();
    }

    public class TermSearchViewHolder extends RecyclerView.ViewHolder {
        private TextView termTitle;
        private TextView termStartDate;
        private TextView termEndDate;

        public TermSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            termTitle = itemView.findViewById(R.id.textViewTermTitle2);
            termStartDate = itemView.findViewById(R.id.textViewTermStart);
            termEndDate = itemView.findViewById(R.id.textViewTermEnd);
        }
    }
}
