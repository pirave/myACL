package com.mobile.app.myacl.PlanManager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mobile.app.myacl.DatabaseManager.ProtocolDB;
import com.mobile.app.myacl.DatabaseManager.UserDB;
import com.mobile.app.myacl.MainActivity;
import com.mobile.app.myacl.ProtocolManager.Category;
import com.mobile.app.myacl.ProtocolManager.ExerciseManager.Exercise;
import com.mobile.app.myacl.ProtocolManager.Goal;
import com.mobile.app.myacl.ProtocolManager.Week;
import com.mobile.app.myacl.UserManager.UserProfile;
import com.mobile.app.myacl.UserManager.UserProgress;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by pirave on 15-02-17.
 */
class PlanGenerator {
    private static final String TAG = "PlanGenerator";
    private Plan plan;
    private ProtocolDB pdb;
    private UserDB udb;
    private Context context;

    /**
     * TODO:CHANGE PLAN TYPE HERE SOMEHOW!
     */
    public PlanGenerator(Context context) {
        this.context = context;
        plan = new Plan(context);
        pdb = new ProtocolDB(context);
        udb = new UserDB(context);

        // Begin DB Connection
        pdb.open();
        udb.open();
        generate();
        udb.close();
        pdb.close(); // close PDB connection
        check(plan);

    }

    public Plan getPlan() {
        return plan;
    }

    /**
     * TODO: Populate plan with data from db
     */
    private void generate(){
        Date startDate = UserProfile.getInstance(context).getSurgeryDate();
        for (int i = 0; i<= pdb.getNumWeeks(); i++) {
            Week week = populateWeek(i);
            startDate = populateUserProgressTable(startDate, week);
            plan.addWeek(startDate, week);
            week.setDate(startDate);
        }
    }

    private Week populateWeek(int i){
        return new Week(i, pdb.getGoalsByWeek(i), pdb.getCategoriesByWeek(i));
    }

    private Date populateUserProgressTable(Date prevStartDate, Week week){
        Date newStartDate;
        int numDaysInWeek = 7;
        switch (week.getNum()){
            case 0:
                // keep prev startDate Same
                newStartDate = prevStartDate;
                numDaysInWeek = 1;
                break;
            case 1:
                // add 1 to prevStartDate
                newStartDate = addDays(prevStartDate, 1);
                numDaysInWeek = 6;
                break;
            case 2:
                // add 6 to prevStartDate
                newStartDate = addDays(prevStartDate, 6);
                break;
            default:
                // add 7 to prevStartDate
                newStartDate = addDays(prevStartDate, 7);
                break;
        }
        for (int i = 1; i <= numDaysInWeek; i++){
            for (int c : week.getCategories().keySet())
                udb.createProgressEntry(
                        new UserProgress(
                            c,
                            false,
                            week.getNum(),
                            i,
                            addDays(newStartDate, i-1)
                        )
                );
        }

        return newStartDate;
    }

    private static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static void check(Plan plan){
        for (Week w : plan.getWeeks().values()) {
            Log.d(TAG, Integer.toString(w.getNum()));
            for (Goal g : w.getGoals())
                Log.d(TAG + "_GOALS", g.getDescription());
            for (Category c : w.getCategories().values()){
                Log.d(TAG + "_CATEGORY", c.getDescription());
                for (Exercise e : c.getExercises())
                    Log.d(TAG + "_EXERCISE", e.toString());
            }
        }
    }
}
