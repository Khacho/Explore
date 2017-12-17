package com.khachik.explore.Models;

import java.util.Date;

public class ArticlesModel {
    private String title;
    private String data;
    private String building_date;
    private String city;
    private String wallpaper_image;
    private String images_folder;

    private String latitude;
    private String longitude;


    public ArticlesModel(String title, String data, String building_date, String city, String country, String images_folder, String wallpaper_image, String latitude, String longitude) {
        this.title = title;
        this.data = data;
        this.building_date = building_date;
        this.city = city;
        this.country = country;
        this.images_folder = images_folder;
        this.wallpaper_image = wallpaper_image;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTitle() {

        return title;
    }


    public String getImages_folder() {
        return images_folder;
    }

    public void setImages_folder(String images_folder) {
        this.images_folder = images_folder;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public String getWallpaper_image() {
        return wallpaper_image;
    }

    public void setWallpaper_image(String wallpaper_image) {
        this.wallpaper_image = wallpaper_image;
    }

    private String country;
}
