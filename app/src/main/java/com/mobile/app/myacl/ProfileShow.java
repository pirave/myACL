package com.mobile.app.myacl;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.app.myacl.DatabaseManager.UserDB;
import com.mobile.app.myacl.UserManager.UserProfile;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Alaa on 2/16/2015.
 */
public class ProfileShow extends Fragment {

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private int yr, mon, dy;
    private Calendar selectedDate;
    TextView surgerydate, name,age,surgerytype,sid,gender;

    String android_id;
    UserProfile uprofile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.profile,container, false);

        name = (TextView) view.findViewById(R.id.spname);
        age=(TextView) view.findViewById(R.id.spage);
        surgerytype=(TextView) view.findViewById(R.id.spsurgerytype);
        sid=(TextView) view.findViewById(R.id.sids);
        surgerydate = (TextView) view.findViewById(R.id.spsurgerydate);
        gender=(TextView) view.findViewById(R.id.sgender);

        uprofile = UserProfile.getInstance(getActivity());
        name.setText(uprofile.getUsername());
        age.setText(Integer.toString(uprofile.getAge()));
        surgerytype.setText(uprofile.getSurgeryType());
        sid.setText(uprofile.getID());
        surgerydate.setText(dateFormat.format(uprofile.getSurgeryDate()));
        gender.setText(uprofile.getGender());


        // Font

        Typeface lightTf = Typeface.createFromAsset(view.getContext().getAssets(), "fonts/OpenSans-Light.ttf");
        Typeface semiBoldTf = Typeface.createFromAsset(view.getContext().getAssets(),"fonts/OpenSans-Semibold.ttf");
        name.setTypeface(semiBoldTf);
        age.setTypeface(lightTf);
        surgerydate.setTypeface(lightTf);
        surgerytype.setTypeface(lightTf);
        gender.setTypeface(lightTf);
        sid.setTypeface(lightTf);
        ((TextView) view.findViewById(R.id.spage_label)).setTypeface(semiBoldTf);
        ((TextView) view.findViewById(R.id.spsurgerytype_label)).setTypeface(semiBoldTf);
        ((TextView) view.findViewById(R.id.sids_label)).setTypeface(semiBoldTf);
        ((TextView) view.findViewById(R.id.spsurgerydate_label)).setTypeface(semiBoldTf);
        ((TextView) view.findViewById(R.id.spgender_label)).setTypeface(semiBoldTf);

        return view;
    }

}