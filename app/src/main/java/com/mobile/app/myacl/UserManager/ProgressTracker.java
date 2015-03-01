package com.mobile.app.myacl.UserManager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
        //udb.close(); // close connection
    }

    private void initialize(){
        this.progresses = udb.getProgressData(this.date);
        for (UserProgress progress : this.progresses){
            if (progress.isComplete())
                complete.add(categories.get(progress.getCatID()));
            else
                incomplete.add(categories.get(progress.getCatID()));
        }
    }

    public List<Category> getComplete() {
        return complete;
    }

    public List<Category> getIncomplete() {
        return incomplete;
    }

    public void markComplete(Category category){
        this.incomplete.remove(category);
        this.complete.add(category);
        new SetCompleteTask().execute(category);
    }

    public void markIncomplete(Category category){
        this.complete.remove(category);
        this.incomplete.add(category);
        new SetIncompleteTask().execute(category);
    }

    private class SetCompleteTask extends AsyncTask<Category, Void, Void> {
        @Override
        protected Void doInBackground(Category... params) {
            getUserProgressByCateogry(params[0], true);
            return null;
        }
    }

    private class SetIncompleteTask extends AsyncTask<Category, Void, Void> {
        @Override
        protected Void doInBackground(Category... params) {
            getUserProgressByCateogry(params[0], false);
            return null;
        }
    }

    private void getUserProgressByCateogry(Category category, Boolean isComplete){
        for (UserProgress p : progresses) {
            if (p.getCatID() == category.getId()) {
                p.setComplete(isComplete);
                udb.updateProgressEntry(p);
                return;
            }
        }
    }
}
