package com.citydir.krenzfer.citydir;

/**
 * Created by Jupe Taek on 6/8/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.citydir.krenzfer.citydir.View.TabKuliner.ListMakanan;
import com.citydir.krenzfer.citydir.View.TabPenginapan.ListPenginapan;
import com.citydir.krenzfer.citydir.View.TabWisata.ListWisata;

/**
 * Created by Belal on 2/3/2016.
 */
//Extending FragmentStatePagerAdapter
public class Pager extends FragmentStatePagerAdapter{

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
//                return new tab1();
                return new ListMakanan();
            case 1:
                return new ListWisata();
//                tab2 tab2 = new tab2();
//                return tab2;
            case 2:
                return new ListPenginapan();
//                tab3 tab3 = new tab3();
//                return tab3;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
