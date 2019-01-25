package com.red1_torabi.mojtabat.motodo.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.red1_torabi.mojtabat.motodo.R;
import com.red1_torabi.mojtabat.motodo.helpers.InputValidation;
import com.red1_torabi.mojtabat.objectsrepos.Message;
import com.red1_torabi.mojtabat.objectsrepos.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    //private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {

        inputValidation = new InputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                new Thread(new LoginActivity.SendDataToServer()).start();
                //Intent intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intentMainActivity);
                //Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intentMain);
                break;
            case R.id.textViewLinkRegister:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    class SendDataToServer implements Runnable {
        @Override
        public void run() {
            try {

                Socket socket = new Socket("10.0.2.2", 8090);

                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

                int messageType = 0;
                User user = new User(0, null, null,
                        null, textInputLayoutEmail.getEditText().getText().toString(),
                        textInputEditTextPassword.getText().toString(), null, false);
                Message message = new Message(user, null, 0, 0);
                os.writeObject(message);
                os.flush();
                //message.setMessageType(4);
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                Message returnMessage = (Message) is.readObject();
                Intent intentMainActivity;
                switch (returnMessage.getMessageType()) {
                    case 1:
                        intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intentMainActivity);
                        break;
                    case 4:
                        textInputEditTextEmail.setError("User password not correct.");

                        break;
                    case 5:
                        textInputLayoutEmail.setError("User not found.");
                        break;
                    default:
                        if (returnMessage.getMessageType() < 4) {
                            intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intentMainActivity);
                        }
                        break;
                }
                System.out.println("return Message is=" + returnMessage);
                //socket.close();
            } catch (UnknownHostException e1) {

                e1.printStackTrace();

            } catch (IOException e1) {

                e1.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }/**/ catch (Exception exce) {
                exce.printStackTrace();
            }

        }
    }
    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }



    private void sendtd(){
        try {

            Socket socket = new Socket("10.0.2.2", 8090);

            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

            int messageType = 0;
            User user = new User(0, null, null,
                    null, textInputLayoutEmail.getEditText().getText().toString(),
                    textInputEditTextPassword.getText().toString(), null, false);
            Message message = new Message(user, null, 0, 0);
            os.writeObject(message);
            os.flush();
            //message.setMessageType(4);
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            Message returnMessage =(Message) is.readObject();
            Intent intentMainActivity;
            switch (returnMessage.getMessageType()) {
                case 1:
                    intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intentMainActivity);
                    break;
                case 4:
                    textInputEditTextEmail.setError("User password not correct.");

                    break;
                case 5:
                    textInputLayoutEmail.setError("User not found.");
                    break;
                default:
                    if (returnMessage.getMessageType() < 4) {
                        intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intentMainActivity);
                    }
                    break;
            }
            System.out.println("return Message is=" + returnMessage);
            //socket.close();
        } catch (UnknownHostException e1) {

            e1.printStackTrace();

        } catch (IOException e1) {

            e1.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }/**/

        catch (Exception exce) {
            exce.printStackTrace();
        }
    }







}


