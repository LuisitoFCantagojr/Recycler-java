package com.football.football_leagues.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.football.football_leagues.R;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.sackcentury.shinebuttonlib.ShineButton;

import static com.github.ybq.android.spinkit.animation.AnimationUtils.start;

public class DashboardController extends AppCompatActivity {
    ShineButton shineButton;
    LinearLayout openLeagues,openCountry,openStanding,openPrediction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);
        openLeagues = findViewById(R.id.open_leagues);
        openCountry = findViewById(R.id.open_country);
        openStanding = findViewById(R.id.open_standing);
        openPrediction = findViewById(R.id.open_predictions);

        tapViewFirstTime();




        openIntent();



    }
    public void onBackPressed() {
    }
    public void openIntent(){
        openLeagues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DashboardController.this, LeagueController.class); //Open new Activity
                startActivity(myIntent);
            }
        });

        openStanding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DashboardController.this, StandingController.class); //Open new Activity
                startActivity(myIntent);
            }
        });

        openCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DashboardController.this, CountryController.class); //Open new Activity
                startActivity(myIntent);
            }
        });
        openPrediction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DashboardController.this, PredictionController.class); //Open new Activity
                startActivity(myIntent);
            }
        });

    }
    public void tapViewFirstTime(){
        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart",true);

        if(firstStart){
            openOneTime();
        }
    }
    public void openOneTime(){


        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.textview_country), "Country", "This is where you can all the leagues countries!"));

        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.textview_leagues), "Leagues", "Leagues are the list of a league in updated year"));

        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.textview_standings), "Standing", "You can see here the standing Overall win and lose."));

        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.textview_predictions), "Predictions", "You can see the score of Home score and away."));

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();


    }


}