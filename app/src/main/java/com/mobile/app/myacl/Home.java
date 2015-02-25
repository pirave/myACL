package com.mobile.app.myacl;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.mobile.app.myacl.ShowGoals.CategoryListAdapter;


/**
 * Created by Alaa on 2/16/2015.
 */
public class Home extends FragmentActivity {

    Button gotoexe;
    ListView lv;
    CategoryListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        if (findViewById(R.id.mfragment) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HomeList fragment = new HomeList();
            fragmentTransaction.add(R.id.mfragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }


    }

    public void clickFunctodayplan(View view){

        Fragment nameFragment = new TodayPlan();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mfragment, nameFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

}
