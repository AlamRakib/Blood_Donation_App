package com.example.blooddonationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity {


    ProgressBar progressBar;
    int progress;

    ImageView imageView;

    Animation ZoomIn;

    Button button1, button2;

    public static int Splash_Time_Out = 3000;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash_screen);

        //progressBar = findViewById(R.id.progressBarId);

        imageView = findViewById(R.id.imageViewId);
        //button1 = findViewById(R.id.buttonId1);
        //button2 = findViewById(R.id.buttonId2);








        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.activity_splash_screen);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();




         new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent intent = new Intent(SplashScreenActivity.this, RolePlayActivity.class);
                    startActivity(intent);
                    finish();
            }
        },Splash_Time_Out);












        //ZoomIn = AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.zoomin);



        //imageView.startAnimation(ZoomIn);




        /*
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences(LogInActivity.PRESS_NAME,0);
                boolean hasloogedIn = sharedPreferences.getBoolean("hasloogedIn",false);
                //startActivity(new Intent(getApplicationContext(), LogInActivity.class));

                if (hasloogedIn)
                {
                    Intent intent = new Intent(SplashScreenActivity.this, DonarDashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{

                    Intent intent = new Intent(SplashScreenActivity.this,LogInActivity.class);
                    startActivity(intent);
                    finish();

                }


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences1 = getSharedPreferences(RolePlayActivity.PRESS_NAME,0);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putBoolean("hasloogedIn",true);
                editor.commit();

                startActivity(new Intent(getApplicationContext(), UserDashBoardActivity.class));

            }
        });

         */




        /*
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });
        thread.start();

         */


    }

     /*
    void doWork()
    {

        for(progress=100; progress<=100; progress = progress+100)
        {
            try {
                Thread.sleep(1300);
                progressBar.setProgress(progress);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    void startApp()
    {
        startActivity(new Intent(getApplicationContext(), RoleSelectionActivity.class));
    }


      */















}