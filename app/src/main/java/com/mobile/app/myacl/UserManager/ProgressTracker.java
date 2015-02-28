package com.mobile.app.myacl.UserManager;

import android.content.Context;

import com.mobile.app.myacl.DatabaseManager.UserDB;
import com.mobile.app.myacl.PlanManager.Plan;
import com.mobile.app.myacl.PlanManager.PlanManager;
import com.mobile.app.myacl.ProtocolManager.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by pirave on 15-02-27.
 */
public final class ProgressTracker {
    private static ProgressTracker instance = null;
    private UserDB udb;
    private Date date;
    private Context context;

    private List<UserProgress> progresses;
    private List<Category> complete = new ArrayList<>();
    private List<Category> incomplete = new ArrayList<>();
    private Map<Integer, Category> categories;

    public static ProgressTracker getInstance(Context context) {
        if (instance == null || isNewDay(instance.date)) {
            instance = new ProgressTracker(context);
        }
        return instance;
    }

    private static boolean isNewDay(Date current){
        Date today = new Date();
        return !(today.getYear() == current.getYear()
                && today.getMonth() == current.getMonth()
                && today.getDay() == current.getDay());
    }

    private ProgressTracker(Context context) {
        this.date = new Date();
        this.context = context;
        this.categories = new PlanManager(context)
                .getPlan()
                .getWeekByDate(this.date)
                .getCategories();
        udb = new UserDB(context);
        // Begin DB Connection
        udb.open();
        initialize();
        udb.close(); // close connection
    }

    private void initialize(){
        progresses = udb.getProgressData(this.date);

        for (UserProgress progress : progresses){
            if (progress.isComplete())
                complete.add(categories.get(progress.getCatID()));
            else
                incomplete.add(categories.get(progress.getCatID()));
        }
    }

    public List<UserProgress> getProgresses() {
        return progresses;
    }

    public List<Category> getComplete() {
        return complete;
    }

    public List<Category> getIncomplete() {
        return incomplete;
    }

    public void markComplete(int catID){
//        for (UserProgress progress : incomplete){
//            if (progress.getCatID() == catID){
//                incomplete.remove(progress);
//                complete.add(progress);
//            }
//        }
    }
}
