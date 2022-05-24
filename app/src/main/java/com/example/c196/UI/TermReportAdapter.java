package com.example.c196.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.List;

public class TermReportAdapter extends RecyclerView.Adapter<TermReportAdapter.TermReportViewHolder> {

    private Context context;
    private List<Terms> termsList;
    private LayoutInflater mInflater;

    public TermReportAdapter(Context context, List<Terms> termsList) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.termsList = termsList;
    }

    @NonNull
    @Override
    public TermReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.report_list,parent,false);
        return new TermReportViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermReportViewHolder holder, int position) {
        if (termsList != null && termsList.size() > 0) {
            Terms term = termsList.get(position);
            holder.termTitle.setText(term.getTermTitle());
            holder.termCreateDate.setText(term.getTermCreateDate());
        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return termsList.size();
    }

    public class TermReportViewHolder extends RecyclerView.ViewHolder {
        TextView termTitle, termCreateDate;
        public TermReportViewHolder(@NonNull View itemView) {
            super(itemView);
            termTitle = itemView.findViewById(R.id.termTitle);
            termCreateDate = itemView.findViewById(R.id.termCreateDate);
        }
    }
}
