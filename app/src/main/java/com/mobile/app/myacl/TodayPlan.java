package com.mobile.app.myacl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mobile.app.myacl.CategoryAdapters.CategoryListAdapter;
import com.mobile.app.myacl.CategoryAdapters.TodayCategoryListAdapter;
import com.mobile.app.myacl.exerciseShow.ExerciseTabs;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by pirave on 15-02-25.
 */
public class TodayPlan extends DailyPlan {

    public static TodayPlan newInstance(){

        // ***************** TEST DATE!! *******************//
        Calendar cal = Calendar.getInstance();
        cal.set(2015,2,10);
        cal.add(Calendar.MONTH, -1);
        Date d = cal.getTime();
        // *************************************************//

        TodayPlan plan = new TodayPlan();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_DATE, new Date());
        plan.setArguments(bundle);
        return plan;
    }

    @Override
    public void initializeAdapter(View view){
        lv = (DynamicListView) view.findViewById(R.id.listViewCategories);
        adapter = new TodayCategoryListAdapter(view.getContext() , week.getCategories());
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
    }
}
