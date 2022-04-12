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
    class TermsViewHolder extends RecyclerView.ViewHolder {
        private final TextView termItemView;
        private final TextView termItemView2;

        private TermsViewHolder(View itemView) {
            super(itemView);
            termItemView = itemView.findViewById(R.id.textView);
            termItemView2 = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
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
    private List<Terms> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    public TermsAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public TermsAdapter.TermsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.terms_list_item,parent,false);
        return new TermsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermsAdapter.TermsViewHolder holder, int position) {
        if(mTerms !=null) {
            final Terms current = mTerms.get(position);
            holder.termItemView.setText(Integer.toString(current.getTermID()));
            holder.termItemView2.setText(current.getTermTitle());
        }
        else {
            holder.termItemView.setText("No term ID");
            holder.termItemView2.setText("No term title");
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
