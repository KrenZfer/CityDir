package com.citydir.krenzfer.citydir.View;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.citydir.krenzfer.citydir.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;

public class MapsActivityInput extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener {

    private GoogleMap mMap;
    int id=1;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private double la, ln;
    private Button refresh;
    Button addButton;
    Marker marker;
    TextView onceSync;
    TextView updateSync;
    private static final int SIGN_IN_REQUEST_CODE = 1;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_input);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapsActivityInput);
        mapFragment.getMapAsync(this);


        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

        }
        onceSync = (TextView)findViewById(R.id.teksSyncOnce);
        updateSync = (TextView)findViewById(R.id.teksSync);

        addButton = (Button)findViewById(R.id.button2);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                add();
            }
        });

        refresh=(Button)findViewById(R.id.button);
        refresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                refresh();
            }
        });

//        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
//            // Start sign in/sign up activity
//            startActivityForResult(
//                    AuthUI.getInstance().createSignInIntentBuilder().setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
//                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
//                            new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
//                            new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build())).build(),
//                    SIGN_IN_REQUEST_CODE
//            );
//        } else {
//            // User is already signed in. Therefore, display
//            // a welcome Toast
//
//            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            FirebaseDatabase.getInstance().getReference("tempat").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    onceSync.setText("");
//                    //  Anggota temp = dataSnapshot.getValue(Anggota.class);
//                    //  updateSync.append(temp.getNama()+ " : "+temp.getAlamat());
//                    /*for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                        Tempat post = postSnapshot.getValue(Tempat.class);
//                        updateSync.append(post.getNama()+ " : "+post.getDeskripsi()+" : "+post.getLongitude()+ " : "+post.getLatitude()+" : "+postSnapshot.getKey()+" \n ");
//                    }*/
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//
//
//            });
//
//
//            Toast.makeText(this,
//                    "Welcome " + FirebaseAuth.getInstance()
//                            .getCurrentUser()
//                            .getDisplayName(),
//                    Toast.LENGTH_LONG)
//                    .show();
//
//
//
//        }
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == SIGN_IN_REQUEST_CODE) {
//            if(resultCode == RESULT_OK) {
//
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//
//                Toast.makeText(this,
//                        "Successfully signed in. Welcome!",
//                        Toast.LENGTH_LONG)
//                        .show();
//
//                FirebaseDatabase.getInstance().getReference("tempat").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        onceSync.setText("");
//                        //Anggota temp = dataSnapshot.getValue(Anggota.class);
//                        //updateSync.setText(temp.getNama()+ " : "+temp.getAlamat());
//
//                        /*for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                            Tempat post = postSnapshot.getValue(Tempat.class);
//                            updateSync.append(post.getNama()+ " : "+post.getDeskripsi()+" : "+post.getLongitude()+ " : "+post.getLatitude()+" : "+postSnapshot.getKey()+" \n ");
//                        }*/
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//
//
//                });
//
//            } else {
//                Toast.makeText(this,
//                        "We couldn't sign you in. Please try again later.",
//                        Toast.LENGTH_LONG)
//                        .show();
//                finish();
//            }
//        }
//    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }


    public void add() {
        Intent in=new Intent(this,FormActivity.class);
        in.putExtra("longitude", ln);
        in.putExtra("latitude", la);
        startActivity(in);
        finish();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            la = mLastLocation.getLatitude();
            ln = mLastLocation.getLongitude();
            String lat = String.valueOf(la);
            String lon = String.valueOf(ln);
            Log.d("Buku: : ", lat);
        }
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

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        marker=mMap.addMarker(new MarkerOptions().position(sydney).title(String.valueOf(ln)));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


    }

    @Override
    public void onLocationChanged(Location location) {

    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void refresh(){
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            la = mLastLocation.getLatitude();
            ln = mLastLocation.getLongitude();
            String lat = String.valueOf(la);
            String lon = String.valueOf(ln);
            Log.d("Buku: : ", lat);
        }
        LatLng now = new LatLng(la, ln);
        if (marker!=null){
            marker.remove();
        }
        marker=mMap.addMarker(new MarkerOptions().position(now).title(String.valueOf(la)+", "+String.valueOf(ln)));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(now));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(now, 15.0f));
    }
}
