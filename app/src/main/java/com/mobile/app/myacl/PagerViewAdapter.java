package com.mobile.app.myacl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Alaa on 2/25/2015.
 */
public class PagerViewAdapter extends FragmentPagerAdapter {

    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                //return new CalendarActivity();
                return new Home();
            case 1:
                return new TimelineHome();
            case 2:
                return new Summary();
            default:
                return new ProfileShow();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

}