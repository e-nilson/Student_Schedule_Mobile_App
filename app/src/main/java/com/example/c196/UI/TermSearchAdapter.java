package com.example.c196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.ArrayList;

public class TermSearchAdapter extends RecyclerView.Adapter<TermSearchAdapter.TermSearchViewHolder> {

    private ArrayList<Terms> termsArrayList;
    private Context context;

    public TermSearchAdapter(ArrayList<Terms> termsArrayList, Context context) {
        this.termsArrayList = termsArrayList;
        this.context = context;
    }

    public void filterList(ArrayList<Terms> filterTerm) {
        termsArrayList = filterTerm;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TermSearchAdapter.TermSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_list, parent, false);
        return new TermSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermSearchAdapter.TermSearchViewHolder holder, int position) {
        final Terms term = termsArrayList.get(position);
        holder.termTitleItemView.setText(term.getTermTitle());
        holder.termCreateDate.setText(term.getTermCreateDate());
    }

    @Override
    public int getItemCount() {
        return termsArrayList.size();
    }

    class TermSearchViewHolder extends RecyclerView.ViewHolder {
        private final TextView termTitleItemView;
        private final TextView termCreateDate;

        private TermSearchViewHolder(View itemView) {
            super(itemView);
            termTitleItemView = itemView.findViewById(R.id.termTitle);
            termCreateDate = itemView.findViewById(R.id.termCreateDate);
        }
    }
}
