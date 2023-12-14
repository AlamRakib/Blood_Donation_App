package com.example.blooddonationproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {

    String[] blood;
    String[] divison;
    Spinner spinnerBlood,spinnerDivison;

    ImageButton backBtn;

    EditText userNameId,emailET,passwordET,numberET,districtET;

    ProgressBar progressBarId;

    Button RegisterButtonId;

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinnerBlood = findViewById(R.id.spinnerId1);
        spinnerDivison = findViewById(R.id.spinnerId2);

        //backBtn = findViewById(R.id.backBtn);
        userNameId = findViewById(R.id.userNameId);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        numberET = findViewById(R.id.numberET);
        districtET = findViewById(R.id.districtEditTextId);
        progressBarId = findViewById(R.id.progressBarId);
        RegisterButtonId = findViewById(R.id.RegisterButtonId);




        blood = getResources().getStringArray(R.array.Blood);
        divison = getResources().getStringArray(R.array.Divison);


        // This part has been working for spineer
        ArrayAdapter<String> bloodAdapter = new ArrayAdapter<String>(this,R.layout.bloodspinner_view,R.id.textViewBloodId,blood);

        spinnerBlood.setAdapter(bloodAdapter);


        ArrayAdapter<String> divisonAdapter = new ArrayAdapter<String>(this,R.layout.spinnersampleview,R.id.textViewSampleId,divison);

        spinnerDivison.setAdapter(divisonAdapter);

        //-------------------------------------------------------------------


        sharedPreferences = getSharedPreferences(""+getString(R.string.app_name),MODE_PRIVATE);

        SharedPreferences.Editor  editor = sharedPreferences.edit();  //=======editor kaj hocce save rakha






        //===================Registration=================================
        RegisterButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String edName = userNameId.getText().toString();
                editor.putString("name",""+edName); //Aiikhane ekta hocce Key arek ta hocce Value ,,,name  ekta column ja kolponay save kora ache
                editor.apply();

                String edEmail = emailET.getText().toString();
                editor.putString("email",""+edEmail);
                editor.apply();

                String edPassword = passwordET.getText().toString();
                editor.putString("password",""+edPassword);
                editor.apply();

                String edNumber = numberET.getText().toString();
                editor.putString("number",""+edNumber);
                editor.apply();

                String edBloodSpinner = spinnerBlood.getSelectedItem().toString();
                editor.putString("bg",""+edBloodSpinner);
                editor.apply();

                String edDivisonSpinner = spinnerDivison.getSelectedItem().toString();
                editor.putString("divison",""+edDivisonSpinner);
                editor.apply();

                String edDistrict = districtET.getText().toString();
                editor.putString("district",""+edDistrict);
                editor.apply();


                //========This part has been using for blood er plus & minus sign database a rakhar jonno
                try {
                    edBloodSpinner = URLEncoder.encode(edBloodSpinner, "UTF-8");

                } catch (UnsupportedEncodingException e) {

                    e.printStackTrace();
                }

                //======================================


                validateEmailAddress(emailET);//================ValidateEmailAddress function ke call kora hoise


                if (edName.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter your Name", Toast.LENGTH_SHORT).show();
                } else if (edEmail.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter your Email", Toast.LENGTH_SHORT).show();
                }else if (!Patterns.EMAIL_ADDRESS.matcher(edEmail).matches()) {
                    Toast.makeText(RegisterActivity.this, "Plz Add Valid Email Address", Toast.LENGTH_SHORT).show();
                } else if (edPassword.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter your Password", Toast.LENGTH_SHORT).show();
                }else if (edNumber.equals("") == edNumber.length()>=11) {
                    numberET.setError("Enter 11 digit Mobile Number");
                    Toast.makeText(RegisterActivity.this, "Enter your Number ", Toast.LENGTH_SHORT).show();
                }else if (edBloodSpinner.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter your Blood Group", Toast.LENGTH_SHORT).show();
                }else if (edDivisonSpinner.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter your Divison", Toast.LENGTH_SHORT).show();
                }else if (edDistrict.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter your District", Toast.LENGTH_SHORT).show();
                }
                else {


                    String url = "https://rakibalam.000webhostapp.com/apps/BloodDonationProject/AddDonarInsert.php?n=" + edName + "&e=" + edEmail + "&p=" + edPassword +
                            "&m=" +edNumber+ "&b=" +edBloodSpinner+ "&d=" +edDivisonSpinner + "&district=" +edDistrict;

                    progressBarId.setVisibility(View.VISIBLE);

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            progressBarId.setVisibility(View.GONE);


                            new AlertDialog.Builder(RegisterActivity.this)
                                    .setMessage(response)
                                    .show();


                            if (response.contains("Successfully Done")) {

                                Intent intent = new Intent(RegisterActivity.this, LogInActivity.class);
                                startActivity(intent);
                                finish();

                            } else {

                                Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            progressBarId.setVisibility(View.GONE);

                            Log.d("sever response",error.toString());
                            Toast.makeText(RegisterActivity.this,"Data successfully not inserted",Toast.LENGTH_SHORT).show();




                            //==========This part has been using for internet connection check
                            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


                            if (networkInfo != null && networkInfo.isConnected()) {


                            }
                            else{
                                Toast.makeText(RegisterActivity.this,"",Toast.LENGTH_SHORT).show();

                                new AlertDialog.Builder(RegisterActivity.this)
                                        .setTitle("No internet")
                                        .setMessage("please connect internet")
                                        .show();

                            }

                            //=======================================================



                        }
                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                    requestQueue.add(stringRequest);


                }


            }

        });

        //======================================================



    }



    //=============This part has been using for Email Validity Check

    private boolean validateEmailAddress(EditText emailET){


        String edEmail = emailET.getText().toString();
        if(!Patterns.EMAIL_ADDRESS.matcher(edEmail).matches())
        {
            Toast.makeText(this,"Email Validated Successfully",Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    //===============================================================






}