package com.mobile.app.myacl.SummaryBuilder;

import android.content.Context;

import com.mobile.app.myacl.DatabaseManager.UserDB;
import com.mobile.app.myacl.PlanManager.Plan;
import com.mobile.app.myacl.PlanManager.PlanManager;
import com.mobile.app.myacl.ProtocolManager.Week;
import com.mobile.app.myacl.UserManager.UserProgress;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by pirave on 15-03-15.
 */
public class FrequencyData {
    private static FrequencyData ourInstance;
    private static List<Float> data;
    private Plan plan;
    private UserDB uDB;

    public static FrequencyData getInstance(Context c) {
        if (ourInstance == null)
            ourInstance = new FrequencyData(c);
        return ourInstance;
    }

    private FrequencyData(Context c) {
        uDB = new UserDB(c);
        uDB.open();
        generateData(c);
        uDB.close();
    }

    private void generateData(Context c){
        plan = new PlanManager(c).getPlan();
        Week currentWeek = plan.getWeekByDate(new Date());
        data = new ArrayList<Float>();
        for (Week week: plan.getPreviousWeeks(currentWeek.getDate()).values())
            data.add(generateDataByWeekNum(week));
    }

    private Float generateDataByWeekNum(Week week){
        Float result = 0.0f;
        Float total = 0.0f;
        for (Date date: plan.getWeekDaysByDate(week.getDate())){
            for (UserProgress progress: uDB.getProgressData(date)){
                result += progress.isComplete() ? 1.0f : 0.0f;
                total += 1.0f;
            }
        }
        return result / total * 100;
    }

    public static List<Float> getData() {
        return data;
    }
}
