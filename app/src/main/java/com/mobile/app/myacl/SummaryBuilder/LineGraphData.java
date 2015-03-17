package com.mobile.app.myacl.SummaryBuilder;

import android.content.Context;

import com.mobile.app.myacl.DatabaseManager.UserDB;
import com.mobile.app.myacl.PlanManager.Plan;
import com.mobile.app.myacl.PlanManager.PlanManager;
import com.mobile.app.myacl.ProtocolManager.Week;
import com.mobile.app.myacl.R;
import com.mobile.app.myacl.UserManager.UserProgress;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pirave on 15-03-15.
 */
public class LineGraphData {
    private static LineGraphData ourInstance;
    private static List<Float> frequencyData;
    private static List<Float> romData;
    private Plan plan;
    private UserDB uDB;
    private Context context;

    public static LineGraphData getInstance(Context c) {
        if (ourInstance == null)
            ourInstance = new LineGraphData(c);
        return ourInstance;
    }

    private LineGraphData(Context c) {
        this.context = c;
        uDB = new UserDB(c);
        uDB.open();
        generateData();
        uDB.close();
    }

    private void generateData(){
        plan = new PlanManager(context).getPlan();
        Week currentWeek = plan.getWeekByDate(new Date());
        frequencyData = new ArrayList<Float>();
        romData = new ArrayList<Float>();
        for (Week week: plan.getPreviousWeeks(currentWeek.getDate()).values())
            generateDataByWeekNum(week);
    }

    private void generateDataByWeekNum(Week week){
        Float result = 0.0f;
        Float total = 0.0f;
        for (Date date: plan.getWeekDaysByDate(week.getDate())){
            for (UserProgress progress: uDB.getProgressData(date)){
                result += progress.isComplete() ? 1.0f : 0.0f;
                total += 1.0f;
                if (progress.getCatID() == Integer.parseInt(context.getString(R.string.MyKneeCatID))) {
                    if (progress.getRangeDegree() != null)
                        romData.add((float) progress.getRangeDegree());
                    else
                        romData.add(0f);
                }
            }
        }
        frequencyData.add(result / total * 100);
    }

    public static List<Float> getFrequencyData() {
        return frequencyData;
    }

    public static List<Float> getRomData() {
        return romData;
    }
}
