package com.citydir.krenzfer.citydir.View.TabPenginapan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.citydir.krenzfer.citydir.Constants;
import com.citydir.krenzfer.citydir.Models.Penginapan;
import com.citydir.krenzfer.citydir.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by krenzfer on 08/06/17.
 */

public class ListPenginapan extends Fragment {

    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private View view;
    RecyclerView recycler;
    TextView data_empty;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_TEMPAT_PENGINAPAN);
        view = inflater.inflate(R.layout.reyclermakanan, container, false);
        data_empty = (TextView) view.findViewById(R.id.data_empty);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpFirebaseDatabase();
    }

    private void setUpFirebaseDatabase() {
        recycler = (RecyclerView) view.findViewById(R.id.recyclerMakanan);
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Penginapan, PenginapanViewHolder>(Penginapan.class, R.layout.itemlist, PenginapanViewHolder.class, mDatabase) {

            @Override
            protected void populateViewHolder(PenginapanViewHolder viewHolder, Penginapan model, int position) {
                viewHolder.BindMakanan(model);
            }
        };
//        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recycler.setAdapter(mFirebaseAdapter);
        mFirebaseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
