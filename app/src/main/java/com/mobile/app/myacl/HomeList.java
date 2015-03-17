package com.mobile.app.myacl;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    List<Date> dates;
    Boolean isweekz=false;
    TextView  tday1,tday2,tday3,tday4,tday5,tday6,tday7;
    View lday1,lday2,lday3,lday4,lday5,lday6,lday7,day1,day2,day3,day4,day5,day6,day7;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.home_list,container, false);
        mContext = view.getContext();

        tday1 = (TextView) view.findViewById(R.id.textday1);
        tday2 = (TextView) view.findViewById(R.id.textday2);
        tday3 = (TextView) view.findViewById(R.id.textday3);
        tday4 = (TextView) view.findViewById(R.id.textday4);
        tday5 = (TextView) view.findViewById(R.id.textday5);
        tday6 = (TextView) view.findViewById(R.id.textday6);
        tday7 = (TextView) view.findViewById(R.id.textday7);
        lday1 = (View) view.findViewById(R.id.lineday1);
        lday2 = (View) view.findViewById(R.id.lineday2);
        lday3 = (View) view.findViewById(R.id.lineday3);
        lday4 = (View) view.findViewById(R.id.lineday4);
        lday5 = (View) view.findViewById(R.id.lineday5);
        lday6 = (View) view.findViewById(R.id.lineday6);
        lday7 = (View) view.findViewById(R.id.lineday7);
        day1 = (View) view.findViewById(R.id.day1);
        day2 = (View) view.findViewById(R.id.day2);
        day3 = (View) view.findViewById(R.id.day3);
        day4 = (View) view.findViewById(R.id.day4);
        day5 = (View) view.findViewById(R.id.day5);
        day6 = (View) view.findViewById(R.id.day6);
        day7 = (View) view.findViewById(R.id.day7);

        mWeeklyProgressChart = (PieChart) view.findViewById(R.id.weekly_pie_chart);


        // Initialize Summary Builder;
        summaryBuilder = new SummaryBuilder(mContext);

        generateCharts();
        genrateDaysofweekchart();

        return view;
    }

    private void genrateDaysofweekchart() {
        Date today = new Date();
        Plan plan = new PlanManager(mContext).getPlan();

        dates = plan.getWeekDaysByDate(today);


        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        if (plan.getWeekByDate(today).getNum()  == 0)
        {
            dates = plan.getWeekDaysByDate(new Date(today.getTime() + (1000 * 60 * 60 * 24)));
        }

        if ((plan.getWeekByDate(today).getNum() == 1)|| (plan.getWeekByDate(today).getNum()  == 0))
        {
            tday1.setText(sdf.format(dates.get(0).getTime() - (1000 * 60 * 60 * 24)));
            tday2.setText(sdf.format(dates.get(0)));
            tday3.setText(sdf.format(dates.get(1)));
            tday4.setText(sdf.format(dates.get(2)));
            tday5.setText(sdf.format(dates.get(3)));
            tday6.setText(sdf.format(dates.get(4)));
            tday7.setText(sdf.format(dates.get(5)));
        } else
        {
        tday1.setText(sdf.format(dates.get(0)));
        tday2.setText(sdf.format(dates.get(1)));
        tday3.setText(sdf.format(dates.get(2)));
        tday4.setText(sdf.format(dates.get(3)));
        tday5.setText(sdf.format(dates.get(4)));
        tday6.setText(sdf.format(dates.get(5)));
        tday7.setText(sdf.format(dates.get(6)));
    }



if (plan.getWeekByDate(today).getNum()  == 0)
{

    day1.setVisibility(View.VISIBLE);
    day2.setVisibility(View.GONE);
    day3.setVisibility(View.GONE);
    day4.setVisibility(View.GONE);
    day5.setVisibility(View.GONE);
    day6.setVisibility(View.GONE);
    day7.setVisibility(View.GONE);

    lday1.setVisibility(View.VISIBLE);
    lday2.setVisibility(View.GONE);
    lday3.setVisibility(View.GONE);
    lday4.setVisibility(View.GONE);
    lday5.setVisibility(View.GONE);
    lday6.setVisibility(View.GONE);
    lday7.setVisibility(View.GONE);
}
        else if (plan.getWeekByDate(today).getNum() == 1) {


    for (int i = 0; i < dates.size(); i++) {

        if (dates.get(i).getDate() == today.getDate())
            {
                switch (i) {
                    case 0:

                        day1.setVisibility(View.GONE);
                        day2.setVisibility(View.VISIBLE);
                        day3.setVisibility(View.GONE);
                        day4.setVisibility(View.GONE);
                        day5.setVisibility(View.GONE);
                        day6.setVisibility(View.GONE);
                        day7.setVisibility(View.GONE);

                        lday1.setVisibility(View.GONE);
                        lday2.setVisibility(View.VISIBLE);
                        lday3.setVisibility(View.GONE);
                        lday4.setVisibility(View.GONE);
                        lday5.setVisibility(View.GONE);
                        lday6.setVisibility(View.GONE);
                        lday7.setVisibility(View.GONE);
                        break;
                    case 1:

                        day1.setVisibility(View.GONE);
                        day2.setVisibility(View.GONE);
                        day3.setVisibility(View.VISIBLE);
                        day4.setVisibility(View.GONE);
                        day5.setVisibility(View.GONE);
                        day6.setVisibility(View.GONE);
                        day7.setVisibility(View.GONE);

                        lday1.setVisibility(View.GONE);
                        lday2.setVisibility(View.GONE);
                        lday3.setVisibility(View.VISIBLE);
                        lday4.setVisibility(View.GONE);
                        lday5.setVisibility(View.GONE);
                        lday6.setVisibility(View.GONE);
                        lday7.setVisibility(View.GONE);
                        break;
                    case 2:

                        day1.setVisibility(View.GONE);
                        day2.setVisibility(View.GONE);
                        day3.setVisibility(View.GONE);
                        day4.setVisibility(View.VISIBLE);
                        day5.setVisibility(View.GONE);
                        day6.setVisibility(View.GONE);
                        day7.setVisibility(View.GONE);

                        lday1.setVisibility(View.GONE);
                        lday2.setVisibility(View.GONE);
                        lday3.setVisibility(View.GONE);
                        lday4.setVisibility(View.VISIBLE);
                        lday5.setVisibility(View.GONE);
                        lday6.setVisibility(View.GONE);
                        lday7.setVisibility(View.GONE);
                        break;
                    case 3:
                        day1.setVisibility(View.GONE);
                        day2.setVisibility(View.GONE);
                        day3.setVisibility(View.GONE);
                        day4.setVisibility(View.GONE);
                        day5.setVisibility(View.VISIBLE);
                        day6.setVisibility(View.GONE);
                        day7.setVisibility(View.GONE);

                        lday1.setVisibility(View.GONE);
                        lday2.setVisibility(View.GONE);
                        lday3.setVisibility(View.GONE);
                        lday4.setVisibility(View.GONE);
                        lday5.setVisibility(View.VISIBLE);
                        lday6.setVisibility(View.GONE);
                        lday7.setVisibility(View.GONE);
                        break;
                    case 4:
                        day1.setVisibility(View.GONE);
                        day2.setVisibility(View.GONE);
                        day3.setVisibility(View.GONE);
                        day4.setVisibility(View.GONE);
                        day5.setVisibility(View.GONE);
                        day6.setVisibility(View.VISIBLE);
                        day7.setVisibility(View.GONE);

                        lday1.setVisibility(View.GONE);
                        lday2.setVisibility(View.GONE);
                        lday3.setVisibility(View.GONE);
                        lday4.setVisibility(View.GONE);
                        lday5.setVisibility(View.GONE);
                        lday6.setVisibility(View.VISIBLE);
                        lday7.setVisibility(View.GONE);
                        break;
                    case 5:

                        day1.setVisibility(View.GONE);
                        day2.setVisibility(View.GONE);
                        day3.setVisibility(View.GONE);
                        day4.setVisibility(View.GONE);
                        day5.setVisibility(View.GONE);
                        day6.setVisibility(View.GONE);
                        day7.setVisibility(View.VISIBLE);

                        lday1.setVisibility(View.GONE);
                        lday2.setVisibility(View.GONE);
                        lday3.setVisibility(View.GONE);
                        lday4.setVisibility(View.GONE);
                        lday5.setVisibility(View.GONE);
                        lday6.setVisibility(View.GONE);
                        lday7.setVisibility(View.VISIBLE);
                        break;

                }

            }
        }
    }else {

    for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).getDate() == today.getDate()) {
                switch (i) {
                    case 0:
                        day1.setVisibility(View.VISIBLE);
                        day2.setVisibility(View.GONE);
                        day3.setVisibility(View.GONE);
                        day4.setVisibility(View.GONE);
                        day5.setVisibility(View.GONE);
                        day6.setVisibility(View.GONE);
                        day7.setVisibility(View.GONE);

                        lday1.setVisibility(View.VISIBLE);
                        lday2.setVisibility(View.GONE);
                        lday3.setVisibility(View.GONE);
                        lday4.setVisibility(View.GONE);
                        lday5.setVisibility(View.GONE);
                        lday6.setVisibility(View.GONE);
                        lday7.setVisibility(View.GONE);
                        break;
                    case 1:
                        day1.setVisibility(View.GONE);
                        day2.setVisibility(View.VISIBLE);
                        day3.setVisibility(View.GONE);
                        day4.setVisibility(View.GONE);
                        day5.setVisibility(View.GONE);
                        day6.setVisibility(View.GONE);
                        day7.setVisibility(View.GONE);

                        lday1.setVisibility(View.GONE);
                        lday2.setVisibility(View.VISIBLE);
                        lday3.setVisibility(View.GONE);
                        lday4.setVisibility(View.GONE);
                        lday5.setVisibility(View.GONE);
                        lday6.setVisibility(View.GONE);
                        lday7.setVisibility(View.GONE);
                        break;
                    case 2:
                        day1.setVisibility(View.GONE);
                        day2.setVisibility(View.GONE);
                        day3.setVisibility(View.VISIBLE);
                        day4.setVisibility(View.GONE);
                        day5.setVisibility(View.GONE);
                        day6.setVisibility(View.GONE);
                        day7.setVisibility(View.GONE);

                        lday1.setVisibility(View.GONE);
                        lday2.setVisibility(View.GONE);
                        lday3.setVisibility(View.VISIBLE);
                        lday4.setVisibility(View.GONE);
                        lday5.setVisibility(View.GONE);
                        lday6.setVisibility(View.GONE);
                        lday7.setVisibility(View.GONE);
                        break;
                    case 3:
                        day1.setVisibility(View.GONE);
                        day2.setVisibility(View.GONE);
                        day3.setVisibility(View.GONE);
                        day4.setVisibility(View.VISIBLE);
                        day5.setVisibility(View.GONE);
                        day6.setVisibility(View.GONE);
                        day7.setVisibility(View.GONE);


                        lday1.setVisibility(View.GONE);
                        lday2.setVisibility(View.GONE);
                        lday3.setVisibility(View.GONE);
                        lday4.setVisibility(View.VISIBLE);
                        lday5.setVisibility(View.GONE);
                        lday6.setVisibility(View.GONE);
                        lday7.setVisibility(View.GONE);
                        break;
                    case 4:

                        day1.setVisibility(View.GONE);
                        day2.setVisibility(View.GONE);
                        day3.setVisibility(View.GONE);
                        day4.setVisibility(View.GONE);
                        day5.setVisibility(View.VISIBLE);
                        day6.setVisibility(View.GONE);
                        day7.setVisibility(View.GONE);

                        lday1.setVisibility(View.GONE);
                        lday2.setVisibility(View.GONE);
                        lday3.setVisibility(View.GONE);
                        lday4.setVisibility(View.GONE);
                        lday5.setVisibility(View.VISIBLE);
                        lday6.setVisibility(View.GONE);
                        lday7.setVisibility(View.GONE);
                        break;
                    case 5:

                        day1.setVisibility(View.GONE);
                        day2.setVisibility(View.GONE);
                        day3.setVisibility(View.GONE);
                        day4.setVisibility(View.GONE);
                        day5.setVisibility(View.GONE);
                        day6.setVisibility(View.VISIBLE);
                        day7.setVisibility(View.GONE);

                        lday1.setVisibility(View.GONE);
                        lday2.setVisibility(View.GONE);
                        lday3.setVisibility(View.GONE);
                        lday4.setVisibility(View.GONE);
                        lday5.setVisibility(View.GONE);
                        lday6.setVisibility(View.VISIBLE);
                        lday7.setVisibility(View.GONE);
                        break;
                    case 6:

                        day1.setVisibility(View.GONE);
                        day2.setVisibility(View.GONE);
                        day3.setVisibility(View.GONE);
                        day4.setVisibility(View.GONE);
                        day5.setVisibility(View.GONE);
                        day6.setVisibility(View.GONE);
                        day7.setVisibility(View.VISIBLE);

                        lday1.setVisibility(View.GONE);
                        lday2.setVisibility(View.GONE);
                        lday3.setVisibility(View.GONE);
                        lday4.setVisibility(View.GONE);
                        lday5.setVisibility(View.GONE);
                        lday6.setVisibility(View.GONE);
                        lday7.setVisibility(View.VISIBLE);
                        break;
                }

            }
        }
    }

    }

    private void generateCharts(){
        makeWeeklyProgressChart();
    }

    private void makeWeeklyProgressChart(){
        mWeeklyProgressChart.setNoDataTextDescription(getActivity().getString(R.string.noDataDesc));

        Plan plan = new PlanManager(mContext).getPlan();
        Date today = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            today = sdf.parse(sdf.format(today));
        }
        catch (ParseException e){}
        List<Date> dates = plan.getWeekDaysByDate(today);
        int done = dates.indexOf(today) + 1;
        int total = dates.size();
        float progress = (float) done / total * 100f;

        mWeeklyProgressChart.setData(
                summaryBuilder.generatePieData(
                        progress,
                        ColorTemplate.VORDIPLOM_COLORS[0],
                        Color.WHITE,
                        false));
        if ((total - done) == 0)
            mWeeklyProgressChart.setCenterText("Last\nDay");
        else if ((total - done) == 1)
            mWeeklyProgressChart.setCenterText("1 Day\nLeft");
        else
            mWeeklyProgressChart.setCenterText(String.format("%d Days\nLeft", total - done));
        mWeeklyProgressChart.setDescription(String.format("You are at week %d", plan.getWeekByDate(today).getNum()));
        mWeeklyProgressChart.setDescriptionTypeface(summaryBuilder.getTf());
        mWeeklyProgressChart.getLegend().setEnabled(false);
        mWeeklyProgressChart.setCenterTextTypeface(summaryBuilder.getTf());
        mWeeklyProgressChart.setCenterTextSize(22f);
        // radius of the center hole in percent of maximum radius
        mWeeklyProgressChart.setHoleRadius(45f);
        mWeeklyProgressChart.setTransparentCircleRadius(50f);
        mWeeklyProgressChart.invalidate();
    }

}
