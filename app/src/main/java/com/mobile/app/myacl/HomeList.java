package com.mobile.app.myacl;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mobile.app.myacl.PlanManager.Plan;
import com.mobile.app.myacl.PlanManager.PlanManager;
import com.mobile.app.myacl.SummaryBuilder.SummaryBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Alaa on 2/16/2015.
 */

public class HomeList extends Fragment {


    private PieChart mDailyProgressChart;
    private PieChart mWeeklyProgressChart;
    private Context mContext;
    private SummaryBuilder summaryBuilder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.home_list,container, false);
        mContext = view.getContext();
        TextView d = (TextView) view.findViewById(R.id.textm);
        d.setText(MainActivity.rd);

        mWeeklyProgressChart = (PieChart) view.findViewById(R.id.weekly_pie_chart);

        // Initialize Summary Builder;
        summaryBuilder = new SummaryBuilder(mContext);

        generateCharts();

        return view;
    }

    private void generateCharts(){
        makeWeeklyProgressChart();
    }

    private void makeWeeklyProgressChart(){
        mWeeklyProgressChart.setNoDataTextDescription(getActivity().getString(R.string.noDataDesc));
        mWeeklyProgressChart.setDescription("");

        Plan plan = new PlanManager(mContext).getPlan();
        Date today = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            today = sdf.parse(sdf.format(today));
        }
        catch (ParseException e){}
        List<Date> dates = plan.getWeekDaysByDate(today);
        int done = dates.indexOf(today);
        int total = dates.size();
        float progress = (float) done / total * 100f;

        mWeeklyProgressChart.setData(
                summaryBuilder.generatePieData(
                        progress,
                        ColorTemplate.VORDIPLOM_COLORS[0],
                        Color.WHITE,
                        false));
        mWeeklyProgressChart.setCenterText(String.format("%.1f%%", progress));
        mWeeklyProgressChart.getLegend().setEnabled(false);
        mWeeklyProgressChart.setCenterTextTypeface(summaryBuilder.getTf());
        mWeeklyProgressChart.setCenterTextSize(22f);
        // radius of the center hole in percent of maximum radius
        mWeeklyProgressChart.setHoleRadius(45f);
        mWeeklyProgressChart.setTransparentCircleRadius(50f);
        mWeeklyProgressChart.invalidate();
    }

}
