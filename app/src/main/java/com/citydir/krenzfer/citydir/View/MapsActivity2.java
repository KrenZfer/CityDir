package com.citydir.krenzfer.citydir.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.citydir.krenzfer.citydir.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback,tab1.OnDataPass {

    private GoogleMap mMap;
    double lat,lang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapsActivity2);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onDataPass(double lat, double lang) {
        this.lat=lat;
        this.lang=lang;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent intent = getIntent();
        lat = intent.getDoubleExtra("latitude", 0);
        lang = intent.getDoubleExtra("longitude", 0);
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng tempat = new LatLng(lat, lang);
        String nama = intent.getStringExtra("Nama");
        mMap.addMarker(new MarkerOptions().position(tempat).title("Marker in " + nama));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tempat));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(tempat, 15.0f));
    }
}
