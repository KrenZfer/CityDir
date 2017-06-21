package com.citydir.krenzfer.citydir.View.TabWisata;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.citydir.krenzfer.citydir.Constants;
import com.citydir.krenzfer.citydir.Models.Wisata;
import com.citydir.krenzfer.citydir.R;
import com.citydir.krenzfer.citydir.View.DetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by krenzfer on 08/06/17.
 */

public class WisataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;

    View view;
    Context mcontext;

    TextView namaTempat;
    ImageView imgView;
    RatingBar ratingBar;
    TextView Description;
    TextView ratingValue;

    public WisataViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        mcontext = itemView.getContext();
        itemView.setOnClickListener(this);

        namaTempat = (TextView) view.findViewById(R.id.namaTempat);
        imgView = (ImageView) view.findViewById(R.id.imageItem);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        Description = (TextView) view.findViewById(R.id.description);
        ratingValue = (TextView) view.findViewById(R.id.ratingValue);
    }

    public void BindMakanan(Wisata wisata){
        Picasso.with(mcontext)
                .load(wisata.getImageURL())
                .fit()
                .centerCrop()
                .into(imgView);

        namaTempat.setText(wisata.getNamaTempat());
        ratingValue = (TextView) view.findViewById(R.id.ratingValue);
        Description.setText(wisata.getDescription());
    }

    @Override
    public void onClick(View v) {
        if(v == itemView) {
            final ArrayList<Wisata> wisata = new ArrayList<>();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_TEMPAT_WISATA);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        wisata.add(data.getValue(Wisata.class));
                    }

                    int itemPosition = getLayoutPosition();
                    //perlu diubah============================V=============
                    Intent intent = new Intent(mcontext, DetailActivity.class);
                    intent.putExtra("Position", itemPosition + "");
                    intent.putExtra("Tempat", Parcels.wrap(wisata));

                    mcontext.startActivity(intent);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if(v == ratingBar) {
            float rating = ratingBar.getRating();
            Toast.makeText(mcontext, "rating ditambah" + rating, Toast.LENGTH_LONG).show();
        }
    }
}