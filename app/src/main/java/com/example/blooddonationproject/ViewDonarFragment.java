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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewDonarFragment extends Fragment {

    RecyclerView recyclerView;

    ProgressBar progressBar;

    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap<String,String> hashMap;

    SwipeRefreshLayout swipeRefreshLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView =  inflater.inflate(R.layout.fragment_view_donar, container, false);

        progressBar = myView.findViewById(R.id.progressBarId);

        recyclerView = myView.findViewById(R.id.recyclerViewId);

        swipeRefreshLayout = myView.findViewById(R.id.swifeRefreshLayout2);


        loadData();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefreshLayout.setRefreshing(false);
                loadData();
                Toast.makeText(getActivity(),"Getting Data Ready",Toast.LENGTH_SHORT).show();


            }
        });



        return myView;
    }



    private class MyAdapter extends RecyclerView.Adapter<ViewDonarFragment.MyAdapter.myViewHolder> {

        private class myViewHolder extends RecyclerView.ViewHolder {

            TextView nameTextView, bloodTextView, divisonTextView, districtTextView;

            LottieAnimationView callAnimationView, messageAnimationView;

            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                nameTextView = itemView.findViewById(R.id.donarNameId);
                bloodTextView = itemView.findViewById(R.id.bloodGroupId);
                divisonTextView = itemView.findViewById(R.id.divisonNameId);
                districtTextView = itemView.findViewById(R.id.districtNameId);

                callAnimationView = itemView.findViewById(R.id.callAnimationViewId);
                messageAnimationView = itemView.findViewById(R.id.messageAnimationViewId);

            }
        }

        @NonNull
        @Override
        public MyAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.donarsearchsample_view, parent, false);


            return new MyAdapter.myViewHolder(myView);

        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.myViewHolder holder, int position) {


            hashMap = arrayList.get(position);
            String Name = hashMap.get("name");
            String Blood_Group = hashMap.get("bloodgroup");
            String Divison = hashMap.get("divison");
            String District = hashMap.get("district");
            String Mobile = hashMap.get("mobile");

            holder.nameTextView.setText(Name);
            holder.bloodTextView.setText(Blood_Group);
            holder.divisonTextView.setText(Divison);
            holder.districtTextView.setText(District);

            holder.callAnimationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Mobile));// Initiates the Intent

                    startActivity(callIntent);

                }
            });

            holder.messageAnimationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Uri uri = Uri.parse("smsto:"+Mobile);
                    Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
                    intent.putExtra("sms_body", "");
                    startActivity(intent);

                }
            });


        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

    }





    public void loadData()
    {

        arrayList = new ArrayList<>();

        String uri= "https://rakibalam.000webhostapp.com/apps/BloodDonationProject/DonarView.php";

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
                        String Blood_Group = jsonObject.getString("bloodgroup");
                        String Divison = jsonObject.getString("divison");
                        String District = jsonObject.getString("district");
                        String Mobile = jsonObject.getString("mobile");

                        hashMap = new HashMap<>();
                        hashMap.put("name",Name);
                        hashMap.put("bloodgroup",Blood_Group);
                        hashMap.put("divison",Divison);
                        hashMap.put("district",District);
                        hashMap.put("mobile",Mobile);
                        arrayList.add(hashMap);


                    }


                    MyAdapter myAdapter = new MyAdapter();
                    recyclerView.setAdapter(myAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



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