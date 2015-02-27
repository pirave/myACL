package com.mobile.app.myacl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobile.app.myacl.PlanManager.Plan;
import com.mobile.app.myacl.PlanManager.PlanManager;
import com.mobile.app.myacl.ProtocolManager.Category;
import com.mobile.app.myacl.ProtocolManager.Week;
import com.mobile.app.myacl.ShowGoals.CategoryListAdapter;
import com.mobile.app.myacl.exerciseShow.ExerciseTabs;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Alaa on 2/16/2015.
 */

public class TimeLine extends Fragment {

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

        Plan plan = new PlanManager(getActivity()).getPlan();

        // ***************** TEST DATE!! *******************//
        Calendar cal = Calendar.getInstance();
        cal.set(2015,2,10);
        cal.add(Calendar.MONTH, -1);
        Date d = cal.getTime();
        // *************************************************//
        week = plan.getWeekByDate(d);
        categories = week.getCategories();
        lv = (ListView) view.findViewById(R.id.listtime);
        adapter = new TimelineListAdapter(view.getContext() , week.getCategories());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent intent = new Intent(view.getContext(), ExerciseTabs.class);
                intent.putExtra(EXTRA_EXERCISE, categories.get(position));
                startActivity(intent);
            }
        });
    return view;

    }



}
