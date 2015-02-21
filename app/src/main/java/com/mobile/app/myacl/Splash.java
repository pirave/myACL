package com.mobile.app.myacl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;



public class Splash extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity
            startActivity(new Intent(Splash.this, ProfileCreate.class));
        }
        else
        {
            startActivity(new Intent(Splash.this, MainActivity.class));
        }

                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();


    }



}
