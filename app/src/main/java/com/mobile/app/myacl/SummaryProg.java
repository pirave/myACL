package com.mobile.app.myacl;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import java.util.List;
import java.util.Random;

public class SummaryProg extends Fragment {

    private PieChart mProgressChart;
    private SummaryBuilder summaryBuilder;
    private Context mContext;
    private TextView mTitle;
    private static String[] motivationalPhrases;
    private final static Random random = new Random();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.summary_prog, container, false);
        mContext = view.getContext();

        mTitle = (TextView) view.findViewById(R.id.txtTitle);
        mProgressChart = (PieChart) view.findViewById(R.id.pie_chart);
        motivationalPhrases = getResources().getStringArray(R.array.motivational_phrases);

        // DEBUGING REMOVE LATER

        mProgressChart.setLogEnabled(true);

        // Initialize Summary Builder;
        summaryBuilder = SummaryBuilder.getInstance(mContext);

        // Initialize Charts

        initProgressChart();

        return view;
    }



    private void initProgressChart(){
        mProgressChart.setNoDataTextDescription(getActivity().getString(R.string.noDataDesc));
        mProgressChart.setDescription("");
        mTitle.setText("Overall Progress");
        mTitle.setTypeface(summaryBuilder.getSemiBoldTf());

        Plan plan = new PlanManager(mContext).getPlan();
        int done = plan.getPreviousWeeks(new Date()).values().size() - 1;
        int total = plan.getWeeksList().size();

        float progress = (float) done / total * 100f;
        mProgressChart.setData(summaryBuilder.generatePieData(
                progress,
                ColorTemplate.VORDIPLOM_COLORS[0],
                ColorTemplate.VORDIPLOM_COLORS[4],
                true));
        mProgressChart.setCenterTextSize(20);
        mProgressChart.setCenterText(motivationalPhrases[random.nextInt(motivationalPhrases.length)]);
        mProgressChart.setCenterTextTypeface(summaryBuilder.getTf());
        mProgressChart.invalidate();
    }

    public class MyValueFormatter implements ValueFormatter {
        @Override
        public String getFormattedValue(float value) {
            return new DecimalFormat("###,###,##0").format(value);
        }
    }

}
