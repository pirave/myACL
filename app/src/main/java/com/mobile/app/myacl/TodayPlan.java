package com.mobile.app.myacl;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mobile.app.myacl.CategoryAdapters.CategoryListAdapter;
import com.mobile.app.myacl.CategoryAdapters.TodayCategoryListAdapter;
import com.mobile.app.myacl.ProtocolManager.Category;
import com.mobile.app.myacl.UserManager.ProgressTracker;
import com.mobile.app.myacl.exerciseShow.ExerciseTabs;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;

import java.util.Date;

/**
 * Created by pirave on 15-02-25.
 */
public class TodayPlan extends DailyPlan {
    ProgressTracker tracker;
    protected DynamicListView lvComplete;
    protected CategoryListAdapter adapterComplete;

    public static TodayPlan newInstance(){
        TodayPlan plan = new TodayPlan();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_DATE, new Date());
        plan.setArguments(bundle);
        return plan;
    }

    @Override
    public View initializeAdapter(LayoutInflater inflater, ViewGroup container){
        tracker = ProgressTracker.getInstance(getActivity());
        View view = inflater.inflate(R.layout.today_plan, container, false);

        initializeIncomplete(view);
        initializeComplete(view);

        return view;
    }

    private void initializeIncomplete(View view) {
        lv = (DynamicListView) view.findViewById(R.id.listIncomplete);
        adapter = new TodayCategoryListAdapter(view.getContext() , tracker.getIncomplete());
        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);
        lv.enableSwipeToDismiss(
                new OnDismissCallback() {
                    @Override
                    public void onDismiss(final ViewGroup listView, final int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            Category category = adapter.remove(position);
                            adapterComplete.insert(category);
                            tracker.markComplete(category);
                        }
                    }
                }
        );

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Category c = tracker.getIncomplete().get(position);
                // Go to MyKnee App if clicked
                if (c.getId() == Integer.parseInt(view.getContext().getString(R.string.MyKneeCatID))){
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.setComponent(new ComponentName("ca.utoronto.ece1778", "ca.utoronto.ece1778.MainActivity"));
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(view.getContext(), ExerciseTabs.class);
                    intent.putExtra(EXTRA_EXERCISE, c);
                    startActivity(intent);
                }
            }
        });
    }

    private void initializeComplete(View view) {
        lvComplete = (DynamicListView) view.findViewById(R.id.listComplete);
        adapterComplete = new TodayCategoryListAdapter(view.getContext() , tracker.getComplete());
        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapterComplete);
        animationAdapter.setAbsListView(lvComplete);
        lvComplete.setAdapter(animationAdapter);
        lvComplete.enableSwipeToDismiss(
                new OnDismissCallback() {
                    @Override
                    public void onDismiss(final ViewGroup listView, final int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            Category category = adapterComplete.remove(position);
                            adapter.insert(category);
                            tracker.markIncomplete(category);
                        }
                    }
                }
        );
        lvComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Category c = tracker.getComplete().get(position);
                // Do nothing if it is MyKnee Category
                if (c.getId() != Integer.parseInt(view.getContext().getString(R.string.MyKneeCatID))){
                    Intent intent = new Intent(view.getContext(), ExerciseTabs.class);
                    intent.putExtra(EXTRA_EXERCISE, c);
                    startActivity(intent);
                }
            }
        });
    }

}
