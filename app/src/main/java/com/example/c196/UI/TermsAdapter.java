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

import java.util.List;

public class TermsAdapter extends RecyclerView.Adapter<TermsAdapter.TermsViewHolder> {
    private List<Terms> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    class TermsViewHolder extends RecyclerView.ViewHolder {
        //private final TextView termIDItemView;
        private final TextView termTitleItemView;
        private final TextView termStartItemView;
        private final TextView termEndItemView;

        // Constructor
        private TermsViewHolder(View itemView) {
            super(itemView);
            //termIDItemView = itemView.findViewById(R.id.textViewTermID);
            termTitleItemView = itemView.findViewById(R.id.textView3);
            termStartItemView = itemView.findViewById(R.id.textView4);
            termEndItemView = itemView.findViewById(R.id.textView5);
            itemView.setOnClickListener(new View.OnClickListener() {

                // Sends it to the course screen
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Terms current = mTerms.get(position);
                    Intent intent = new Intent(context,CoursesList.class);
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("termTitle", current.getTermTitle());
                    intent.putExtra("startDate", current.getTermStartDate());
                    intent.putExtra("endDate", current.getTermEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }


    public TermsAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public TermsAdapter.TermsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.terms_list,parent,false);
        return new TermsViewHolder(itemView);
    }

    // This is where you put things on the text field
    @Override
    public void onBindViewHolder(@NonNull TermsAdapter.TermsViewHolder holder, int position) {
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

    public void setTerms(List<Terms> terms) {
        mTerms = terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mTerms != null) {
            return mTerms.size();
        }
        else return 0;
    }
}