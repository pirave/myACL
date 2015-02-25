package com.mobile.app.myacl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.app.myacl.ShowGoals.GoalsListAdapter;
import com.mobile.app.myacl.exerciseShow.ExerciseShow;


/**
 * Created by Alaa on 2/16/2015.
 */

public class TodayPlan extends Fragment {

    ListView lv;
    GoalsListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.todayplan,container, false);

/*public class TodayPlan extends FragmentActivity {

    ListView lv;
    GoalsListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todayplan);*/
        String[] list ={"goal 1","goal2","goal3","goal4"};
        lv = (ListView) view.findViewById(R.id.listViewgoals);
        adapter = new GoalsListAdapter(view.getContext() , list );
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                startActivity(new Intent(view.getContext(), ExerciseShow.class));
            }
        });
    return view;

    }



}
