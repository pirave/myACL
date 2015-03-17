package com.mobile.app.myacl;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mobile.app.myacl.PlanManager.Plan;
import com.mobile.app.myacl.PlanManager.PlanManager;
import com.mobile.app.myacl.SummaryBuilder.SummaryBuilder;
import com.mobile.app.myacl.UserManager.ProgressTracker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
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
        //delete();

        generateCharts();

        return view;
    }

    private void delete(){
        try {
            AssetManager assetManager = mContext.getAssets();
            InputStream in = assetManager.open("databases/ACLUserDB");
            File SDCardRoot = new File(mContext.getFilesDir() + "/../databases/");
            for (File f : SDCardRoot.listFiles()){
                if (f.getName().contains("ACLUserDB"))
                    f.delete();
            }
            File file = new File(SDCardRoot, "ACLUserDB");
            FileOutputStream fileOutput = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int bufferLength = 0;
            while ((bufferLength = in.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
            }
            fileOutput.close();
        }
        catch (Exception e){

        }

    }

    private void generateCharts(){
        makeWeeklyProgressChart();
        makeDaysOfWeekChart();
    }

    private void makeDaysOfWeekChart() {
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
        }
        else
        {
            tday1.setText(sdf.format(dates.get(0)));
            tday2.setText(sdf.format(dates.get(1)));
            tday3.setText(sdf.format(dates.get(2)));
            tday4.setText(sdf.format(dates.get(3)));
            tday5.setText(sdf.format(dates.get(4)));
            tday6.setText(sdf.format(dates.get(5)));
            tday7.setText(sdf.format(dates.get(6)));
        }

        tday1.setTypeface(summaryBuilder.getSemiBoldTf());
        tday2.setTypeface(summaryBuilder.getSemiBoldTf());
        tday3.setTypeface(summaryBuilder.getSemiBoldTf());
        tday4.setTypeface(summaryBuilder.getSemiBoldTf());
        tday5.setTypeface(summaryBuilder.getSemiBoldTf());
        tday6.setTypeface(summaryBuilder.getSemiBoldTf());
        tday7.setTypeface(summaryBuilder.getSemiBoldTf());

        day1.setVisibility(View.GONE);
        day2.setVisibility(View.GONE);
        day3.setVisibility(View.GONE);
        day4.setVisibility(View.GONE);
        day5.setVisibility(View.GONE);
        day6.setVisibility(View.GONE);
        day7.setVisibility(View.GONE);

        lday1.setVisibility(View.GONE);
        lday2.setVisibility(View.GONE);
        lday3.setVisibility(View.GONE);
        lday4.setVisibility(View.GONE);
        lday5.setVisibility(View.GONE);
        lday6.setVisibility(View.GONE);
        lday7.setVisibility(View.GONE);



        if (plan.getWeekByDate(today).getNum()  == 0)
        {
            day1.setVisibility(View.VISIBLE);
            lday1.setVisibility(View.VISIBLE);
        }
        else if (plan.getWeekByDate(today).getNum() == 1) {

            for (int i = 0; i < dates.size(); i++) {

                if (dates.get(i).getDate() == today.getDate())
                {
                    switch (i) {
                        case 0:

                            day2.setVisibility(View.VISIBLE);
                            lday2.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            day3.setVisibility(View.VISIBLE);
                            lday3.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            day4.setVisibility(View.VISIBLE);
                            lday4.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            day5.setVisibility(View.VISIBLE);
                            lday5.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            day6.setVisibility(View.VISIBLE);
                            lday6.setVisibility(View.VISIBLE);
                            break;
                        case 5:
                            day7.setVisibility(View.VISIBLE);
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
                            lday1.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            day2.setVisibility(View.VISIBLE);
                            lday2.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            day3.setVisibility(View.VISIBLE);
                            lday3.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            day4.setVisibility(View.VISIBLE);
                            lday4.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            day5.setVisibility(View.VISIBLE);
                            lday5.setVisibility(View.VISIBLE);
                            break;
                        case 5:
                            day6.setVisibility(View.VISIBLE);
                            lday6.setVisibility(View.VISIBLE);
                            break;
                        case 6:
                            day7.setVisibility(View.VISIBLE);
                            lday7.setVisibility(View.VISIBLE);
                            break;
                    }

                }
            }
        }
    }

    private void makeWeeklyProgressChart(){
        mWeeklyProgressChart.setNoDataTextDescription(getActivity().getString(R.string.noDataDesc));
        ProgressTracker tracker = ProgressTracker.getInstance(mContext);
        int done = tracker.getComplete().size();
        int total = done + tracker.getIncomplete().size();
        float progress = (float) done / total * 100f;

        mWeeklyProgressChart.setData(
                summaryBuilder.generatePieData(
                        progress,
                        ColorTemplate.VORDIPLOM_COLORS[0],
                        Color.WHITE,
                        false));
        if ((total - done) == 0)
            mWeeklyProgressChart.setCenterText("Complete!");
        else
            mWeeklyProgressChart.setCenterText(String.format("%d Left", total - done));

        mWeeklyProgressChart.setDescription("Exercises Remaining");
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
