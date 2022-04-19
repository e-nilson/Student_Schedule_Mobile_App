package com.example.c196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Entity.Assessments;
import com.example.c196.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    private List<Assessments> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentTitleItemView;
        private final TextView assessmentStartItemView;
        private final TextView assessmentEndItemView;
        //TODO private final TextView assessmentTypeItemView;

        private AssessmentViewHolder (View itemView) {
            super (itemView);
            assessmentTitleItemView = itemView.findViewById(R.id.textViewAssessmentTitle);
            assessmentStartItemView = itemView.findViewById(R.id.textViewAssessmentStart);
            assessmentEndItemView = itemView.findViewById(R.id.textViewAssessmentEnd);
            //TODO assessmentTypeItemView = itemView.findViewById(R.id.textViewAssessmentType);
            itemView.setOnClickListener(new View.OnClickListener() {

                // Sends it to the detailed assessment screen
                @Override
                public void onClick(View view) {
                   int position = getAdapterPosition();
                   final Assessments current = mAssessments.get(position);
                    Intent intent = new Intent(context, AssessmentDetailList.class);
                    //TODO intent.putExtra("assessmentID", current.getAssessmentID());
                    intent.putExtra("assessmentTitle", current.getAssessmentTitle());
                    intent.putExtra("assessmentStartDate", current.getAssessmentStartDate());
                    intent.putExtra("assessmentEndDate", current.getAssessmentEndDate());
                    intent.putExtra("assessmentType", current.getAssessmentType());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list,parent,false);
        return new AssessmentViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if(mAssessments !=null) {
            final Assessments current = mAssessments.get(position);
            //holder.assessmentIDItemView.setText(Integer.toString(current.getAssessmentID()));
            holder.assessmentTitleItemView.setText(current.getAssessmentTitle());
            holder.assessmentStartItemView.setText(current.getAssessmentStartDate());
            holder.assessmentEndItemView.setText(current.getAssessmentEndDate());
        }
        else {
            //holder.assessmentIDItemView.setText("No Assessment ID");
            holder.assessmentTitleItemView.setText("No Assessment Title");
            holder.assessmentStartItemView.setText("No Assessment Start Date");
            holder.assessmentEndItemView.setText("No Assessment End Date");
        }
    }


    @Override
    public int getItemCount() {
        if (mAssessments != null) {
            return mAssessments.size();
        }
        return 0;
    }


    public void setAssessments(List<Assessments> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }


    public AssessmentAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

}
