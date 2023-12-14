package com.example.blooddonationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import soup.neumorphism.NeumorphCardView;

public class UserDashBoardActivity extends AppCompatActivity {

    ViewPager2 viewPager2;

    ArrayList<Image> ImageArrayList;
    ImageAdapter imageAdapter;
    Handler sliderHandler = new Handler();

    NeumorphCardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6;




    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash_board);

        drawerLayout = findViewById(R.id.drawerlayout);
        materialToolbar = findViewById(R.id.materialToolbar);
        frameLayout = findViewById(R.id.framelayout);
        navigationView = findViewById(R.id.navigationView);





        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(

                UserDashBoardActivity.this,drawerLayout,materialToolbar,R.string.drawer_close,R.string.drawer_open);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.home)
                {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (item.getItemId()==R.id.share)
                {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    startActivity(Intent.createChooser(intent, "share with"));

                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (item.getItemId()==R.id.developer)
                {
                    startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                else if (item.getItemId()==R.id.rating)
                {

                    Intent intent = new Intent(UserDashBoardActivity.this, RatingBarActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }


                else if (item.getItemId()==R.id.privacy)
                {


                    startActivity(new Intent(getApplicationContext(),PrivacyPolicyActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }



                drawerLayout.closeDrawer(GravityCompat.START);




                return true;
            }
        });


        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AlertDialog.Builder alertDialogBuilder;
                alertDialogBuilder = new AlertDialog.Builder(UserDashBoardActivity.this);
                alertDialogBuilder.setTitle(R.string.title_text);
                alertDialogBuilder.setMessage(R.string.message_text);
                alertDialogBuilder.setCancelable(false);


                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();

                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.cancel();

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                return false;
            }
        });











        viewPager2 = findViewById(R.id.viewPager2);

        cardView1 = findViewById(R.id.userPostId);
        cardView2 = findViewById(R.id.cardView2Id);
        cardView3 = findViewById(R.id.cardView3Id);
        cardView4 = findViewById(R.id.cardView4Id);
        cardView5 = findViewById(R.id.cardView5Id);
        cardView6 = findViewById(R.id.cardView6Id);


        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(getApplicationContext(),UserPostActivity.class));

            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),UserStatusFeedActivity.class));

            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), UserDonarSearchActivity.class));

            }
        });


        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), UserOrganizationActivity.class));

            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),UserAmbulanceActivity.class));

            }
        });

        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),UserBloodBankActivity.class));
            }
        });



        //=============================================ViewPageImageSlider function ke call korsi
        viewPageeiMageSlider();
        //=================================================

    }






    //==================================Aiikhane View Page ImageSlider er Kaj kora Hose
    public void viewPageeiMageSlider() {
        ImageArrayList = new ArrayList<>();

        ImageArrayList.add(new Image(R.drawable.bloodbanner1));
        ImageArrayList.add(new Image(R.drawable.bloodbanner2));
        ImageArrayList.add(new Image(R.drawable.bloodbanner3));
        ImageArrayList.add(new Image(R.drawable.bloodbanner4));

        imageAdapter = new ImageAdapter(ImageArrayList, viewPager2);

        viewPager2.setAdapter(imageAdapter);

        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);

        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

                float r = 1 - Math.abs(position);
                page.setScaleY((float) (0.85 + r * 0.14f));
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                sliderHandler.removeCallbacks(runnable);
                sliderHandler.postDelayed(runnable, 5000);
            }
        });

        //==================================================================================


    }






    //=========This part has been using for slider run, pause and resume
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1 );

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(runnable, 1000);
    }

    //=============================================================




    public void onBackPressed() {


        AlertDialog.Builder alertDialogBuilder;
        alertDialogBuilder = new AlertDialog.Builder(UserDashBoardActivity.this);
        alertDialogBuilder.setTitle(R.string.title_text);

        alertDialogBuilder.setMessage(R.string.message_text);
        alertDialogBuilder.setCancelable(false);


        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();


            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                dialog.cancel();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }








}