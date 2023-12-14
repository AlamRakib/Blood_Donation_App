package com.example.blooddonationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class UserPostActivity extends AppCompatActivity {

    String[] blood;
    String[] divison;
    Spinner spinnerBlood,spinnerDivison;


    EditText fullName,number,date,hospital_name,details;

    Button Post;

    ProgressBar progressBarId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_post);




        spinnerBlood = findViewById(R.id.spinnerId1);
        spinnerDivison = findViewById(R.id.spinnerId2);

        fullName = findViewById(R.id.fullNameETId);
        number = findViewById(R.id.numberETId);
        date = findViewById(R.id.dateETId);
        hospital_name = findViewById(R.id.hospitalETId);
        details = findViewById(R.id.detailsETId);

        Post = findViewById(R.id.postButtonId);

        progressBarId = findViewById(R.id.progressBarId);


        blood = getResources().getStringArray(R.array.Blood);
        divison = getResources().getStringArray(R.array.Divison);

        // This part has been working for spineer
        ArrayAdapter<String> bloodAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.bloodspinner_view,R.id.textViewBloodId,blood);

        spinnerBlood.setAdapter(bloodAdapter);


        ArrayAdapter<String> divisonAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinnersampleview,R.id.textViewSampleId,divison);

        spinnerDivison.setAdapter(divisonAdapter);

        //-------------------------------------------------------------------











        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String edName = fullName.getText().toString();
                String edNumber = number.getText().toString();
                String edDate = date.getText().toString();
                String edHospital_Name = hospital_name.getText().toString();
                String edDetails = details.getText().toString();

                String edBloodSpinner = spinnerBlood.getSelectedItem().toString();
                String edDivisonSpinner = spinnerDivison.getSelectedItem().toString();




                //========This part has been using for blood er plus & minus sign database a rakhar jonno
                try {
                    edBloodSpinner = URLEncoder.encode(edBloodSpinner, "UTF-8");

                } catch (UnsupportedEncodingException e) {

                    e.printStackTrace();
                }

                //======================================

                if (edName.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter Full Name", Toast.LENGTH_SHORT).show();
                } else if (edDate.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter Date", Toast.LENGTH_SHORT).show();
                }else if (edHospital_Name.equals("")) {
                    Toast.makeText(getApplicationContext(), "Email Hospital", Toast.LENGTH_SHORT).show();
                } else if (edDetails.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter Details", Toast.LENGTH_SHORT).show();
                }else if (edNumber.equals("")== edNumber.length()>=11) {
                    number.setError("Enter 11 digit Mobile Number");
                    Toast.makeText(getApplicationContext(), "Enter Number", Toast.LENGTH_SHORT).show();
                }else if (edBloodSpinner.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter your Blood Group", Toast.LENGTH_SHORT).show();
                }else if (edDivisonSpinner.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter your Divison", Toast.LENGTH_SHORT).show();
                }
                else {

                    String url = "https://rakibalam.000webhostapp.com/apps/BloodDonationProject/PostInsert.php?n=" +edName + "&bg=" +edBloodSpinner + "&d=" +edDivisonSpinner+
                            "&m=" +edNumber+ "&date=" +edDate+  "&hn=" +edHospital_Name+ "&details=" +edDetails;

                    progressBarId.setVisibility(View.VISIBLE);

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            progressBarId.setVisibility(View.GONE);

                            new AlertDialog.Builder(getApplicationContext())
                                    .setMessage(response)
                                    .show();


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            progressBarId.setVisibility(View.GONE);

                            Log.d("sever response",error.toString());
                            Toast.makeText(getApplicationContext(),"Data successfully not inserted",Toast.LENGTH_SHORT).show();




                            //==========This part has been using for internet connection check
                            ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


                            if (networkInfo != null && networkInfo.isConnected()) {


                            }
                            else{
                                Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();

                                new AlertDialog.Builder(getApplicationContext())
                                        .setTitle("No internet")
                                        .setMessage("please connect internet")
                                        .show();

                            }

                            //=======================================================


                        }


                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);


                }

            }
        });









    }
}