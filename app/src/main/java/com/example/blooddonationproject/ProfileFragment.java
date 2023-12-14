package com.example.blooddonationproject;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    TextView textView1,textView2,textView3,textView4,textView5,textView6;
    EditText editText;

    LinearLayout linearLayout;

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView =  inflater.inflate(R.layout.fragment_profile, container, false);


        textView1 = myView.findViewById(R.id.EmailtextViewId);
        textView2 = myView.findViewById(R.id.NameTextView);
        textView3 = myView.findViewById(R.id.BloodGroupTextView);
        textView4 = myView.findViewById(R.id.MobileTextView);
        textView5 = myView.findViewById(R.id.DivisonTextView);
        textView6 = myView.findViewById(R.id.DistrictTextView);
        editText = myView.findViewById(R.id.passwordETId);



        sharedPreferences = this.getActivity().getSharedPreferences(getString(R.string.app_name),Context.MODE_PRIVATE);


        String Email = sharedPreferences.getString("email","DEFAULT VALUE");
        String Password = sharedPreferences.getString("password","DEFAULT VALUE");
        String Name = sharedPreferences.getString("name","DEFAULT VALUE");
        String BloodGroup = sharedPreferences.getString("bg","DEFAULT VALUE");
        String Mobile = sharedPreferences.getString("number","DEFAULT VALUE");
        String Divison = sharedPreferences.getString("divison","DEFAULT VALUE");
        String District = sharedPreferences.getString("district","DEFAULT VALUE");

        textView1.setText("Email: " +Email+ "");
        textView2.setText("Name: " +Name+ "");
        textView3.setText("Blood Group: "+BloodGroup+ "");
        textView4.setText("Mobile: "+Mobile+ "");
        textView5.setText("Divison: "+Divison+ "");
        textView6.setText("District: "+District+ "");
        editText.setText(Password+ "");




        return myView;
    }







}