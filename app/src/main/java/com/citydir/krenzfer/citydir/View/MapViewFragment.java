package com.citydir.krenzfer.citydir.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.citydir.krenzfer.citydir.Constants;
import com.citydir.krenzfer.citydir.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by krenzfer on 09/06/17.
 */

public class MapViewFragment extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    double Latitude, Longitude;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final LinearLayout parent = (LinearLayout) inflater.inflate(R.layout.nearby_layout, container, false);
        mapView = (MapView) parent.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        return parent;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(mapView!=null) {
            mapView.onSaveInstanceState(outState);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        if(mapView!=null) {
            mapView.onResume();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        if(mapView!=null) {
            mapView.onPause();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if(mapView!=null) {
            try {
                mapView.onDestroy();
            } catch (NullPointerException e) {
                Log.e(Constants.TAG, "Error while attempting MapView.onDestroy(), ignoring exception", e);
            }
        }
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        if(mapView!=null) {
            mapView.onLowMemory();
        }
        super.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(Latitude, Longitude))
                .title("Marker"));

    }
}
