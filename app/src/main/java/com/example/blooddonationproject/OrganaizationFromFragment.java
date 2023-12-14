package com.example.blooddonationproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class OrganaizationFromFragment extends Fragment {


    EditText editText1, editText2, editText3;

    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_organaization_from, container, false);


        editText1 = myView.findViewById(R.id.ORGNameID);
        editText2 = myView.findViewById(R.id.districtID);
        editText3 = myView.findViewById(R.id.numberID);
        button = myView.findViewById(R.id.AddButtonId);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String edORGName = editText1.getText().toString();
                String edDistrict = editText2.getText().toString();
                String edNumber = editText3.getText().toString();

                if (edORGName.equals("")) {
                    Toast.makeText(getActivity(), "Enter ORG Name", Toast.LENGTH_SHORT).show();
                } else if (edDistrict.equals("")) {
                    Toast.makeText(getActivity(), "Enter District", Toast.LENGTH_SHORT).show();
                } else if (edNumber.equals("") == edNumber.length() >= 11) {
                    editText3.setError("Enter 11 digit Mobile Number");
                    Toast.makeText(getActivity(), "Email Number", Toast.LENGTH_SHORT).show();
                } else {


                    String url = "https://rakibalam.000webhostapp.com/apps/BloodDonationProject/ORGInsert.php?n=" + edORGName + "&d=" + edDistrict + "&c=" + edNumber;

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            new AlertDialog.Builder(getActivity())
                                    .setMessage(response)
                                    .show();


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                            //==========This part has been using for internet connection check
                            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

                            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


                            if (networkInfo != null && networkInfo.isConnected()) {


                            }
                            else{
                                Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();

                                new AlertDialog.Builder(getActivity())
                                        .setTitle("No internet")
                                        .setMessage("please connect internet")
                                        .show();

                            }

                            //=======================================================

                        }
                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                    requestQueue.add(stringRequest);

                }


            }
        });





        return myView;
    }







}