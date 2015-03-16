package com.mobile.app.myacl;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mobile.app.myacl.PlanManager.Plan;
import com.mobile.app.myacl.PlanManager.PlanManager;
import com.mobile.app.myacl.ProtocolManager.Category;
import com.mobile.app.myacl.ProtocolManager.Week;
import com.mobile.app.myacl.CategoryAdapters.CategoryListAdapter;
import com.mobile.app.myacl.exerciseShow.ExerciseTabs;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by Alaa on 2/16/2015.
 */

public class DailyPlan extends Fragment {

    public static final String EXTRA_EXERCISE = "EXERCISE";
    protected static final String EXTRA_DATE = "DATE";
    protected DynamicListView lv;
    protected CategoryListAdapter adapter;
    protected Week week;
    protected List<Category> categories;

    public static DailyPlan newInstance(Date date){
        DailyPlan plan = new DailyPlan();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_DATE, date);
        plan.setArguments(bundle);
        return plan;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Plan plan = new PlanManager(getActivity()).getPlan();
        Date d = (Date) getArguments().getSerializable(EXTRA_DATE);

        week = plan.getWeekByDate(d);
        categories = week.getCategoryList();

        List<Date> daysInWeek = plan.getWeekDaysByDate(d);
        if (daysInWeek.indexOf(d) != daysInWeek.size() - 1) {
            Category rom = week.getCategories().get(
                    Integer.parseInt(getActivity().getString(R.string.MyKneeCatID)));
            categories.remove(rom);
        }

        //Inflate the layout for this fragment
        View view = initializeAdapter(inflater, container);

        return view;

    }

    public View initializeAdapter(LayoutInflater inflater, ViewGroup container){
        View view = inflater.inflate(R.layout.daily_plan,container, false);
        lv = (DynamicListView) view.findViewById(R.id.listViewCategories);
        adapter = new CategoryListAdapter(view.getContext() , categories);
        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);
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
