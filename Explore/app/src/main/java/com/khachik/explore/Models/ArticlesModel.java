package com.khachik.explore.Models;

import java.util.Date;

public class ArticlesModel {
    private String title;
    private String data;
    private String building_date;
    private String city;

    public ArticlesModel(String title, String data, String building_date, String city, String country) {
        this.title = title;
        this.data = data;
        this.building_date = building_date;
        this.city = city;
        this.country = country;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getBuilding_date() {
        return building_date;
    }

    public void setBuilding_date(String building_date) {
        this.building_date = building_date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String country;
}
