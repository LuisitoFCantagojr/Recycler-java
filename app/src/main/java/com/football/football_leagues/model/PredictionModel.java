package com.football.football_leagues.model;

import com.google.gson.annotations.SerializedName;

public class PredictionModel {


    @SerializedName("country_name")
    public String countryName;
    @SerializedName("league_name")
    public String leagueName;
    @SerializedName("match_date")
    public String matchDate;
    @SerializedName("match_hometeam_name")
    public String matchHometeamName;
    @SerializedName("match_hometeam_score")
    public String matchHometeamScore;
    @SerializedName("match_awayteam_name")
    public String matchAwayteamName;
    @SerializedName("match_awayteam_score")
    public String matchAwayteamScore;

    public String getCountryName() {
        return countryName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public String getMatchHometeamName() {
        return matchHometeamName;
    }

    public String getMatchHometeamScore() {
        return matchHometeamScore;
    }

    public String getMatchAwayteamName() {
        return matchAwayteamName;
    }

    public String getMatchAwayteamScore() {
        return matchAwayteamScore;
    }
}
