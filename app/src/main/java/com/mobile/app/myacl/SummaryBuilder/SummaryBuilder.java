package com.mobile.app.myacl.SummaryBuilder;

import android.content.Context;
import android.graphics.Typeface;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pirave on 15-03-15.
 */
public class SummaryBuilder {

    private Typeface mTf;
    private Context mContext;

    public SummaryBuilder(Context context) {
        mContext = context;
        mTf = Typeface.createFromAsset(mContext.getAssets(),"fonts/OpenSans-Light.ttf");
    }

    /**
     * generates less data (1 DataSet, 2 values)
     * @return
     */
    public PieData generatePieData(Float val, int colorA, int colorB, boolean showVal) {

        ArrayList<Entry> entries = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Complete");
        xVals.add("Left");

        entries.add(new Entry(val,0));
        entries.add(new Entry(100 - val,1));

        PieDataSet ds1 = new PieDataSet(entries, "");
        ds1.setColors(new ArrayList<Integer>(Arrays.asList(colorA, colorB)));
        ds1.setSliceSpace(2f);

        PieData data = new PieData(xVals, ds1);
        data.setDrawValues(showVal);
        return data;
    }

    /**
     * generates Frequency data (1 DataSet, 2 values)
     * @return
     */
    public LineData generateFrequencyData() {
        ArrayList<Entry> valsComplete = new ArrayList<Entry>();
        ArrayList<Entry> valsIncomplete = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();

        List<Float> rawData = LineGraphData.getInstance(mContext).getFrequencyData();
        for (int i = 0; i < rawData.size(); i++){
            valsComplete.add(new Entry(rawData.get(i),i));
            valsIncomplete.add(new Entry(100-rawData.get(i),i));
            xVals.add("Week " + Integer.toString(i));
        }

        LineDataSet setComplete = new LineDataSet(valsComplete, "Complete");
        LineDataSet setIncomplete = new LineDataSet(valsIncomplete, "Incomplete");

        // Style
        setComplete.setLineWidth(2f);
        setIncomplete.setLineWidth(2f);
        setComplete.setDrawValues(false);
        setIncomplete.setDrawValues(false);
        setComplete.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        setIncomplete.setColor(ColorTemplate.VORDIPLOM_COLORS[4]);
        setComplete.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        setIncomplete.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[4]);


        dataSets.add(setComplete);
        dataSets.add(setIncomplete);

        LineData data = new LineData(xVals, dataSets);
        data.setValueTypeface(mTf);
        return data;
    }

    /**
     * generates Frequency data (1 DataSet, 2 values)
     * @return
     */
    public LineData generateROMData() {
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();

        List<Float> rawData = LineGraphData.getInstance(mContext).getRomData();
        for (int i = 0; i < rawData.size(); i++){
            yVals.add(new Entry(rawData.get(i),i));
            xVals.add("Week " + Integer.toString(i + 1));
        }

        LineDataSet setComplete = new LineDataSet(yVals, "Range of Motion");

        // Style
        setComplete.setLineWidth(2f);
        setComplete.setDrawValues(false);

        dataSets.add(setComplete);

        LineData data = new LineData(xVals, dataSets);
        data.setValueTypeface(mTf);
        return data;
    }

    public Typeface getTf() {
        return mTf;
    }
}
