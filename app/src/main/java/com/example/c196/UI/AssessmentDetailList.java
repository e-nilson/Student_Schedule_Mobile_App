package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Courses;
import com.example.c196.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AssessmentDetailList extends AppCompatActivity {

    private Repository repository;
    private int assessmentID;
    private int termID;
    private int courseID;

    Assessments currentAssessment;
    Courses courses;
    RecyclerView recyclerView;
    List<Assessments> allAssessments;
    List<Courses> allCourses;

    EditText editTitle;
    EditText editStartDate;
    EditText editEndDate;
    EditText editType;

    DatePickerDialog.OnDateSetListener assessmentStartDate;
    DatePickerDialog.OnDateSetListener assessmentEndDate;
    final Calendar myCalendarStart = Calendar.getInstance();

    String dateFormat;
    SimpleDateFormat sdf;

    // Initializes the Assessment Details homepage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dateFormat = "MM/dd/yyyy";
        sdf = new SimpleDateFormat(dateFormat, Locale.US);
        editStartDate = findViewById(R.id.editAssessmentStartDate);
        editStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = editStartDate.getText().toString();
                if (info.equals("")) info = "05/01/2022";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AssessmentDetailList.this, assessmentStartDate,
                        myCalendarStart.get(Calendar.YEAR),
                        myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        assessmentStartDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();
            }
        };

        editEndDate = findViewById(R.id.editAssessmentEndDate);
        editEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = editEndDate.getText().toString();
                if (info.equals("")) info = "05/01/2022";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AssessmentDetailList.this, assessmentEndDate,
                        myCalendarStart.get(Calendar.YEAR),
                        myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        assessmentEndDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelEnd();
            }
        };

        // methods for entering and saving assessments from assessment details page back to assessments page
        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        repository = new Repository(getApplication());
        allAssessments = repository.getAllAssessments();
        for (Assessments a:allAssessments){
            if (a.getAssessmentID()== assessmentID) currentAssessment = a;
        }
        editType = findViewById(R.id.editAssessmentType);
        editTitle = findViewById(R.id.editAssessmentTitle);
        editStartDate = findViewById(R.id.editAssessmentStartDate);
        editEndDate = findViewById(R.id.editAssessmentEndDate);

        if (currentAssessment != null) {
            editType.setText(currentAssessment.getAssessmentType());
            editTitle.setText(currentAssessment.getAssessmentTitle());
            editStartDate.setText(currentAssessment.getAssessmentStartDate());
            editEndDate.setText(currentAssessment.getAssessmentEndDate());
        }
    }

    private void updateLabelStart() {
        editStartDate.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void updateLabelEnd() {
        editEndDate.setText(sdf.format(myCalendarStart.getTime()));
    }


    // Creates a menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // This adds items to the action bar if it's present
        getMenuInflater().inflate(R.menu.menu_assessment_detail, menu);
        return true;
    }

    // Tells what happens with the created menu functions
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent homeIntent = new Intent(AssessmentDetailList.this, MainActivity.class);
                startActivity(homeIntent);
                return true;

            case R.id.notifyAssessmentStart:
                String startDateFromScreen = editStartDate.getText().toString();
                Date myStartDate = null;
                try{
                    myStartDate = sdf.parse(startDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long startTrigger = myStartDate.getTime();
                Intent startIntent = new Intent(AssessmentDetailList.this, MyReceiver.class);
                startIntent.putExtra("key", editTitle.getText().toString() + " starts on" +": "+ editStartDate.getText().toString());
                PendingIntent startSender = PendingIntent.getBroadcast(AssessmentDetailList.this, MainActivity.numAlert++, startIntent,0);
                AlarmManager startAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                startAlarmManager.set(AlarmManager.RTC_WAKEUP, startTrigger, startSender);
                return true;

            case R.id.notifyAssessmentEnd:
                String endDateFromScreen = editEndDate.getText().toString();
                Date myEndDate = null;
                try {
                    myEndDate = sdf.parse(endDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long endTrigger = myEndDate.getTime();
                Intent endIntent = new Intent(AssessmentDetailList.this, MyReceiver.class);
                endIntent.putExtra("key", editTitle.getText().toString() + " ends on" +": "+ editEndDate.getText().toString());
                PendingIntent endSender = PendingIntent.getBroadcast(AssessmentDetailList.this, MainActivity.numAlert++, endIntent,0);
                AlarmManager endAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                endAlarmManager.set(AlarmManager.RTC_WAKEUP, endTrigger, endSender);
                return true;

            case R.id.deleteAssessmentDetail:
                repository.delete(currentAssessment);
                Toast.makeText(getApplicationContext(), "Assessment deleted successfully", Toast.LENGTH_LONG).show();
                Intent deleteIntent = new Intent(AssessmentDetailList.this, AssessmentList.class);
                startActivity(deleteIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Saves new assessments and/or assessment details
    public void saveAssessmentDetails(View view) {
        Assessments assessments;
        if (assessmentID == -1) {
            courseID = 0;
            for (Assessments assessment : repository.getAllAssessments()) {
                if (assessment.getCourseID() > courseID);
                courseID = ++courseID;
            }
            int newAssessmentID = repository.getAllAssessments().get(repository.getAllAssessments().size() - 1).getAssessmentID() + 1;
            assessments = new Assessments(newAssessmentID, editType.getText().toString(), editTitle.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString(), courseID);
            repository.insert(assessments);
        } else {
            assessments = new Assessments(assessmentID, editType.getText().toString(), editTitle.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString(), courseID);
            repository.update(assessments);
        }
        Intent intent = new Intent(AssessmentDetailList.this, AssessmentList.class);
        startActivity(intent);
    }

}