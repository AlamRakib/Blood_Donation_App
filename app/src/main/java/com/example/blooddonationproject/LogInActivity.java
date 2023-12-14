package com.example.blooddonationproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LogInActivity extends AppCompatActivity {


    TextView signUp;
    EditText ed_email, ed_password;
    Button login;
    ProgressBar progressBar;

    TextView textDialog;

    AlertDialog alertDialog;

    public static String PRESS_NAME="MyPrefsFile";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        signUp = findViewById(R.id.noAccountTv);
        ed_email = findViewById(R.id.emailET);
        ed_password = findViewById(R.id.passwordET);
        progressBar = findViewById(R.id.progressBarId);


        login = findViewById(R.id.loginBtn);

        textDialog = findViewById(R.id.btnDialog);





        buildDialog();

        textDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.show();
            }
        });



        //===========This part has been using for signup
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
        //=============================================



        //==========This part has been working for logiN=====================================================================================================
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //skipActivity er kaj korerer jonno==============
                SharedPreferences sharedPreferences = getSharedPreferences(LogInActivity.PRESS_NAME,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("hasloogedIn",true);
                editor.commit();

                //startActivity(new Intent(getApplicationContext(),DonarDashboardActivity.class));
                //finish();

                String edEmail = ed_email.getText().toString();
                String edPassword = ed_password.getText().toString();

                if (edEmail.equals("")) {
                    Toast.makeText(LogInActivity.this, "Enter your email", Toast.LENGTH_SHORT).show();
                } else if (edPassword.equals("")) {
                    Toast.makeText(LogInActivity.this, "Enter your password", Toast.LENGTH_SHORT).show();
                } else {

                    String url = "https://rakibalam.000webhostapp.com/apps/BloodDonationProject/LogIn.php?e=" + edEmail + "&p=" + edPassword;

                    progressBar.setVisibility(View.VISIBLE);

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            progressBar.setVisibility(View.GONE);




                            if (response.contains("log in Successfully")) {

                                ed_email.setText("");
                                ed_password.setText("");

                                Intent intent = new Intent(LogInActivity.this, DonarDashboardActivity.class);
                                startActivity(intent);
                                finish();

                            } else {

                                Toast.makeText(LogInActivity.this, response, Toast.LENGTH_SHORT).show();
                            }


                        }


                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(LogInActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                        }
                    });


                    RequestQueue requestQueue = Volley.newRequestQueue(LogInActivity.this);
                    requestQueue.add(stringRequest);

                }


            }



        });

        //================================================================================================================================================================



    }



    private void buildDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
        View view = getLayoutInflater().inflate(R.layout.forgot_password_layout_dialog,null);



        builder.setView(view);
        builder.setTitle("Please Input New Email & Password also remain it")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                    }

                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });


        alertDialog = builder.create();

    }



}