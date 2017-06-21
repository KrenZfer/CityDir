package com.citydir.krenzfer.citydir.View.TabKuliner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.citydir.krenzfer.citydir.Constants;
import com.citydir.krenzfer.citydir.Models.Makanan;
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

public class MakananViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;

    View view;
    Context mcontext;

    TextView namaTempat;
    ImageView imgView;
    RatingBar ratingBar;
    TextView Description;
    TextView ratingValue;

    public MakananViewHolder(View itemView) {
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

    public void BindMakanan(Makanan makanan){
        Picasso.with(mcontext)
                .load(makanan.getImageURL())
                .fit()
                .centerCrop()
                .into(imgView);
        namaTempat.setText(makanan.getNamaTempat());
        ratingValue.setText(makanan.getRating() + "/5");
        Description.setText(makanan.getDescription());
    }

    @Override
    public void onClick(View v) {
        if(v == itemView) {
            final ArrayList<Makanan> makanan = new ArrayList<>();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_TEMPAT_KULINER);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        makanan.add(data.getValue(Makanan.class));
                    }

                    int itemPosition = getLayoutPosition();
                    //perlu diubah============================V=============
                    Intent intent = new Intent(mcontext, DetailActivity.class);
                    intent.putExtra("Position", itemPosition + "");
                    intent.putExtra("Tempat", Parcels.wrap(makanan));
                    mcontext.startActivity(intent);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if(v == ratingBar){
            float rating = ratingBar.getRating();
            Toast.makeText(mcontext, "rating ditambah" + rating, Toast.LENGTH_LONG).show();
        }
    }
}