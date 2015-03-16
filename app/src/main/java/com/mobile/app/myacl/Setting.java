package com.mobile.app.myacl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Alaa on 2/16/2015.
 */
public class Setting extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.setting, container, false);

        // Create a new Fragment to be placed in the activity layout
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();

        transaction.replace(R.id.settingfragment, new SettingList());

        transaction.commit();
        return view;
    }

}
