package com.citydir.krenzfer.citydir.View;

/**
 * Created by Jupe Taek on 6/8/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.citydir.krenzfer.citydir.R;

/**
 * Created by Belal on 2/3/2016.
 */

//Our class extending fragment
public class tab3 extends Fragment {

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        return inflater.inflate(R.layout.tab3, container, false);
    }
}
