package com.example.blooddonationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDonarSearchActivity extends AppCompatActivity {


    String phoneNo;
    String message;

    ProgressBar progressBar;
    Spinner spinnerBlood, spinnerDivison;

    EditText districtET;
    String[] blood;
    String[] divison;
    Button searchButton;
    ListView listView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_donar_search);



        progressBar = findViewById(R.id.progressBarId);


        searchButton = findViewById(R.id.searchButtonId);
        listView = findViewById(R.id.listViewId);

        spinnerBlood = findViewById(R.id.spinnerId1);
        spinnerDivison = findViewById(R.id.spinnerId2);
        districtET = findViewById(R.id.districtNameId);


        blood = getResources().getStringArray(R.array.Blood);
        divison = getResources().getStringArray(R.array.Divison);



        // This part has been working for spineer
        ArrayAdapter<String> bloodAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.bloodspinner_view,R.id.textViewBloodId,blood);

        spinnerBlood.setAdapter(bloodAdapter);


        ArrayAdapter<String> divisonAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinnersampleview,R.id.textViewSampleId,divison);

        spinnerDivison.setAdapter(divisonAdapter);

        //-------------------------------------------------------------------


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String districtName = districtET.getText().toString();
                String edBloodSpinner = spinnerBlood.getSelectedItem().toString();
                String edDivisonSpinner = spinnerDivison.getSelectedItem().toString();

                if(districtName.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter Location Name", Toast.LENGTH_SHORT).show();
                }else if (edBloodSpinner.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter your Blood Group", Toast.LENGTH_SHORT).show();
                }else if (edDivisonSpinner.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter your Divison", Toast.LENGTH_SHORT).show();
                }

                //========This part has been using for blood er plus & minus sign database a rakhar jonno
                try {
                    edBloodSpinner = URLEncoder.encode(edBloodSpinner, "UTF-8");

                } catch (UnsupportedEncodingException e) {

                    e.printStackTrace();
                }

                //======================================



                progressBar.setVisibility(View.VISIBLE);

                String url = "https://rakibalam.000webhostapp.com/apps/BloodDonationProject/DonarSearchData.php?divison=" +edDivisonSpinner+

                        "&bloodgroup="  +edBloodSpinner + "&district=" +districtName;

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {


                        progressBar.setVisibility(View.GONE);

                        try {

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String Name = jsonObject.getString("name");
                                String Blood_Group = jsonObject.getString("bloodgroup");
                                String Divison = jsonObject.getString("divison");
                                String District = jsonObject.getString("district");
                                String Mobile = jsonObject.getString("mobile");

                                hashMap = new HashMap<>();
                                hashMap.put("name", Name);
                                hashMap.put("bloodgroup", Blood_Group);
                                hashMap.put("divison", Divison);
                                hashMap.put("district", District);
                                hashMap.put("mobile", Mobile);
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


                        Log.d("response",error.toString());
                        Toast.makeText(getApplicationContext(),"Data Getting Failed",Toast.LENGTH_SHORT).show();

                    }
                });


                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonArrayRequest);


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
        public View getView(int position, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.donarsearchsample_view,viewGroup,false);


            TextView nameTextView = myView.findViewById(R.id.donarNameId);
            TextView bloodTextView = myView.findViewById(R.id.bloodGroupId);
            TextView divisonTextView = myView.findViewById(R.id.divisonNameId);
            TextView districtTextView = myView.findViewById(R.id.districtNameId);


            hashMap = arrayList.get(position);

            LottieAnimationView callAnimationView = myView.findViewById(R.id.callAnimationViewId);
            LottieAnimationView messageAnimationView = myView.findViewById(R.id.messageAnimationViewId);


            hashMap = arrayList.get(position);
            String Name = hashMap.get("name");
            String Blood_Group = hashMap.get("bloodgroup");
            String Divison = hashMap.get("divison");
            String District = hashMap.get("district");
            String Mobile = hashMap.get("mobile");

            nameTextView.setText(Name);
            bloodTextView.setText(Blood_Group);
            divisonTextView.setText(Divison);
            districtTextView.setText(District);


            callAnimationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Mobile)); // Initiates the Intent

                    startActivity(callIntent);

                }
            });


            messageAnimationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Uri uri = Uri.parse("smsto:"+Mobile);
                    Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
                    intent.putExtra("sms_body", "");
                    startActivity(intent);

                    /*
                    Intent messageIntent = new Intent(Intent.ACTION_VIEW);
                    messageIntent.putExtra("sms_body", "");
                    Uri.fromParts("mobile", Mobile, null);
                    messageIntent.setType("vnd.android-dir/mms-sms");
                    startActivity(messageIntent);

                     */

                }
            });





            return myView;
        }


    }





}