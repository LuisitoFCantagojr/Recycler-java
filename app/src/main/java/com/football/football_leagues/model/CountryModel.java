package com.football.football_leagues.model;

import com.google.gson.annotations.SerializedName;

public class CountryModel {


    @SerializedName("country_id")
    public String countryId;
    @SerializedName("country_name")
    public String countryName;
    @SerializedName("country_logo")
    public String countryLogo;

    public String getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryLogo() {
        return countryLogo;
    }
}
