package com.football.football_leagues.model;

import com.google.gson.annotations.SerializedName;

public class LeaguesModel {
    @SerializedName("country_id")
   
    public String countryId;
    @SerializedName("country_name")
   
    public String countryName;
    @SerializedName("league_id")
   
    public String leagueId;
    @SerializedName("league_name")
   
    public String leagueName;
    @SerializedName("league_season")
   
    public String leagueSeason;
    @SerializedName("league_logo")
   
    public String leagueLogo;
    @SerializedName("country_logo")
   
    public String countryLogo;
    public LeaguesModel(String countryName,  String leagueName, String leagueSeason, String leagueLogo, String countryLogo) {
        super();
        this.countryName = countryName;
        this.leagueName = leagueName;
        this.leagueSeason = leagueSeason;
        this.leagueLogo = leagueLogo;
        this.countryLogo = countryLogo;
    }


    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getLeagueSeason() {
        return leagueSeason;
    }

    public void setLeagueSeason(String leagueSeason) {
        this.leagueSeason = leagueSeason;
    }

    public String getLeagueLogo() {
        return leagueLogo;
    }

    public void setLeagueLogo(String leagueLogo) {
        this.leagueLogo = leagueLogo;
    }

    public String getCountryLogo() {
        return countryLogo;
    }

    public void setCountryLogo(String countryLogo) {
        this.countryLogo = countryLogo;
    }
}