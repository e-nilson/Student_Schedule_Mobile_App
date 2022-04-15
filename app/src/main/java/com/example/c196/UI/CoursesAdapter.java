package com.example.c196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Entity.Courses;
import com.example.c196.R;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseTitleItemView;
        private final TextView courseStartItemView;
        private final TextView courseEndItemView;


        private CourseViewHolder(View itemView) {
            super(itemView);
            courseTitleItemView = itemView.findViewById(R.id.textView6);
            courseStartItemView = itemView.findViewById(R.id.textView7);
            courseEndItemView = itemView.findViewById(R.id.textView8);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Courses current = mCourses.get(position);
                    Intent intent = new Intent(context, AssessmentsList.class);
                    intent.putExtra("courseID", current.getCourseID());
                    intent.putExtra("courseTitle", current.getCourseTitle());
                    intent.putExtra("coursestartDate", current.getCourseStartDate());
                    intent.putExtra("courseEndDate", current.getCourseEndDate());
                    intent.putExtra("courseStatus", current.getCourseStatus());
                    intent.putExtra("instructorName", current.getInstructorName());
                    intent.putExtra("instructorPhone", current.getInstructorPhone());
                    intent.putExtra("instructorEmail", current.getInstructorEmail());
                    intent.putExtra("termID", current.getTermID());
                    context.startActivity(intent);
                }
            });

        }
    }

    private List<Courses> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    public CoursesAdapter (Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public CoursesAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.courses_list, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.CourseViewHolder holder, int position) {
        if(mCourses != null) {
            final Courses current = mCourses.get(position);
            holder.courseTitleItemView.setText(current.getCourseTitle());
            holder.courseStartItemView.setText(current.getCourseStartDate());
            holder.courseEndItemView.setText(current.getCourseEndDate());
        }
        else {
            holder.courseTitleItemView.setText("No Course Title");
            holder.courseStartItemView.setText("No Course Start Date");
            holder.courseEndItemView.setText("No Course End Date");
        }
    }

    public void setCourses(List<Courses> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
