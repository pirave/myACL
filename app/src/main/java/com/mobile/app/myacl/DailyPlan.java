package com.mobile.app.myacl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.mobile.app.myacl.ShowCategories.CategoryListAdapter;
import com.mobile.app.myacl.exerciseShow.ExerciseTabs;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Alaa on 2/16/2015.
 */

public class DailyPlan extends Fragment {

    public static final String EXTRA_EXERCISE = "EXERCISE";
    protected DynamicListView lv;
    protected CategoryListAdapter adapter;
    protected Week week;
    protected List<Category> categories;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.daily_plan,container, false);

        Plan plan = new PlanManager(getActivity()).getPlan();

        // ***************** TEST DATE!! *******************//
        Calendar cal = Calendar.getInstance();
        cal.set(2015,2,10);
        cal.add(Calendar.MONTH, -1);
        Date d = cal.getTime();
        // *************************************************//
        week = plan.getWeekByDate(d);
        categories = week.getCategories();
        lv = (DynamicListView) view.findViewById(R.id.listViewCategories);
        adapter = new CategoryListAdapter(view.getContext() , week.getCategories());

        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);
        lv.enableSwipeToDismiss(
                new OnDismissCallback() {
                    @Override
                    public void onDismiss(@NonNull final ViewGroup listView, @NonNull final int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            adapter.remove(position);
                        }
                    }
                }
        );
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
