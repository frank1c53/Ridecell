package com.organization.androidhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    Button signUp,login;
    EditText passText,emailText;
    JSONObject obj = new JSONObject();
    private ProgressDialog progressBar;
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
                emailValidator(emailText,passText);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,UserRegistration.class);
                startActivity(i);
            }
        });


    }

    // the function which triggered when the VALIDATE button is clicked
    // which validates the email address entered by the user
    public void emailValidator(EditText etMail,EditText pass)  {

        // extract the entered data from the EditText
        String emailToText = etMail.getText().toString();
        String passText = pass.getText().toString();

        // Android offers the inbuilt patterns which the entered
        // data from the EditText field needs to be compared with
        // In this case the the entered data needs to compared with
        // the EMAIL_ADDRESS, which is implemented same below
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show();
try {
    obj.put("email", emailToText);
    obj.put("password", passText);
    deciderState();
}catch (JSONException e){
    e.printStackTrace();
    Toast.makeText(MainActivity.this, "Register Error!" + e.toString(), Toast.LENGTH_SHORT).show();
}
        } else {
            Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
        }
    }





    public void deciderState(){

        Toast.makeText(this, "Decider State", Toast.LENGTH_SHORT).show();
        System.out.println(obj);
        //networkHandler.createRequest((NetworkHandler.NetworkManagerListener) this, requestParameters,Tags.Employee_Details);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.POST,
                Tags.User_login,obj,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.dismiss();
                        Log.d("Response", response.toString());
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setCancelable(true);
                        builder.setTitle("Success");
                        builder.setMessage(response.toString());
                        builder.show();
                        JSONArray value ;
                        JSONObject nameObject = null;
                        JSONObject Data =new JSONObject();




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Response Error", error.toString());
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setCancelable(true);
                        builder.setTitle("Login Failed");
                        builder.setMessage(error.toString());
                        builder.show();
                        JSONArray value ;
                        JSONObject nameObject = null;
                        JSONObject Data =new JSONObject();
                        progressBar.dismiss();
                    }
                }
        );
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(objectRequest);


        progressBar = ProgressDialog.show(MainActivity.this, "Please wait..", "Loading...");



    }
}



