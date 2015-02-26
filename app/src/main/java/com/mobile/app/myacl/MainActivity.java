package com.mobile.app.myacl;


import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.Tab;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import com.mobile.app.myacl.DatabaseManager.UserDB;


public class MainActivity extends ActionBarActivity implements  android.support.v7.app.ActionBar.TabListener {
    private ViewPager viewPager;
    private PagerViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initilization
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        mAdapter = new PagerViewAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        getSupportActionBar().setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getSupportActionBar().setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        Tab friendstab = getSupportActionBar().newTab().setIcon(R.drawable.icon_home_tab).setTabListener(this);
        Tab publicprofiletab = getSupportActionBar().newTab().setIcon(R.drawable.icon_plan_tab).setTabListener(this);
        Tab communitytab2 = getSupportActionBar().newTab().setIcon(R.drawable.icon_progress_tab).setTabListener(this);
        Tab communitytab = getSupportActionBar().newTab().setIcon(R.drawable.icon_settings_tab).setTabListener(this);

        getSupportActionBar().addTab(friendstab);
        getSupportActionBar().addTab(publicprofiletab);
        getSupportActionBar().addTab(communitytab2);
        getSupportActionBar().addTab(communitytab);

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
               // actionBar.setSelectedNavigationItem(position);
                getSupportActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    public void onClickShowTodayPlan(View view){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mfragment, new TodayPlan());
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

}