package com.example.blooddonationproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserStatusFeedActivity extends AppCompatActivity {


    ProgressBar progressBar;

    ListView listView;

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_status_feed);


        progressBar = findViewById(R.id.progressBarId);
        listView = findViewById(R.id.listViewId);
        swipeRefreshLayout = findViewById(R.id.swifeRefreshLayout2);


        loadData();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefreshLayout.setRefreshing(false);
                loadData();

                Toast.makeText(getApplicationContext(), "Getting Data Ready", Toast.LENGTH_SHORT).show();

            }
        });


    }


    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int Position, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.user_post_sample_view, viewGroup, false);

            TextView nameTextView = myView.findViewById(R.id.nameTextViewId);
            TextView bloodTextView = myView.findViewById(R.id.bloodTextViewId);
            TextView divisonTextView = myView.findViewById(R.id.divisonTextViewId);
            TextView mobileTextView = myView.findViewById(R.id.numberTextViewId);
            TextView dateTextView = myView.findViewById(R.id.dateTextViewId);
            TextView hospitalTextView = myView.findViewById(R.id.hospitalTextViewId);
            TextView detailsTextView = myView.findViewById(R.id.detailsTextViewId);
            //ImageButton postDeleteBtn = myView.findViewById(R.id.deletePostBtnId);

            LottieAnimationView CalllottieAnimationView = myView.findViewById(R.id.callAnimationView);


            HashMap<String, String> hashMap = arrayList.get(Position);
            String Name = hashMap.get("name");
            String ID = hashMap.get("id");
            String Blood_Group = hashMap.get("blood_group");
            String Divison = hashMap.get("divison");
            String Mobile = hashMap.get("mobile");
            String Date = hashMap.get("date");
            String Hospital_Name = hashMap.get("hospital_name");
            String Details = hashMap.get("details");

            nameTextView.setText(Name);
            bloodTextView.setText(Blood_Group);
            divisonTextView.setText(Divison);
            mobileTextView.setText(Mobile);
            dateTextView.setText(Date);
            hospitalTextView.setText(Hospital_Name);
            detailsTextView.setText(Details);


            CalllottieAnimationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Mobile));// Initiates the Intent

                    startActivity(callIntent);

                }
            });


            /*
            postDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder;
                    alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(getApplicationContext());
                    alertDialogBuilder.setMessage(R.string.delete_post);
                    alertDialogBuilder.setCancelable(false);


                    alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            String url = "https://rakibalam.000webhostapp.com/apps/BloodDonationProject/PostDelete.php?id=" + ID;

                            progressBar.setVisibility(View.VISIBLE);
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    progressBar.setVisibility(View.GONE);

                                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();


                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {


                                    Toast.makeText(getApplicationContext(), "Something wrong!!!", Toast.LENGTH_SHORT).show();
                                    Log.d("DeleteResponse", error.toString());
                                }
                            });

                            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(stringRequest);


                        }
                    });


                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();

                        }
                    });

                    androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();


                }
            });


             */


            return myView;

        }



    }






    public void loadData()
    {

        arrayList = new ArrayList<>();

        String uri= "https://rakibalam.000webhostapp.com/apps/BloodDonationProject/PostView.php";

        progressBar.setVisibility(View.VISIBLE);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, uri, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                progressBar.setVisibility(View.GONE);

                try {

                    for(int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String Name = jsonObject.getString("name");
                        String Blood_Group = jsonObject.getString("blood_group");
                        String Divison = jsonObject.getString("divison");
                        String Mobile = jsonObject.getString("mobile");
                        String Date = jsonObject.getString("date");
                        String Hospital_Name = jsonObject.getString("hospital_name");
                        String Details = jsonObject.getString("details");

                        String id = jsonObject.getString("id");


                        hashMap = new HashMap<>();
                        hashMap.put("id",id);
                        hashMap.put("name",Name);
                        hashMap.put("blood_group",Blood_Group);
                        hashMap.put("divison",Divison);
                        hashMap.put("date",Date);
                        hashMap.put("mobile",Mobile);
                        hashMap.put("hospital_name",Hospital_Name);
                        hashMap.put("details",Details);


                        arrayList.add(hashMap);


                    }

                    MyAdapter myAdapter = new MyAdapter();
                    listView.setAdapter(myAdapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);

                Log.d("sever response",error.toString());

                if (checkInternet()) {

                    //Toast.makeText(getApplicationContext(), "NO INTERNET!! Try these steps to get back online:   " +
                            //"1.Check your mobile data and router       or         2.Reconnect Wi-fi", Toast.LENGTH_LONG).show();

                }else {

                    new AlertDialog.Builder(getApplicationContext())
                            .setTitle("No internet")
                            .setMessage("please connect internet")
                            .show();

                }



            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);



    }











    //=====================Internet Access Call Method===================================

    private boolean checkInternet(){


        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && networkInfo != null && networkInfo.isConnected()) {
            Network nw = connectivityManager.getActiveNetwork();
            if (nw == null) return false;
            NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
            return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
        }
        NetworkInfo nwInfo = connectivityManager.getActiveNetworkInfo();
        return nwInfo != null && nwInfo.isConnected();

    }







}







