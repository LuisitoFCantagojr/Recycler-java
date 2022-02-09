package com.football.football_leagues.service;

import com.football.football_leagues.model.CountryModel;
import com.football.football_leagues.model.LeaguesModel;
import com.football.football_leagues.model.PredictionModel;
import com.football.football_leagues.model.StandingModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FootballApiService {
    String BASE_URL = "https://apiv2.apifootball.com/";

    @GET("?action=get_leagues&country_id=random&APIkey=b8f331d10b12a679dc03ffe489a47b6bdee8d3bce4b6483d1db15966b502baa5")
    Call<List<LeaguesModel>> getLeagues();

    @GET("?action=get_standings&league_id=148&APIkey=b8f331d10b12a679dc03ffe489a47b6bdee8d3bce4b6483d1db15966b502baa5")
    Call<List<StandingModel>> getStanding();

    @GET("?action=get_countries&APIkey=b8f331d10b12a679dc03ffe489a47b6bdee8d3bce4b6483d1db15966b502baa5")
    Call<List<CountryModel>> getCountries();

    @GET("?action=get_predictions&from=2019-06-01&to=2019-06-08&APIkey=b8f331d10b12a679dc03ffe489a47b6bdee8d3bce4b6483d1db15966b502baa5")
    Call<List<PredictionModel>> getPredictions();
}
