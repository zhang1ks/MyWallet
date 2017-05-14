package com.example.zhang1ks.mywallet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;

/**
 * Created by zhang1ks on 2017-04-20.
 */

public class MyPagerAdapter extends FragmentPagerAdapter{
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //Set title for each tab
    private final String[] titles = new String[]{"Payment Cards", "Gift Cards", "Loyalty Cards",};
    private TabFg1 tabFg1;
    private TabFg2 tabFg2;
    private TabFg3 tabFg3;

    //Switching from 3 Fragments
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (tabFg1 == null) {
                    tabFg1 = new TabFg1();
                }
                return tabFg1;
            case 1:
                if (tabFg2 == null) {
                    tabFg2 = new TabFg2();
                }
                return tabFg2;
            case 2:
                if (tabFg3 == null) {
                    tabFg3 = new TabFg3();
                }
                return tabFg3;
            default:
                return null;
        }
    }

    //Amount of tabs
    @Override
    public int getCount() {
        return titles.length;
    }

    //Current tab title
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }



}
