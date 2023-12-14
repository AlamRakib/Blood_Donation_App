package com.example.blooddonationproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class MyProfileFragment extends Fragment {


    ImageButton imageButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_my_profile, container, false);


        imageButton = myView.findViewById(R.id.mailBtn);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("Mail To"));
                intent.putExtra(intent.EXTRA_EMAIL, new String[]{"shamsulalamrakib@gmail.com"});
                startActivity(intent.createChooser(intent,"Email Via"));


            }
        });





        return myView;
    }
}