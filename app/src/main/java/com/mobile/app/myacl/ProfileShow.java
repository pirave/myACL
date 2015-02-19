package com.mobile.app.myacl;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.widget.TextView;

import com.mobile.app.myacl.DatabaseManager.UserDB;
import com.mobile.app.myacl.UserManager.UserProfile;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Alaa on 2/16/2015.
 */
public class ProfileShow extends ActionBarActivity {

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private int yr, mon, dy;
    private Calendar selectedDate;
    TextView surgerydate, name,age,surgerytype,sid,gender;

    UserProfile x;
    String android_id;
    UserDB uDB;
    UserProfile uprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileshow);
        x = UserProfile.getInstance();

        name = (TextView) findViewById(R.id.spname);
        age=(TextView) findViewById(R.id.spage);
        surgerytype=(TextView) findViewById(R.id.spsurgerytype);
        sid=(TextView) findViewById(R.id.sids);
        surgerydate = (TextView) findViewById(R.id.spsurgerydate);
        gender=(TextView) findViewById(R.id.sgender);
        uDB = new UserDB(this);
        uDB.open();
        uprofile = uDB.getrow();
        uDB.close();

        name.setText(uprofile.getUsername());
        age.setText(Integer.toString(uprofile.getAge()));
        surgerytype.setText(uprofile.getSurgeryType());
        sid.setText(uprofile.getID());
        surgerydate.setText(dateFormat.format(uprofile.getSurgeryDate()));
        gender.setText(uprofile.getGender());
    }

}
