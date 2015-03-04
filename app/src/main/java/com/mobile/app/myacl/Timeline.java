package com.mobile.app.myacl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobile.app.myacl.PlanManager.Plan;
import com.mobile.app.myacl.PlanManager.PlanManager;
import com.mobile.app.myacl.ProtocolManager.Category;
import com.mobile.app.myacl.ProtocolManager.Week;

import java.util.Date;
import java.util.List;


/**
 * Created by Alaa on 2/16/2015.
 */

public class Timeline extends Fragment {

    public static final String EXTRA_EXERCISE = "EXERCISE";
    protected ListView lv;
    protected TimelineListAdapter adapter;
    protected Week week;
    protected List<Category> categories;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.timeline_page,container, false);
        final Plan plan = new PlanManager(getActivity()).getPlan();

        lv = (ListView) view.findViewById(R.id.listtime);
        adapter = new TimelineListAdapter(view.getContext(), plan.getWeeksList());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                // Switch to Calendar View with given date
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(android.R.id.tabcontent, CalendarActivity.newInstance(plan.getWeeksList().get(position).getDate()));
                transaction.addToBackStack(null);
//this is me all
                // Commit the transaction
                transaction.commit();
            }
        });
        return view;

    }



}
