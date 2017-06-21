package com.citydir.krenzfer.citydir.Models;

import org.parceler.Parcel;

/**
 * Created by krenzfer on 09/06/17.
 */
@Parcel
public class Penginapan extends TempatAbstract {
    public Penginapan(String uID, String imageURL, String namaTempat, String alamat, Double latitude, Double longitude, String description, float rating, int numUserRate) {
        super(uID, imageURL, namaTempat, alamat, latitude, longitude, description, rating, numUserRate);
    }

    public Penginapan() {
    }

    @Override
    void displayMessage() {

    }
}
