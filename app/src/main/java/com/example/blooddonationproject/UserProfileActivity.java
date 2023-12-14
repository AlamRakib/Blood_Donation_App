package com.example.blooddonationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class UserProfileActivity extends AppCompatActivity {

    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        imageButton = findViewById(R.id.mailBtn);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("Mail To"));
                intent.putExtra(intent.EXTRA_EMAIL, new String[]{"shamsulalamrakib@gmail.com"});
                startActivity(intent.createChooser(intent,"Email Via"));


            }
        });
    }
}