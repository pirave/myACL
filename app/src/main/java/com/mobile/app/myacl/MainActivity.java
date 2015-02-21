package com.mobile.app.myacl;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


import com.mobile.app.myacl.DatabaseManager.DataAdapter;
import com.mobile.app.myacl.DatabaseManager.UserDB;
import com.mobile.app.myacl.PlanManager.PlanManager;
import com.mobile.app.myacl.UserManager.UserProfile;
import com.mobile.app.myacl.UserManager.UserProgress;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends  TabActivity {
    static UserDB adb;
    Button show,creat;
    // private ArrayList<UserProfile> userprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        // Tab for Home
        TabHost.TabSpec homespec = tabHost.newTabSpec("Home");
        homespec.setIndicator("", getResources().getDrawable(R.drawable.icon_home_tab));
        Intent homeIntent = new Intent(MainActivity.this, ProfileShow.class);
        homespec.setContent(homeIntent);

        // Tab for timeline
        TabHost.TabSpec timelinespec = tabHost.newTabSpec("TimeLine");
        timelinespec.setIndicator("", getResources().getDrawable(R.drawable.icon_home_tab));
        Intent timelineIntent = new Intent(MainActivity.this, ProfileShow.class);
        timelinespec.setContent(timelineIntent);

        // Tab for Progress
        TabHost.TabSpec progressspec = tabHost.newTabSpec("Progress");
        progressspec.setIndicator("", getResources().getDrawable(R.drawable.icon_home_tab));
        Intent progressIntent = new Intent(MainActivity.this, ProfileShow.class);
        progressspec.setContent(progressIntent);


        // Tab for Settings
        TabHost.TabSpec settingspec = tabHost.newTabSpec("Settings");
        settingspec.setIndicator("", getResources().getDrawable(R.drawable.icon_home_tab));
        Intent settingIntent = new Intent(MainActivity.this, ProfileShow.class);
        settingspec.setContent(settingIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(homespec); // Adding home tab
        tabHost.addTab(timelinespec); // Adding timeline tab
        tabHost.addTab(progressspec); // Adding progress tab
        tabHost.addTab(settingspec); // Adding settings tab



        // creat=(Button)findViewById(R.id.buttoncreat);
        //show=(Button)findViewById(R.id.buttonshow);


//        DataAdapter mDataAdapter = new DataAdapter(this);
//        mDataAdapter.createDatabase();
       // adb = new UserDB(this);
      //  adb.open();
        //userprofile = adb.getProfileData();
      //  adb.close();
//        UserProfile.getInstance().setSurgeryDate(Calendar.getInstance().getTime());
//        PlanManager planManager = new PlanManager(this);
//        planManager.getPlan();




       /* creat.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent view = new Intent(MainActivity.this, ProfileCreate.class);
                MainActivity.this.startActivity(view);
            }
        });
        show.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent view = new Intent(MainActivity.this, ProfileShow.class);
                MainActivity.this.startActivity(view);
            }
        });*/

    }



}
