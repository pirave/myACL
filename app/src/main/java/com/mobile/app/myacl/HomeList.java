package com.mobile.app.myacl;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobile.app.myacl.ShowGoals.GoalsListAdapter;
import com.mobile.app.myacl.exerciseShow.ExerciseShow;


/**
 * Created by Alaa on 2/16/2015.
 */

public class HomeList extends android.app.Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.home_list,container, false);

    return view;

    }



}
