package com.example.blooddonationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class DonarDashboardActivity extends AppCompatActivity {


    CardView profile,post,statusFeed,donarsearch,addDonar,viewDonar,organization,bloodBank,ambulance;

    ViewPager2 viewPager2;
    ArrayList<Image> ImageArrayList;
    ImageAdapter imageAdapter;
    Handler sliderHandler = new Handler();

    public static RelativeLayout relativeLayout;

    MaterialToolbar materialToolbar;

    int count = 1;


    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    NavigationView navigationView;

    



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_dashboard);


        profile = findViewById(R.id.ProfileCradViewId);
        post = findViewById(R.id.postCardViewId);
        statusFeed = findViewById(R.id.statusFeedId);
        donarsearch = findViewById(R.id.donarsearchId);
        addDonar = findViewById(R.id.addDonarId);
        viewDonar = findViewById(R.id.ViewDonarCardView);
        organization = findViewById(R.id.organizationId);
        ambulance = findViewById(R.id.ambulanceId);

        materialToolbar = findViewById(R.id.materialToolbar);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationView);


        bloodBank = findViewById(R.id.bloodBankId);


        relativeLayout = findViewById(R.id.layout);






        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(

                DonarDashboardActivity.this,drawerLayout,materialToolbar,R.string.drawer_close,R.string.drawer_open);

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


                    relativeLayout.setVisibility(View.GONE);


                    Fragment fragment = new MyProfileFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayoutId,fragment).commit();

                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                else if (item.getItemId()==R.id.rating)
                {

                    Intent intent = new Intent(DonarDashboardActivity.this, RatingBarActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }


                else if (item.getItemId()==R.id.privacy)
                {

                    Toast.makeText(getApplicationContext(), "Privacy Policy", Toast.LENGTH_SHORT).show();
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
                alertDialogBuilder = new AlertDialog.Builder(DonarDashboardActivity.this,R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Background);
                alertDialogBuilder.setTitle(R.string.title_text);
                alertDialogBuilder.setMessage(R.string.message_text);
                alertDialogBuilder.setCancelable(false);


                alertDialogBuilder.setPositiveButton("yes" , new DialogInterface.OnClickListener() {
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



        //==================================Aiikhane DonarDashboard Profile Fragment er kaj kora hoise
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //========relative layout gone kore disi karon Jokhon Profile fragment a click kora hobe jate Donar Dashboard er sob GONE hpye jay
                relativeLayout.setVisibility(View.GONE);


                Fragment fragment = new ProfileFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayoutId,fragment).commit();

                count = 1;


            }
        });
        //==========================================================

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                relativeLayout.setVisibility(View.GONE);


                Fragment fragment = new PostFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayoutId,fragment).commit();

            }
        });

        statusFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                relativeLayout.setVisibility(View.GONE);


                Fragment fragment = new StatusFeedFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayoutId,fragment).commit();

            }
        });

        donarsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                relativeLayout.setVisibility(View.GONE);


                Fragment fragment = new DonarSearchFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayoutId,fragment).commit();

            }
        });

        addDonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                relativeLayout.setVisibility(View.GONE);


                Fragment fragment = new AddDonarFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayoutId,fragment).commit();

            }
        });

        viewDonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                relativeLayout.setVisibility(View.GONE);


                Fragment fragment = new ViewDonarFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayoutId,fragment).commit();

            }
        });

        organization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                relativeLayout.setVisibility(View.GONE);


                Fragment fragment = new OrganizationFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayoutId,fragment).commit();

            }
        });

        bloodBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                relativeLayout.setVisibility(View.GONE);


                Fragment fragment = new BloodBankFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayoutId,fragment).commit();


            }
        });

        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                relativeLayout.setVisibility(View.GONE);


                Fragment fragment = new AmbulanceFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayoutId,fragment).commit();

            }
        });



        viewPager2 = findViewById(R.id.viewPager2);

        //=============================================ViewPageImageSlider function ke call korsi
        viewPageeiMageSlider();
        //=================================================


    }


    //==================================Aiikhane View Page ImageSlider er Kaj kora Hose
    public void viewPageeiMageSlider()
    {
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
                sliderHandler.postDelayed(runnable,2000);
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





    public void onBackPressed()
    {

        DonarDashboardActivity.relativeLayout.setVisibility(View.VISIBLE);
        Intent intent = new Intent(DonarDashboardActivity.this, DonarDashboardActivity.class);
        startActivity(intent);
        finish();

    }





    /*
    public void onBackPressed() {

        AlertDialog.Builder alertDialogBuilder;
        alertDialogBuilder = new AlertDialog.Builder(DonarDashboardActivity.this);
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

   */










}