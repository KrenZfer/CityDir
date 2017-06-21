package com.citydir.krenzfer.citydir.Models;

import org.parceler.Parcel;

/**
 * Created by krenzfer on 08/06/17.
 */
@Parcel
public class Makanan extends TempatAbstract {

    public Makanan(String uID, String imageURL, String namaTempat, String alamat, Double latitude, Double longitude, String description, float rating, int numUserRate){
        super(uID, imageURL, namaTempat, alamat, latitude, longitude, description, rating, numUserRate);
    }

    public Makanan(){}
    @Override
    void displayMessage() {

    }
}
