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

public class SummaryROM extends Fragment {


    private LineChart mROMChart;

    private SummaryBuilder summaryBuilder;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.summary_rom, container, false);
        mContext = view.getContext();
        mROMChart = (LineChart) view.findViewById(R.id.rom_chart);

        // DEBUGING REMOVE LATER
        mROMChart.setLogEnabled(true);

        // Initialize Summary Builder;
        summaryBuilder = new SummaryBuilder(mContext);

        // Initialize Charts
        initROMChart();

        return view;
    }


    private void initROMChart(){
        mROMChart.setNoDataTextDescription(getActivity().getString(R.string.noDataDesc));
        mROMChart.setDescription("Range of Motion Achieved");

        // ** Style ** //
        mROMChart.setHighlightIndicatorEnabled(false);
        mROMChart.setDrawGridBackground(false);
        mROMChart.getAxisRight().setEnabled(false);
        // Get Axis
        YAxis yAxis = mROMChart.getAxisLeft();
        XAxis xAxis = mROMChart.getXAxis();
        // Set Axis style
        yAxis.setTypeface(summaryBuilder.getTf());
        yAxis.setStartAtZero(true);
        yAxis.setValueFormatter(new MyValueFormatter());
        //yAxis.setAxisMaxValue(100);
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(summaryBuilder.getTf());
        xAxis.setDrawGridLines(false);

        mROMChart.setData(summaryBuilder.generateROMData());
        mROMChart.invalidate();
    }


    public class MyValueFormatter implements ValueFormatter {
        @Override
        public String getFormattedValue(float value) {
            return new DecimalFormat("###,###,##0").format(value);
        }
    }

}
