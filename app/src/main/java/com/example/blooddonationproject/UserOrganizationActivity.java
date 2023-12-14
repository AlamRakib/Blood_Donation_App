package com.example.blooddonationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class UserOrganizationActivity extends AppCompatActivity {



    ProgressBar progressBar;


    EditText districtET;
    Button searchButton;
    ListView listView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_organization);


        districtET = findViewById(R.id.districtNameId);
        listView = findViewById(R.id.listViewId);
        progressBar = findViewById(R.id.progressBarId);
        searchButton = findViewById(R.id.searchButtonId);



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String districtName = districtET.getText().toString();


                if (districtName.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter Location Name", Toast.LENGTH_SHORT).show();
                } else {

                    progressBar.setVisibility(View.VISIBLE);

                    String url = "https://rakibalam.000webhostapp.com/apps/BloodDonationProject/ORGSearchData.php?district=" + districtName;

                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray jsonArray) {


                            progressBar.setVisibility(View.GONE);

                            try {

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    String Name = jsonObject.getString("org_name");
                                    String District = jsonObject.getString("district");
                                    String Mobile = jsonObject.getString("contact");

                                    hashMap = new HashMap<>();
                                    hashMap.put("org_name", Name);
                                    hashMap.put("district", District);
                                    hashMap.put("contact", Mobile);
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


                            Log.d("response", error.toString());
                            Toast.makeText(getApplicationContext(), "Data Getting Failed", Toast.LENGTH_SHORT).show();

                        }
                    });


                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(jsonArrayRequest);


                }

            }
        });



    }





    public class MyAdapter extends BaseAdapter{

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
        public View getView(int position, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.org_sample_view,viewGroup,false);

            TextView ORGname   = myView.findViewById(R.id.orgName);
            TextView District = myView.findViewById(R.id.districtId);
            TextView Contact = myView.findViewById(R.id.contactId);

            hashMap = arrayList.get(position);
            String name = hashMap.get("org_name");
            String district = hashMap.get("district");
            String number = hashMap.get("contact");

            ORGname.setText(name);
            District.setText(district);
            Contact.setText(number);




            return myView;
        }
    }




    /*


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
    //==================================================================================================



     */


}