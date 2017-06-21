package com.citydir.krenzfer.citydir.Models;

/**
 * Created by krenzfer on 09/06/17.
 */

public abstract class TempatAbstract {

    private String uID;
    private String ImageURL;
    private String NamaTempat;
    private String Alamat;
    private Double Latitude;
    private Double Longitude;
    private String Description;
    private float Rating;
    private int NumUserRate;
    abstract void displayMessage();

    public TempatAbstract(String uid, String imageURL, String namaTempat, String alamat, Double latitude, Double longitude, String description, float rating, int numUserRate) {
        uID = uid;
        ImageURL = imageURL;
        NamaTempat = namaTempat;
        Alamat = alamat;
        Latitude = latitude;
        Longitude = longitude;
        Description = description;
        Rating = rating;
        NumUserRate = numUserRate;
    }

    public TempatAbstract(){}

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getNamaTempat() {
        return NamaTempat;
    }

    public void setNamaTempat(String namaTempat) {
        NamaTempat = namaTempat;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }

    public int getNumUserRate() {
        return NumUserRate;
    }

    public void setNumUserRate(int numUserRate) {
        NumUserRate = numUserRate;
    }

}
