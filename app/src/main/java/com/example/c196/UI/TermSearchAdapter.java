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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.term_list, parent, false);
        return new TermSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermSearchAdapter.TermSearchViewHolder holder, int position) {

        final Terms term = termsArrayList.get(position);
        holder.termTitleItemView.setText(term.getTermTitle());
        holder.termStartItemView.setText(term.getTermStartDate());
        holder.termEndItemView.setText(term.getTermEndDate());
    }

    @Override
    public int getItemCount() {
        return termsArrayList.size();
    }

    class TermSearchViewHolder extends RecyclerView.ViewHolder {
        private final TextView termTitleItemView;
        private final TextView termStartItemView;
        private final TextView termEndItemView;

        private TermSearchViewHolder(View itemView) {
            super(itemView);
            termTitleItemView = itemView.findViewById(R.id.textViewTermTitle2);
            termStartItemView = itemView.findViewById(R.id.textViewTermStart);
            termEndItemView = itemView.findViewById(R.id.textViewTermEnd);

            /*
            // on click listener for terms to send to detailed page

            itemView.setOnClickListener(new View.OnClickListener() {
                // Sends it to the detailed screen
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Terms term = termsArrayList.get(position);
                    Intent intent = new Intent(context, TermDetailList.class);
                    intent.putExtra("termID", term.getTermID());
                    intent.putExtra("termTitle", term.getTermTitle());
                    intent.putExtra("startDate", term.getTermStartDate());
                    intent.putExtra("endDate", term.getTermEndDate());
                    context.startActivity(intent);
                }
            });

             */
        }
    }
}
