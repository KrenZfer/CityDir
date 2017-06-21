package com.citydir.krenzfer.citydir.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.citydir.krenzfer.citydir.Models.TempatAbstract;
import com.citydir.krenzfer.citydir.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ImageView imgView;
    TextView Description, namaTempat;
    RatingBar ratingBar;
    Button location;
    TempatAbstract tempatFix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        ArrayList<TempatAbstract> tempat = Parcels.unwrap(intent.getParcelableExtra("Tempat"));
        tempatFix = tempat.get(Integer.valueOf(intent.getStringExtra("Position")));

        namaTempat = (TextView) findViewById(R.id.namaTempatdetail);
        imgView = (ImageView) findViewById(R.id.imgView);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar2);
        Description = (TextView) findViewById(R.id.description);
        location = (Button) findViewById(R.id.Location);

        Picasso.with(getApplicationContext())
                .load(tempatFix.getImageURL())
                .fit()
                .centerCrop()
                .into(imgView);

        namaTempat.setText(tempatFix.getNamaTempat());
        ratingBar.setRating(tempatFix.getRating());
        Description.setText(tempatFix.getDescription());
    }

    public void goToMaps(View view){
        Intent intent = new Intent(DetailActivity.this, MapsActivity2.class);
        intent.putExtra("Nama", tempatFix.getNamaTempat());
        intent.putExtra("longitude", tempatFix.getLongitude());
        intent.putExtra("latitude", tempatFix.getLatitude());
        startActivity(intent);
    }
}
