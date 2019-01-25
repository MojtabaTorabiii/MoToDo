package com.red1_torabi.mojtabat.motodo.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import com.red1_torabi.mojtabat.motodo.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class NewTodo extends AppCompatActivity {

    private EditText _title;
    private EditText _desc;
    private Button cancel_button;
    private Button create_button;
    private Button date_button;
    private String date_time;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);
        TimeZone.setDefault(TimeZone.getTimeZone("Paris"));

        _title = (EditText) findViewById(R.id.fieldTitle);
        _desc = (EditText) findViewById(R.id.fieldDesc);

        cancel_button = (Button) findViewById(R.id.cancelCreateButton);
        create_button = (Button) findViewById(R.id.createButton);
        date_button = (Button) findViewById(R.id.dateButton);

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Paris"));
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Init button at current time()
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
        date_button.setText(sdf.format(new Date()));

        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //to add tasks in file and sort them

        // updateCreateButtonState();
        //  updateDateButton();
    }

    private void datePicker() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (view.isShown()) {
                            mYear = year;
                            mMonth = monthOfYear;
                            mDay = dayOfMonth;

                            //   updateDateButton();
                            timePicker();
                        }
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.setTitle("Select a day");
        datePickerDialog.show();
    }


    private void timePicker() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        if (view.isShown()) {
                            mHour = hourOfDay;
                            mMinute = minute;

                            //    updateDateButton();
                        }
                    }
                }, mHour, mMinute, false);
        timePickerDialog.setTitle("Select an hour");
        timePickerDialog.show();
    }

}