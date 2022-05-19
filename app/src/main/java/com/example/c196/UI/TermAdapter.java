package com.example.c196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    private List<Terms> mTerms;
    private Context context;
    private LayoutInflater mInflater;


    class TermViewHolder extends RecyclerView.ViewHolder {
        //private final TextView termIDItemView;
        private final TextView termTitleItemView;
        private final TextView termStartItemView;
        private final TextView termEndItemView;

        // Constructor
        private TermViewHolder(View itemView) {
            super(itemView);
            //termIDItemView = itemView.findViewById(R.id.textViewTermID);
            termTitleItemView = itemView.findViewById(R.id.textViewTermTitle2);
            termStartItemView = itemView.findViewById(R.id.textViewTermStart);
            termEndItemView = itemView.findViewById(R.id.textViewTermEnd);
            itemView.setOnClickListener(new View.OnClickListener() {

                // Sends it to the detailed assessment screen
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Terms current = mTerms.get(position);
                    Intent intent = new Intent(context, TermDetailList.class);
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("termTitle", current.getTermTitle());
                    intent.putExtra("startDate", current.getTermStartDate());
                    intent.putExtra("endDate", current.getTermEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.term_list,parent,false);
        return new TermViewHolder(itemView);
    }

    // This is where you put things on the text field
    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        if(mTerms !=null) {
            final Terms current = mTerms.get(position);
            //holder.termIDItemView.setText(Integer.toString(current.getTermID()));
            holder.termTitleItemView.setText(current.getTermTitle());
            holder.termStartItemView.setText(current.getTermStartDate());
            holder.termEndItemView.setText(current.getTermEndDate());
        }
        else {
            //holder.termIDItemView.setText("No Term ID");
            holder.termTitleItemView.setText("No Term Title");
            holder.termStartItemView.setText("No Term Start Date");
            holder.termEndItemView.setText("No Term End Date");
        }
    }


    @Override
    public int getItemCount() {
        if(mTerms != null) {
            return mTerms.size();
        }
        else return 0;
    }

    public void setTerms(List<Terms> terms) {
        mTerms = terms;
        notifyDataSetChanged();
    }

    public TermAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
}