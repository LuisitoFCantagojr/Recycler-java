package com.football.football_leagues.model;

import com.google.gson.annotations.SerializedName;

public class StandingModel {

    @SerializedName("country_name")
    public String countryName;
    @SerializedName("league_id")
    public String leagueId;
    @SerializedName("league_name")
    public String leagueName;
    @SerializedName("team_id")
    public String teamId;
    @SerializedName("team_name")
    public String teamName;
    @SerializedName("overall_league_position")
    public String overallLeaguePosition;
    @SerializedName("overall_league_payed")
    public String overallLeaguePayed;
    @SerializedName("overall_league_W")
    public String overallLeagueW;
    @SerializedName("overall_league_D")
    public String overallLeagueD;
    @SerializedName("overall_league_L")
    public String overallLeagueL;
    @SerializedName("team_badge")
    public String teamBadge;

    public String getCountryName() {
        return countryName;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getOverallLeaguePosition() {
        return overallLeaguePosition;
    }

    public String getOverallLeaguePayed() {
        return overallLeaguePayed;
    }

    public String getOverallLeagueW() {
        return overallLeagueW;
    }

    public String getOverallLeagueD() {
        return overallLeagueD;
    }

    public String getOverallLeagueL() {
        return overallLeagueL;
    }

    public String getTeamBadge() {
        return teamBadge;
    }
}
