package com.example.blooddonationproject;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class AddDonarFragment extends Fragment {

    String[] blood;
    String[] divison;
    Spinner spinnerBlood,spinnerDivison;

    ImageButton backBtn;

    EditText userNameId,districtET,numberET;

    ProgressBar progressBarId;

    Button AddDonarButtonId;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_add_donar, container, false);


        spinnerBlood = myView.findViewById(R.id.spinnerId1);
        spinnerDivison = myView.findViewById(R.id.spinnerId2);

        backBtn = myView.findViewById(R.id.backBtn);
        userNameId = myView.findViewById(R.id.userNameId);
        numberET = myView.findViewById(R.id.numberET);
        progressBarId = myView.findViewById(R.id.progressBarId);
        AddDonarButtonId = myView.findViewById(R.id.AddDonarButtonId);
        districtET = myView.findViewById(R.id.districtNameET);




        blood = getResources().getStringArray(R.array.Blood);
        divison = getResources().getStringArray(R.array.Divison);


        // This part has been working for spineer
        ArrayAdapter<String> bloodAdapter = new ArrayAdapter<String>(getActivity(),R.layout.bloodspinner_view,R.id.textViewBloodId,blood);

        spinnerBlood.setAdapter(bloodAdapter);


        ArrayAdapter<String> divisonAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinnersampleview,R.id.textViewSampleId,divison);

        spinnerDivison.setAdapter(divisonAdapter);

        //-------------------------------------------------------------------


        AddDonarButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String edName = userNameId.getText().toString();
                String edNumber = numberET.getText().toString();
                String edDistrict = districtET.getText().toString();
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
                    Toast.makeText(getActivity(), "Enter  Name", Toast.LENGTH_SHORT).show();
                }else if (edNumber.equals("") == edNumber.length()>=11) {
                    numberET.setError("Enter 11 digit Mobile Number");
                    Toast.makeText(getActivity(), "Enter  Number", Toast.LENGTH_SHORT).show();
                }else if (edBloodSpinner.equals("")) {
                    Toast.makeText(getActivity(), "Enter Blood Group", Toast.LENGTH_SHORT).show();
                }else if (edDivisonSpinner.equals("")) {
                    Toast.makeText(getActivity(), "Enter Divison", Toast.LENGTH_SHORT).show();
                } else if (edDistrict.equals("")) {
                    Toast.makeText(getActivity(), "Enter Location ", Toast.LENGTH_SHORT).show();
                }
                else {


                    String url = "https://rakibalam.000webhostapp.com/apps/BloodDonationProject/AddDonarInsert.php?n=" + edName + "&m=" +edNumber+ "&b=" +edBloodSpinner+
                            "&d=" +edDivisonSpinner + "&district=" +edDistrict;

                    progressBarId.setVisibility(View.VISIBLE);

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            progressBarId.setVisibility(View.GONE);


                            new AlertDialog.Builder(getActivity())
                                    .setMessage(response)
                                    .show();


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            progressBarId.setVisibility(View.GONE);

                            Log.d("sever response",error.toString());
                            Toast.makeText(getActivity(),"Data successfully not inserted",Toast.LENGTH_SHORT).show();




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

        //======================================================



        return myView;
    }
}