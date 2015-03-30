package com.mobile.app.myacl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Alaa on 2/16/2015.
 */
public class TeamPage extends Fragment {

    TextView teamtext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.team,container, false);

        teamtext = (TextView) view.findViewById(R.id.team_text);
        teamtext.setText("Nirtal Shah \n" +
                "Pirave Eahalaivan \n" +
                "Alaa Abdulaal \n ");


        return view;
    }

}