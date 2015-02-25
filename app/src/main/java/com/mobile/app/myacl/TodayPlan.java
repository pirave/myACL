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
import com.mobile.app.myacl.exerciseShow.ExerciseShow;

import java.util.Date;
import java.util.List;


/**
 * Created by Alaa on 2/16/2015.
 */

public class TodayPlan extends Fragment {

    public static final String EXTRA_EXERCISE = "EXERCISE";
    ListView lv;
    CategoryListAdapter adapter;
    Week week;
    List<Category> categories;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.todayplan,container, false);

        Plan plan = new PlanManager(getActivity()).getPlan();
        Date date = new Date();
        // ***************** TEST DATE!! *******************//
        date.setDate(10);
        date.setMonth(02);
        date.setYear(2015);
        // *************************************************//
        week = plan.getWeekByDate(date);
        categories = week.getCategories();
        lv = (ListView) view.findViewById(R.id.listViewgoals);
        adapter = new CategoryListAdapter(view.getContext() , week.getCategories());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent intent = new Intent(view.getContext(), ExerciseShow.class);
                intent.putExtra(EXTRA_EXERCISE, categories.get(position));
                startActivity(intent);
            }
        });
    return view;

    }



}
