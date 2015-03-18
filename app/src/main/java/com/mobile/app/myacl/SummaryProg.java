package com.mobile.app.myacl;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ValueFormatter;
import com.mobile.app.myacl.PlanManager.Plan;
import com.mobile.app.myacl.PlanManager.PlanManager;
import com.mobile.app.myacl.SummaryBuilder.SummaryBuilder;

import java.text.DecimalFormat;
import java.util.Date;

public class SummaryProg extends Fragment {

    private PieChart mProgressChart;
    private SummaryBuilder summaryBuilder;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.summary_prog, container, false);
        mContext = view.getContext();

        mProgressChart = (PieChart) view.findViewById(R.id.pie_chart);

        // DEBUGING REMOVE LATER

        mProgressChart.setLogEnabled(true);

        // Initialize Summary Builder;
        summaryBuilder = new SummaryBuilder(mContext);

        // Initialize Charts

        initProgressChart();

        return view;
    }



    private void initProgressChart(){
        mProgressChart.setNoDataTextDescription(getActivity().getString(R.string.noDataDesc));
        mProgressChart.setDescription("Progress");

        Plan plan = new PlanManager(mContext).getPlan();
        int done = plan.getPreviousWeeks(new Date()).values().size() - 1;
        int total = plan.getWeeksList().size();

        float progress = (float) done / total * 100f;
        mProgressChart.setData(summaryBuilder.generatePieData(
                progress,
                ColorTemplate.VORDIPLOM_COLORS[0],
                ColorTemplate.VORDIPLOM_COLORS[4],
                true));
        //mProgressChart.setCenterTextTypeface(summaryBuilder.getTf());
        mProgressChart.invalidate();
    }

    public class MyValueFormatter implements ValueFormatter {
        @Override
        public String getFormattedValue(float value) {
            return new DecimalFormat("###,###,##0").format(value);
        }
    }

}
