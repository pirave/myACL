package com.mobile.app.myacl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.mobile.app.myacl.UserManager.APIClient;
import com.mobile.app.myacl.UserManager.UserProfile;


public class Splash extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        Boolean profileCreated = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("profileCreated", false);

        if (isFirstRun) {
            //show start activity
            startActivity(new Intent(Splash.this, ProfileCreate.class));
        }
        else
        {
            APIClient apiClient = new APIClient(this);
            if (apiClient.isConnected()) {
                if (!profileCreated)
                    apiClient.sendProfileData(UserProfile.getInstance(this));
                apiClient.updateProgress();
            }

            startActivity(new Intent(Splash.this, MainActivity.class));
        }
                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();
    }



}
