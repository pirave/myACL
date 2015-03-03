package com.mobile.app.myacl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentTabHost;

/**
 * Created by Alaa on 2/16/2015.
 */
import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

public class TimelineHome extends Fragment {

    private FragmentTabHost tabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.timeline_home, container, false);
        tabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        tabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);

        //tabHost = new FragmentTabHost(getActivity());
     //   tabHost.setup(getActivity(), getChildFragmentManager(), R.layout.timeline_home);

        Bundle arg1 = new Bundle();
        arg1.putInt("Arg for Frag1", 1);
        tabHost.addTab(tabHost.newTabSpec("Tab1").setIndicator("", getResources().getDrawable(R.drawable.icon_plan_tab)),
                Timeline.class, arg1);

        Bundle arg2 = new Bundle();
        arg2.putInt("Arg for Frag2", 2);
        tabHost.addTab(tabHost.newTabSpec("Tab2").setIndicator("", getResources().getDrawable(R.drawable.icon_plan_tab)),
                CalendarActivity.class, arg2);
        Bundle arg3 = new Bundle();
        arg2.putInt("Arg for Frag3", 3);
        tabHost.addTab(tabHost.newTabSpec("Tab3").setIndicator("", getResources().getDrawable(R.drawable.icon_plan_tab)),
                Timeline.class, arg3);

        return tabHost;
    }
}