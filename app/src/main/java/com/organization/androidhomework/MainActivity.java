package com.organization.androidhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button signUp,login;
    EditText passText,emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUp = findViewById(R.id.signup);
        signUp.setPaintFlags(signUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        login = findViewById(R.id.login);
        emailText = findViewById(R.id.emailtext);
        passText = findViewById(R.id.passtext);

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                emailValidator(emailText);
            }
        });


    }

    // the function which triggered when the VALIDATE button is clicked
    // which validates the email address entered by the user
    public void emailValidator(EditText etMail) {

        // extract the entered data from the EditText
        String emailToText = etMail.getText().toString();

        // Android offers the inbuilt patterns which the entered
        // data from the EditText field needs to be compared with
        // In this case the the entered data needs to compared with
        // the EMAIL_ADDRESS, which is implemented same below
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
        }
    }
}




