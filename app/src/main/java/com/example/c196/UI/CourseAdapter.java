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

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Courses> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseTitleItemView;
        private final TextView courseStartItemView;
        private final TextView courseEndItemView;
        /*
        private final TextView courseStatusItemView;
        private final TextView courseInstructorNameItemView;
        private final TextView courseInstructorPhoneItemView;
        private final TextView courseInstructorEmailItemView;
         */

        // Constructor
        private CourseViewHolder(View itemView) {
            super(itemView);
            courseTitleItemView = itemView.findViewById(R.id.textViewCourseTitle);
            courseStartItemView = itemView.findViewById(R.id.textViewCourseStart);
            courseEndItemView = itemView.findViewById(R.id.textViewCourseEnd);
            /*
            courseStatusItemView = itemView.findViewById(R.id.textViewCourseStatus);
            courseInstructorNameItemView = itemView.findViewById(R.id.textViewCourseInstructorName);
            courseInstructorPhoneItemView = itemView.findViewById(R.id.textViewCourseInstructorPhone);
            courseInstructorEmailItemView = itemView.findViewById(R.id.textViewCourseInstructorEmail);
             */
            itemView.setOnClickListener(new View.OnClickListener() {
                //Sends it to the detailed course screen
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Courses current = mCourses.get(position);
                    Intent intent = new Intent(context, CourseDetailList.class);
                    intent.putExtra("courseID", current.getCourseID());
                    intent.putExtra("courseTitle", current.getCourseTitle());
                    intent.putExtra("courseStartDate", current.getCourseStartDate());
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

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if (mCourses != null) {
            final Courses current = mCourses.get(position);
            holder.courseTitleItemView.setText(current.getCourseTitle());
            holder.courseStartItemView.setText(current.getCourseStartDate());
            holder.courseEndItemView.setText(current.getCourseEndDate());
            /*
            holder.courseStatusItemView.setText(current.getCourseStatus());
            holder.courseInstructorNameItemView.setText(current.getInstructorName());
            holder.courseInstructorPhoneItemView.setText(current.getInstructorPhone());
            holder.courseInstructorEmailItemView.setText(current.getInstructorEmail());
             */
        } else {
            holder.courseTitleItemView.setText("No Course Title");
            holder.courseStartItemView.setText("No Course Start Date");
            holder.courseEndItemView.setText("No Course End Date");
            /*
            holder.courseStatusItemView.setText("No Course Status");
            holder.courseInstructorNameItemView.setText("No Instructor Name");
            holder.courseInstructorPhoneItemView.setText("No Instructor Phone");
            holder.courseInstructorEmailItemView.setText("No Instructor Email");
             */
        }
    }

    @Override
    public int getItemCount() {
        if (mCourses != null) {
            return mCourses.size();
        }
        return 0;
    }

    public void setCourses(List<Courses> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

}
