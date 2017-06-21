package com.citydir.krenzfer.citydir.View;

/**
 * Created by Jupe Taek on 6/8/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.citydir.krenzfer.citydir.R;

/**
 * Created by Belal on 2/3/2016.
 */

//Our class extending fragment
public class tab1 extends Fragment {
    OnDataPass dataPasser;
    Button addButton;
    MapsActivity2 mp=new MapsActivity2();

    public tab1(){

    }
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View v= inflater.inflate(R.layout.tab1, container, false);
        addButton = (Button)v.findViewById(R.id.button3);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp.lat=-3;
                mp.lang=7;
                Bundle bundle = new Bundle();
                bundle.putDouble("lat",-7.636664);
                bundle.putDouble("lang",111.526903 );
                Intent in=new Intent(getActivity(),MapsActivity2.class);
                in.putExtras(bundle);
                startActivity(in);

            }
        });
        return v;
    }

    @Override
    public void onAttach(Context a) {
        super.onAttach(a);
        dataPasser = (OnDataPass) a;
    }

    public interface OnDataPass {
        public void onDataPass(double lat, double lang);
    }
}