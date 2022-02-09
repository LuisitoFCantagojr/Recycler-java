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
import com.football.football_leagues.adapter.CountryAdapter;
import com.football.football_leagues.model.CountryModel;
import com.football.football_leagues.service.FootballApiService;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryController extends AppCompatActivity {
    ImageView backToDashboard;
    RecyclerView countries;
    ProgressBar progressBar;
    CountDownTimer countTimer;
    RecyclerView.LayoutManager homeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datalist_country);
        backToDashboard = findViewById(R.id.back_to_dashboard_country);
        countries = findViewById(R.id.recycler_data_only_country);

        loadingScreen();
        getCountries();
        clickBackToDashboard();
    }
    public void clickBackToDashboard(){
        backToDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(CountryController.this, DashboardController.class); //Open new Activity
                startActivity(myIntent);
                finish();
            }
        });


    }
    public void getCountries(){

        homeLayout = new GridLayoutManager(CountryController.this, 2, GridLayoutManager.VERTICAL, false);
        countries.setLayoutManager(homeLayout);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FootballApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //create api interface
        FootballApiService footballApiService = retrofit.create(FootballApiService.class);

        //object calling
        Call<List<CountryModel>> call = footballApiService.getCountries();

        call.enqueue(new Callback<List<CountryModel>>() {
            @Override
            public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
                if(response.isSuccessful()){
                    List<CountryModel> countryModel = (List<CountryModel>) response.body();


                    CountryAdapter mAdapter = new CountryAdapter(CountryController.this, countryModel);
                    countries.setAdapter(mAdapter);
                }
                else{
                    dialogYesOrNo();

                }
            }

            @Override
            public void onFailure(Call<List<CountryModel>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Please fix your internet", Toast.LENGTH_SHORT).show();
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
                        Intent myIntent = new Intent(CountryController.this, DashboardController.class); //Open new Activity
                        startActivity(myIntent);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        getCountries();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Detected No Internet: Do you want to refresh or go back to dashboard?").setPositiveButton("go back", dialogClickListener)
                .setNegativeButton("refresh", dialogClickListener).show();
    }



    public void onBackPressed() {
        Intent myIntent = new Intent(CountryController.this, DashboardController.class); //Open new Activity
        startActivity(myIntent);
        finish();
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
                Toast.makeText(getApplicationContext(),"Please Wait...", Toast.LENGTH_SHORT).show();


            }
        }.start();
    }
}