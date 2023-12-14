package com.example.blooddonationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RolePlayActivity extends AppCompatActivity {

    Button button1, button2;
    public static String PRESS_NAME="MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_play);

        button1 = findViewById(R.id.buttonId1);
        button2 = findViewById(R.id.buttonId2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences(RolePlayActivity.PRESS_NAME,0);
                boolean hasloogedIn = sharedPreferences.getBoolean("hasloogedIn",false);

                if (hasloogedIn)
                {
                    Intent intent = new Intent(RolePlayActivity.this, DonarDashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{

                    Intent intent = new Intent(RolePlayActivity.this, LogInActivity.class);
                    startActivity(intent);
                    finish();

                }


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences(RolePlayActivity.PRESS_NAME,0);
                boolean hasloogedIn = sharedPreferences.getBoolean("hasloogedIn",false);

                if (hasloogedIn)
                {
                    Intent intent = new Intent(RolePlayActivity.this,UserDashBoardActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{

                    Intent intent = new Intent(RolePlayActivity.this, UserDashBoardActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        });






    }
}