package com.mobile.app.myacl.exerciseShow;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.mobile.app.myacl.R;
import com.mobile.app.myacl.exerciseShow.ExerciseTabs;


/**
 * Created by Alaa on 2/16/2015.
 */
public class ExerciseShow extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_show);
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ExerciseTabs fragment = new ExerciseTabs();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }

    }

}
