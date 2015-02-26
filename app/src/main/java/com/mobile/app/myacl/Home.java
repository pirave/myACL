package com.mobile.app.myacl;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.mobile.app.myacl.ShowGoals.CategoryListAdapter;


/**
 * Created by Alaa on 2/16/2015.
 */
public class Home extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home, container, false);

        // Create a new Fragment to be placed in the activity layout
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();

        transaction.replace(R.id.mfragment, new HomeList());

        transaction.commit();
        return view;
    }

}
