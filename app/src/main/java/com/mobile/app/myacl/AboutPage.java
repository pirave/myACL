package com.mobile.app.myacl;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.app.myacl.UserManager.UserProfile;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Alaa on 2/16/2015.
 */
public class AboutPage extends Fragment {

    TextView abouttext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.about,container, false);

        abouttext = (TextView) view.findViewById(R.id.about_text);
        abouttext.setText("This is for the app settings page:\n" +
                "myACL will guide you through 6 months of rehabilitation following your ACL surgery.\n" +
                "This app was created by a physical therapist and engineers to help you complete your daily exercises, track your progress, and ultimately return to your physical activity and sports.  \n ");


        return view;
    }

}