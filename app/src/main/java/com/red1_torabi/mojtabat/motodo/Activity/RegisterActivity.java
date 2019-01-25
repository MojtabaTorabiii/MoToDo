package com.red1_torabi.mojtabat.motodo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.red1_torabi.mojtabat.motodo.R;
import com.red1_torabi.mojtabat.motodo.helpers.InputValidation;
import com.red1_torabi.mojtabat.objectsrepos.Message;
import com.red1_torabi.mojtabat.objectsrepos.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

//import com.red1_torabi.mojtabat.motodo.Model.User;
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = RegisterActivity.this;
    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutUserName;
    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextUserName;
    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;
    private InputValidation inputValidation;
    private TextInputEditText textInputEditTextFamilyName;
    private User user;

    private RadioGroup radioUserTypeGroup;
    private RadioButton radioGoldButton;
    private RadioButton radioSilverButton;
    private RadioButton radioBronzeButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.textInputLayoutUserName);
        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextUserName = (TextInputEditText) findViewById(R.id.textInputEditTextUserName);
        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);
        textInputEditTextFamilyName = (TextInputEditText) findViewById(R.id.textInputEditTextFamilyName);
        radioGoldButton = (RadioButton) findViewById(R.id.Gold);
        radioSilverButton = (RadioButton) findViewById(R.id.Silver);
        radioBronzeButton = (RadioButton) findViewById(R.id.Bronze);
        radioUserTypeGroup = (RadioGroup)findViewById(R.id.UserType);
    }
    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
    }
    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        //user = new User();
    }
    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonRegister:
                new Thread(new SendDataToServer()).start();
                //Intent intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intentMainActivity);
                //sendtd();
                break;
            case R.id.appCompatTextViewLoginLink:
                Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intentLogin);
                //finish();
                break;
        }
    }
    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextUserName.setText(null);
    }
private void sendtd(){
    try {
                /*//Scanner scn = new Scanner(System.in);
                // getting localhost ip
                InetAddress ip = InetAddress.getByName("10.0.2.2");
                // establish the connection with server port 5056
                Socket s = new Socket(ip, 8090);
                // obtaining input and out streams
                //DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                // the following loop performs the exchange of
                // information between client and client handler
                //while (true) {
                    //System.out.println(dis.readUTF());
                    String tosend = textInputLayoutEmail.getEditText().getText().toString();
                    tosend += ","+ textInputEditTextUserName.getText().toString();
                    tosend += ","+ textInputEditTextName.getText().toString();
                    tosend += ","+ textInputEditTextFamilyName.getText().toString();
                    tosend += ","+textInputEditTextPassword.getText().toString();
                    dos.writeUTF(tosend);
                    // If client sends exit,close this connection
                    // and then break from the while loop
                    if (tosend.equals("Exit")) {
                        System.out.println("Closing this connection : " + s);
                        s.close();
                        System.out.println("Connection closed");
                        //break;
                    }
                    // printing date or time as requested by client
                    //String received = dis.readUTF();
                    //System.out.println(received);
                //}
                // closing resources
                //scn.close();
                //dis.close();
                dos.close();*/
        System.out.println("welcome client");
        Socket socket = new Socket("10.0.2.2", 8090);
        System.out.println("Client connected");
        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Ok");
        int messageType = 0;

        String userType = "";
        switch (radioUserTypeGroup.getCheckedRadioButtonId()) {
            case R.id.Gold:
                userType = "Gold";
                break;
            case R.id.Silver:
                userType = "Silver";
                break;
            case R.id.Bronze:
                userType = "Bronze";
                break;
        }
        User user = new User(0, textInputEditTextName.getText().toString(), textInputEditTextFamilyName.getText().toString(),
                textInputEditTextUserName.getText().toString(), textInputLayoutEmail.getEditText().getText().toString(),
                textInputEditTextPassword.getText().toString(), userType, false);
        Message message = new Message(user, null, 0, 1);
        os.writeObject(message);
        os.flush();
        //message.setMessageType(4);
        ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
        Message returnMessage =(Message) is.readObject();
        Intent intentMainActivity;
        switch (returnMessage.getMessageType()) {
            case 4:
                textInputEditTextUserName.setError("User Name is already exists");

                break;
            case 5:
                textInputLayoutEmail.setError("Email is already exists");
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
    class SendDataToServer implements Runnable {
        @Override
        public void run() {
            //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            //StrictMode.setThreadPolicy(policy);
            try {
                /*//Scanner scn = new Scanner(System.in);
                // getting localhost ip
                InetAddress ip = InetAddress.getByName("10.0.2.2");
                // establish the connection with server port 5056
                Socket s = new Socket(ip, 8090);
                // obtaining input and out streams
                //DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                // the following loop performs the exchange of
                // information between client and client handler
                //while (true) {
                    //System.out.println(dis.readUTF());
                    String tosend = textInputLayoutEmail.getEditText().getText().toString();
                    tosend += ","+ textInputEditTextUserName.getText().toString();
                    tosend += ","+ textInputEditTextName.getText().toString();
                    tosend += ","+ textInputEditTextFamilyName.getText().toString();
                    tosend += ","+textInputEditTextPassword.getText().toString();
                    dos.writeUTF(tosend);
                    // If client sends exit,close this connection
                    // and then break from the while loop
                    if (tosend.equals("Exit")) {
                        System.out.println("Closing this connection : " + s);
                        s.close();
                        System.out.println("Connection closed");
                        //break;
                    }
                    // printing date or time as requested by client
                    //String received = dis.readUTF();
                    //System.out.println(received);
                //}
                // closing resources
                //scn.close();
                //dis.close();
                dos.close();*/
                System.out.println("welcome client");
                Socket socket = new Socket("10.0.2.2", 8090);
                System.out.println("Client connected");
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("Ok");
                int messageType = 0;

                String userType = "";
                switch (radioUserTypeGroup.getCheckedRadioButtonId()) {
                    case R.id.Gold:
                        userType = "Gold";
                        break;
                    case R.id.Silver:
                        userType = "Silver";
                        break;
                    case R.id.Bronze:
                        userType = "Bronze";
                        break;
                }
                User user = new User(0, textInputEditTextName.getText().toString(), textInputEditTextFamilyName.getText().toString(),
                        textInputEditTextUserName.getText().toString(), textInputLayoutEmail.getEditText().getText().toString(),
                        textInputEditTextPassword.getText().toString(), userType, false);
                Message message = new Message(user, null, 0, 1);
                os.writeObject(message);
                os.flush();
                //message.setMessageType(4);
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                Message returnMessage =(Message) is.readObject();
                Intent intentMainActivity;
                switch (returnMessage.getMessageType()) {
                    case 4:
                        textInputEditTextUserName.setError("User Name is already exists");

                        break;
                    case 5:
                        textInputLayoutEmail.setError("Email is already exists");
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
}




