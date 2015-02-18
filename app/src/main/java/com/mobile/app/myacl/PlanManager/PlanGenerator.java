package com.mobile.app.myacl.PlanManager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mobile.app.myacl.DatabaseManager.ProtocolDB;
import com.mobile.app.myacl.MainActivity;
import com.mobile.app.myacl.ProtocolManager.Category;
import com.mobile.app.myacl.ProtocolManager.ExerciseManager.Exercise;
import com.mobile.app.myacl.ProtocolManager.Goal;
import com.mobile.app.myacl.ProtocolManager.Week;
import com.mobile.app.myacl.UserManager.UserProfile;

/**
 * Created by pirave on 15-02-17.
 */
class PlanGenerator {
    private static final String TAG = "PlanGenerator";
    private Plan plan;
    private ProtocolDB pdb;

    /**
     * TODO:CHANGE PLAN TYPE HERE SOMEHOW!
     */
    public PlanGenerator(Context context, UserProfile profile) {
        plan = new Plan(profile.getSurgeryType());
        pdb = new ProtocolDB(context);

        // Begin DB Connection
        pdb.open();
        generate();
        pdb.close(); // close DB connection
        check(plan);

    }

    public Plan getPlan() {
        return plan;
    }

    /**
     * TODO: Populate plan with data from db
     */
    private void generate(){
        for (int i = 0; i<= pdb.getNumWeeks(); i++)
            plan.addWeek(populateWeek(i));
    }

    private Week populateWeek(int i){
        return new Week(i, pdb.getGoalsByWeek(i), pdb.getCategoriesByWeek(i));
    }

    public static void check(Plan plan){
        for (Week w : plan.getWeeks()) {
            Log.d(TAG, Integer.toString(w.getNum()));
            for (Goal g : w.getGoals())
                Log.d(TAG + "_GOALS", g.getDescription());
            for (Category c : w.getCategories()){
                Log.d(TAG + "_CATEGORY", c.getDescription());
                for (Exercise e : c.getExercises())
                    Log.d(TAG + "_EXERCISE", e.toString());
            }
        }
    }
}
