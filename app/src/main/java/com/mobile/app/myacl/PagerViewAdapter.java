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
                return new CalendarActivity();
            case 1:
                return new TimelineHome();
            case 2:
                // ***************** TEST DATE!! *******************//
                Calendar cal = Calendar.getInstance();
                cal.set(2015,2,28);
                cal.add(Calendar.MONTH, -1);
                Date d = cal.getTime();
                // *************************************************//
                return DailyPlan.newInstance(new Date());
            case 3:
                return new ProfileShow();
            default:
                return new ProfileShow();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

}