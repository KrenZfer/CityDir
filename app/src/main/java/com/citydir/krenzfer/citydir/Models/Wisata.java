package com.citydir.krenzfer.citydir.Models;

import org.parceler.Parcel;

/**
 * Created by krenzfer on 09/06/17.
 */
@Parcel
public class Wisata extends TempatAbstract {

    public Wisata(String uID, String imageURL, String namaTempat, String alamat, Double latitude, Double longitude, String description, float rating, int userNumRate) {
        super(uID, imageURL, namaTempat, alamat, latitude, longitude, description, rating, userNumRate);
    }

    public Wisata() {
    }

    @Override
    void displayMessage() {

    }
}