package com.football.football_leagues.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.football.football_leagues.model.StandingModel;
import com.football.football_leagues.service.FootballApiService;
import com.football.football_leagues.adapter.StandingAdapter;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StandingController extends AppCompatActivity {
    RecyclerView datalistStanding;
    CountDownTimer countDownTimer;
    ProgressBar progressBar;
    RecyclerView.LayoutManager standingLayout;
    ImageView backToDashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datalist_standing);
        datalistStanding = findViewById(R.id.datalist_standing_recycler_view);
        backToDashboard = findViewById(R.id.back_to_dashboard_standing);
        getStanding();
        progressBar = (ProgressBar) findViewById(R.id.spin_kit_standing);
        Sprite chasingDots = new ThreeBounce();
        progressBar.setIndeterminateDrawable(chasingDots);

        countDownTimer = new CountDownTimer(3500, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                progressBar.setVisibility(View.GONE);

                Toast.makeText(getApplicationContext(),"Please Wait...", Toast.LENGTH_SHORT).show();
            }
        }.start();

        getBackToDashboard();
    }
    public void getBackToDashboard(){
        backToDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(StandingController.this, DashboardController.class); //Open new Activity
                startActivity(myIntent);
                finish();
            }
        });
    }

    public void getStanding() {
        standingLayout = new LinearLayoutManager(this);
        datalistStanding.setLayoutManager(standingLayout);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FootballApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FootballApiService footballApiService = retrofit.create(FootballApiService.class);

        Call<List<StandingModel>> call = footballApiService.getStanding();

        call.enqueue(new Callback<List<StandingModel>>() {
            @Override
            public void onResponse(Call<List<StandingModel>> call, Response<List<StandingModel>> response) {
                if(response.isSuccessful()){
                    List<StandingModel> standingModels = response.body();

                    StandingAdapter mAdapter = new StandingAdapter(StandingController.this, standingModels);
                    datalistStanding.setAdapter(mAdapter);
                }
                else{
                    dialogYesOrNo();
                }
//                    Toast.makeText(getApplicationContext(),"Response Unsuccessful please try again...", Toast.LENGTH_SHORT).show();
//                    progressBar = (ProgressBar)findViewById(R.id.spin_kit_standing);
//                    Sprite chasingDots = new ThreeBounce();
//                    progressBar.setIndeterminateDrawable(chasingDots);
//                    countDownTimer = new CountDownTimer(3500,1000) {
//                        @Override
//                        public void onTick(long millisUntilFinished) {
//
//                        }
//                        @Override
//                        public void onFinish() {
//                            progressBar.setVisibility(View.GONE);
//                            Intent myIntent = new Intent(StandingController.this, DashboardController.class); //Open new Activity
//                            startActivity(myIntent);
//                            finish();
//
//                        }
//                    }.start();
//                }
            }

            @Override
            public void onFailure(Call<List<StandingModel>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Please fix your internet", Toast.LENGTH_SHORT).show();
                System.out.println("your error " + t);
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
                        Intent myIntent = new Intent(StandingController.this, DashboardController.class); //Open new Activity
                        startActivity(myIntent);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        getStanding();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Detected No Internet: Do you want to refresh or go back to dashboard?").setPositiveButton("go back", dialogClickListener)
                .setNegativeButton("refresh", dialogClickListener).show();
    }

    public void onBackPressed () {
        Intent myIntent = new Intent(StandingController.this, DashboardController.class); //Open new Activity
        startActivity(myIntent);
        finish();
    }
}