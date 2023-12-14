package com.example.blooddonationproject;

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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AmbulanceFragment extends Fragment {

    ListView listView;

    ProgressBar progressBar;

    FloatingActionButton floatingActionButton;

    HashMap<String,String> hashMap = new HashMap<>();
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView =  inflater.inflate(R.layout.fragment_ambulance, container, false);

        listView = myView.findViewById(R.id.listViewId);
        progressBar = myView.findViewById(R.id.progressBarId);


        floatingActionButton = myView.findViewById(R.id.fltbar);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment fragment = new AmbulanceFromFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayoutId,fragment).commit();


            }
        });





        loadData();


        return myView;
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
            View myView = layoutInflater.inflate(R.layout.ambulance_sample_view, viewGroup, false);

            TextView ORGname   = myView.findViewById(R.id.OwnerName);
            TextView District = myView.findViewById(R.id.districtId);
            TextView Contact = myView.findViewById(R.id.contactId);
            LottieAnimationView callAnimationView = myView.findViewById(R.id.callAnimationViewId);

            hashMap = arrayList.get(position);
            String name = hashMap.get("owner_name");
            String district = hashMap.get("district");
            String number = hashMap.get("contact");

            ORGname.setText(name);
            District.setText(district);
            Contact.setText(number);


            callAnimationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number)); // Initiates the Intent

                    startActivity(callIntent);

                }
            });

            return myView;
        }
    }



    public void loadData()
    {

        String url = "https://rakibalam.000webhostapp.com/apps/BloodDonationProject/AmbulanceView.php";

        progressBar.setVisibility(View.VISIBLE);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {


                progressBar.setVisibility(View.GONE);

                try {

                    for(int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String orgName = jsonObject.getString("owner_name");
                        String District = jsonObject.getString("district");
                        String Mobile = jsonObject.getString("contact");

                        hashMap = new HashMap<>();
                        hashMap.put("owner_name",orgName);
                        hashMap.put("district",District);
                        hashMap.put("contact",Mobile);
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


                Log.d("sever response",error.toString());

                if (checkInternet()) {

                    Toast.makeText(getActivity(), "NO INTERNET!! Try these steps to get back online:   " +
                            "1.Check your mobile data and router       or         2.Reconnect Wi-fi", Toast.LENGTH_LONG).show();

                }else{

                    new AlertDialog.Builder(getActivity())
                            .setTitle("No internet")
                            .setMessage("please connect internet")
                            .show();
                }

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }






    //=====================Internet Access Call Method===================================

    private boolean checkInternet(){


        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
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








}