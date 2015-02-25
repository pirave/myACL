package com.mobile.app.myacl.DatabaseManager;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.mobile.app.myacl.ProtocolManager.Category;
import com.mobile.app.myacl.ProtocolManager.ExerciseManager.Exercise;
import com.mobile.app.myacl.ProtocolManager.ExerciseManager.Step;
import com.mobile.app.myacl.ProtocolManager.Goal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pirave on 15-02-16.
 */
public class ProtocolDB {

    private ProtocolDBHandler mDBHandler;
    private final Context mContext;
    private SQLiteDatabase mDB;

    public ProtocolDB(Context c) {
        mContext = c;
    }

    public void open() throws SQLException {
        mDBHandler = new ProtocolDBHandler(mContext);
        mDB = mDBHandler.getWritableDatabase();
    }

    public void close() {
        mDBHandler.close();
    }

    public int getNumWeeks(){
        final SQLiteStatement stmt = mDB
                .compileStatement("SELECT MAX("
                                + ProtocolDBHandler.KEY_WEEKNUM
                                + ") FROM "
                                + ProtocolDBHandler.TABLE_CATEGORY);

        return (int) stmt.simpleQueryForLong();
    }

    public List<Goal> getGoalsByWeek(int i){
        List<Goal> goals = new ArrayList<Goal>();

        Cursor cursor = mDB.query(
                ProtocolDBHandler.TABLE_GOALS,
                new String[]{ProtocolDBHandler.KEY_GOAL_DESC},
                ProtocolDBHandler.KEY_WEEKNUM + "=?",
                new String[]{Integer.toString(i)},
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                goals.add(new Goal(cursor.getString(0)));
            } while (cursor.moveToNext());
        }
        return goals;
    }

    public List<Category> getCategoriesByWeek(int i){
        // SELECT DISTINCT(cat_id)
        List<Category> categories = new ArrayList<Category>();
        Cursor cursor = mDB.query(
                ProtocolDBHandler.TABLE_CATEGORY,
                new String[]{
                        "DISTINCT(" + ProtocolDBHandler.KEY_CAT_ID + ")",
                        ProtocolDBHandler.KEY_CAT_DESC
                },
                ProtocolDBHandler.KEY_WEEKNUM + "=?",
                new String[]{Integer.toString(i)},
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                categories.add(new Category(
                        cursor.getInt(0),
                        cursor.getString(1),
                        getExercisesByWeekAndCategory(i, cursor.getInt(0))));
            } while (cursor.moveToNext());
        }
        return categories;
    }

    public List<Exercise> getExercisesByWeekAndCategory(int week_num, int cat_id){
        //
        List<Exercise> exercises = new ArrayList<Exercise>();
        Cursor cursor = mDB.query(
                ProtocolDBHandler.TABLE_CATEGORY,
                new String[]{
                        ProtocolDBHandler.FKEY_EXE_ID
                },
                ProtocolDBHandler.KEY_WEEKNUM + "=? AND " + ProtocolDBHandler.KEY_CAT_ID + "=?",
                new String[]{
                        Integer.toString(week_num),
                        Integer.toString(cat_id)
                },
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                exercises.add(new Exercise(getStepsByExercise(cursor.getInt(0))));
            } while (cursor.moveToNext());
        }
        return exercises;
    }
   public List<Step> getStepsByExercise(int exercise_id){
      List<Step> steps = new ArrayList<Step>();
          Cursor cursor = mDB.query(
                ProtocolDBHandler.TABLE_MEDIA,
                new String[]{
                        ProtocolDBHandler.KEY_STEP_NUM,ProtocolDBHandler.KEY_PIC_DESC,ProtocolDBHandler.KEY_PIC_PATH
                },
                ProtocolDBHandler.KEY_EXE_ID + "=? ",
                new String[]{
                        Integer.toString(exercise_id)
                },
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                steps.add(new Step(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return steps;
    }


}
