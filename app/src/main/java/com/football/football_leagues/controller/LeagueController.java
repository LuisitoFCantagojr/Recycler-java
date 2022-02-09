package com.football.football_leagues.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.football.football_leagues.R;
import com.football.football_leagues.model.LeaguesModel;
import com.football.football_leagues.service.FootballApiService;
import com.football.football_leagues.adapter.LeaguesAdapter;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LeagueController extends AppCompatActivity {
    ImageView backToDashboard;
    ProgressBar progressBar;
    CountDownTimer countTimer;
    RecyclerView.LayoutManager homeLayout;
    RecyclerView leagues;
    LeaguesModel leaguesModel;

    List<LeaguesModel> leaguesModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datalist_leagues);
        backToDashboard = findViewById(R.id.back_to_dashboard);
        leagues = findViewById(R.id.recycler_data_only_leagues);

//        recycleViewHome = findViewById(R.id.recycler_data_only_leagues);

        getLeagues();
        backToDashboard ();
        loadingScreen();


    }

    public void getLeagues() {

        homeLayout = new GridLayoutManager(LeagueController.this, 1, GridLayoutManager.VERTICAL, false);
        leagues.setLayoutManager(homeLayout);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(FootballApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            //create api interface
            FootballApiService footballApiService = retrofit.create(FootballApiService.class);

            //object calling
            Call<List<LeaguesModel>> call = footballApiService.getLeagues();

        call.enqueue(new Callback<List<LeaguesModel>>() {
            @Override
            public void onResponse(Call<List<LeaguesModel>> call, Response<List<LeaguesModel>> response) {
                if(response.isSuccessful()){
                    List<LeaguesModel> leaguesModel = (List<LeaguesModel>) response.body();

                    LeaguesAdapter mAdapter = new LeaguesAdapter(LeagueController.this, leaguesModel);
                    leagues.setAdapter(mAdapter);
                }
                else{
                    dialogYesOrNo();

//                    getLeagues();
//                    progressBar = (ProgressBar)findViewById(R.id.spin_kit_country);
//                    Sprite chasingDots = new ThreeBounce();
//                    progressBar.setIndeterminateDrawable(chasingDots);
//                    countTimer = new CountDownTimer(3500,1000) {
//                        @Override
//                        public void onTick(long millisUntilFinished) {
//
//                        }
//                        @Override
//                        public void onFinish() {
//                            progressBar.setVisibility(View.GONE);
//                            Intent myIntent = new Intent(LeagueController.this, DashboardController.class); //Open new Activity
//                            startActivity(myIntent);
//                            finish();
//
//                        }
//                    }.start();
                }
            }

            @Override
            public void onFailure(Call<List<LeaguesModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please fix your internet", Toast.LENGTH_SHORT).show();
//                System.out.println("your error " + t);

                dialogYesOrNo();
            }
        });

    }
    public void dialogYesOrNo(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        Intent myIntent = new Intent(LeagueController.this, DashboardController.class); //Open new Activity
                        startActivity(myIntent);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        getLeagues();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Detected No Internet: Do you want to refresh or go back to dashboard?").setPositiveButton("go back", dialogClickListener)
                .setNegativeButton("refresh", dialogClickListener).show();
    }

    public void loadingScreen(){
        progressBar = (ProgressBar)findViewById(R.id.spin_kit_country);
        Sprite chasingDots = new ThreeBounce();
        progressBar.setIndeterminateDrawable(chasingDots);
        countTimer = new CountDownTimer(2600,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                progressBar.setVisibility(View.GONE);

            }
        }.start();
    }
    public void onBackPressed() {
        Intent myIntent = new Intent(LeagueController.this, DashboardController.class); //Open new Activity
        startActivity(myIntent);
        finish();
    }
     public void backToDashboard (){

        backToDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LeagueController.this, DashboardController.class); //Open new Activity
                startActivity(myIntent);
                finish();
            }
        });
     }
}