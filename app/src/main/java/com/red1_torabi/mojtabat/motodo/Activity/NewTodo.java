package com.red1_torabi.mojtabat.motodo.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.red1_torabi.mojtabat.motodo.R;
import com.red1_torabi.mojtabat.objectsrepos.Message;
import com.red1_torabi.mojtabat.objectsrepos.Todo;
import com.red1_torabi.mojtabat.objectsrepos.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class NewTodo extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = NewTodo.this;
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
    private Message messageReceived;
    private Spinner prioritySpinner;
    private Todo todo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);
        TimeZone.setDefault(TimeZone.getTimeZone("Tehran"));
        messageReceived = (Message) getIntent().getSerializableExtra("com.red1_torabi.mojtabat.objectsrepos.Message");
        _title = (EditText) findViewById(R.id.fieldTitle);
        _desc = (EditText) findViewById(R.id.fieldDesc);

        cancel_button = (Button) findViewById(R.id.cancelCreateButton);
        create_button = (Button) findViewById(R.id.createButton);
        date_button = (Button) findViewById(R.id.dateButton);
        prioritySpinner = (Spinner) findViewById(R.id.spinner);
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Tehran"));
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Init button at current time()
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
        date_button.setText(sdf.format(new Date()));




        this.todo = new Todo();


        //to add tasks in file and sort them

        // updateCreateButtonState();
        //  updateDateButton();
        initViews();
        initListeners();
        updateCreateButtonState();
        //updateDateButton();
    }

    private void initViews() {
        create_button = (Button) findViewById(R.id.createButton);
        cancel_button = (Button) findViewById(R.id.cancelCreateButton);
    }

    private void initListeners() {
        cancel_button.setOnClickListener(this);
        create_button.setOnClickListener(this);
        date_button.setOnClickListener(this);
    }

    //@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createButton:
                new Thread(new NewTodo.SendDataToServer()).start();
                //Intent intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intentMainActivity);
                //sendtd();
                break;
            case R.id.cancelCreateButton:
                finish();
                break;
            case R.id.dateButton:
                datePicker();
                break;

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                updateCreateButtonState();
            }
        };

        _title.addTextChangedListener(tw);
        _desc.addTextChangedListener(tw);
    }

    String CustomDateFactory(String expectedPattern, String outputPattern, String input) {

        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(outputPattern);
            Date date = formatter.parse(input);
            String output = sdf.format(date);
            return (output);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return (null);
    }



    void updateDateButton() {

        // Build date string from Custom dialog
        date_time =  mDay + "/" +(mMonth + 1)  + "/" + mYear + " "
                + mHour + ":" + mMinute;

        String toUpdate;
        toUpdate = CustomDateFactory("dd/MM/yyyy H:mm",
                "EEE, d MMM yyyy HH:mm",
                date_time);

        date_button.setText(toUpdate);
    }

    void updateCreateButtonState() {
        create_button.setEnabled(!TextUtils.isEmpty(_title.getText()));
        if(TextUtils.isEmpty(_title.getText()))
        create_button.setBackgroundColor(Color.GRAY);
        else
            create_button.setBackgroundColor(Color.rgb(1,163,163));
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

                            updateDateButton();
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

                            updateDateButton();
                        }
                    }
                }, mHour, mMinute, false);
        timePickerDialog.setTitle("Select an hour");
        timePickerDialog.show();
    }





    class SendDataToServer implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("welcome client");
                //Socket socket = new Socket("10.0.2.2", 8090);
                System.out.println("Client connected");
                //ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("Ok");
                int messageType = 0;
                User user = messageReceived.getUser();
                Message message = new Message(user, null, 0, 1);
                //os.writeObject(message);
                //os.flush();
                //message.setMessageType(4);
                //ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                //Message returnMessage =(Message) is.readObject();
                //Intent intentMainActivity;
                long selected = prioritySpinner.getSelectedItemId();
                switch ((int)prioritySpinner.getSelectedItemId()) {
                    case 4:
                    //    textInputEditTextUserName.setError("User Name is already exists");

                        break;
                    case 5:
                      //  textInputLayoutEmail.setError("Email is already exists");
                        break;
                    default:
                        /*if (returnMessage.getMessageType() < 4) {
                            intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);

                            startActivity(intentMainActivity);
                        }*/
                        break;
                }
                System.out.println("return Message is=" + messageReceived);
                //socket.close();
            } /*catch (UnknownHostException e1) {

                e1.printStackTrace();

            } catch (IOException e1) {

                e1.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }*/

            catch (Exception exce) {
                exce.printStackTrace();
            }
        }
    }

}



