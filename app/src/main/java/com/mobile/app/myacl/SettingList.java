package com.mobile.app.myacl;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mobile.app.myacl.PlanManager.Plan;
import com.mobile.app.myacl.PlanManager.PlanManager;
import com.mobile.app.myacl.SummaryBuilder.SummaryBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Alaa on 2/16/2015.
 */

public class SettingList extends Fragment {

    private CheckBox chknot;

    private Context mContext;
    SharedPreferences prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.setting_list,container, false);
        mContext = view.getContext();
        chknot = (CheckBox) view.findViewById(R.id.chknot);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        chknot.setChecked(!prefs.getBoolean("Setmode", true));
     //   chknot.setChecked(getActivity().getSharedPreferences("SETTINGMODE", getActivity().MODE_PRIVATE).getBoolean("Setmode", true));
        addListenerOnChkNot();

        return view;
    }



    public void addListenerOnChkNot() {


        chknot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (chknot.isChecked()) {
                    prefs.edit().putBoolean("Setmode", false).commit();
                  //  getActivity().getSharedPreferences("SETTINGMODE", getActivity().MODE_PRIVATE).edit()
                        //    .putBoolean("Setmode", false).commit();

                }else
                {
                    prefs.edit().putBoolean("Setmode", true).commit();

                 //   getActivity().getSharedPreferences("SETTINGMODE", getActivity().MODE_PRIVATE).edit()
                         //   .putBoolean("Setmode", true).commit();
                }

            }
        });

    }

}
