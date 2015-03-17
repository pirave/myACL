package com.mobile.app.myacl;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import com.mobile.app.myacl.UserManager.ProgressTracker;
import com.mobile.app.myacl.UserManager.UserProfile;

import java.util.Calendar;
import java.util.Date;


public class MainActivity extends ActionBarActivity implements  android.support.v7.app.ActionBar.TabListener {
    private ViewPager viewPager;
    private PagerViewAdapter mAdapter;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            // SAVING DATA //
            int rom = Integer.parseInt(extras.getString("rangofmotion"));
            ProgressTracker.getInstance(getApplicationContext()).markCompleteROM(rom);
        }

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




        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY, 12);
        calendar2.set(Calendar.MINUTE, 05);
        calendar2.set(Calendar.AM_PM,Calendar.PM);


        Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), pendingIntent);
// setRepeating() lets you specify a precise custom interval--in this case,
// 20 minutes.
      //  alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,    calendar2.getTimeInMillis(),
        //        1000 * 60 * 480, pendingIntent);

    }
    public void onClickProfile(View view){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.settingfragment, new ProfileShow());
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void onClickShowTodayPlan(View view){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mfragment, TodayPlan.newInstance());
                transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
    public void onClickGoToMyKnee(View view){

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName("ca.utoronto.ece1778", "ca.utoronto.ece1778.MainActivity"));
        startActivity(intent);


       /* Intent intent = getPackageManager().getLaunchIntentForPackage("ca.utoronto.myknee");
        if (intent != null) {*/
    /* We found the activity now start the activity */
      /*      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
    // Bring user to the market or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + "ca.utoronto.myknee"));
            startActivity(intent);
        }*/
    }
    @Override
    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if (tab.getPosition()== 0)
        {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mfragment, new Home());
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
           // viewPager.setCurrentItem(tab.getPosition());

        }
        if (tab.getPosition()== 1)
        {
            TimelineHome.tabHost.setCurrentTab(0);
        }
        if (tab.getPosition() == 3)
        {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.settingfragment, new Setting());
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();

        }
    }

    @Override
    public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    /*}

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
      Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value =  getIntent(). getExtras().getString("rangofmotion");
            rd=value;
            Toast.makeText(this, "Voic" + rd, Toast.LENGTH_SHORT).show();

        }
 */   }
}