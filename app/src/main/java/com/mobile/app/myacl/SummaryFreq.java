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

public class SummaryFreq extends Fragment {

    private LineChart mFrequencyChart;
    private SummaryBuilder summaryBuilder;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.summary_freq, container, false);
        mContext = view.getContext();
        mFrequencyChart = (LineChart) view.findViewById(R.id.frequency_chart);


        // DEBUGING REMOVE LATER
        mFrequencyChart.setLogEnabled(true);


        // Initialize Summary Builder;
        summaryBuilder = new SummaryBuilder(mContext);

        // Initialize Charts
        initFrequencyChart();


        return view;
    }

    private void initFrequencyChart(){
        mFrequencyChart.setNoDataTextDescription(getActivity().getString(R.string.noDataDesc));
        mFrequencyChart.setDescription("Frequency of Exercise Completion");

        // ** Style ** //
        mFrequencyChart.setHighlightIndicatorEnabled(false);
        mFrequencyChart.setDrawGridBackground(false);
        mFrequencyChart.getAxisRight().setEnabled(false);
        // Get Axis
        YAxis yAxis = mFrequencyChart.getAxisLeft();
        XAxis xAxis = mFrequencyChart.getXAxis();
        // Set Axis style
        yAxis.setTypeface(summaryBuilder.getTf());
        yAxis.setStartAtZero(true);
        yAxis.setValueFormatter(new MyValueFormatter());
        yAxis.setAxisMaxValue(100);
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(summaryBuilder.getTf());
        xAxis.setDrawGridLines(false);

        mFrequencyChart.setData(summaryBuilder.generateFrequencyData());
        mFrequencyChart.invalidate();
    }



    public class MyValueFormatter implements ValueFormatter {
        @Override
        public String getFormattedValue(float value) {
            return new DecimalFormat("###,###,##0").format(value);
        }
    }

}
