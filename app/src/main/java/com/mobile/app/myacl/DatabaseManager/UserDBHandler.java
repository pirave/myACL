package com.mobile.app.myacl.DatabaseManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pirave on 15-02-16.
 */
public class UserDBHandler extends SQLiteOpenHelper {
    public static final String KEY_USERID = "_id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_AGE = "age";
    public static final String KEY_SURGERYTYPE = "surgerytype";
    public static final String KEY_SURGERYDATE = "surgerydate";
    public static final String KEY_CREATEDATE = "createdate";


    public static final String KEY_CATID = "catid";
    public static final String KEY_COMPLETE = "complete";
    public static final String KEY_WEEKNUM = "weeknum";
    public static final String KEY_DAYNUM = "daynum";
    public static final String KEY_DAYDATE = "daydate";
    public static final String KEY_RANGEDGREE = "rangedgree";


    public static final String DATABASE_NAME = "ACLUserDB";
    public static final String TABLE_PROFILE = "profiletable";
    public static final String TABLE_PROGRESS = "progresstable";
    public static final int DATABASE_VERSION = 1;

    public UserDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE " + TABLE_PROFILE + " ("
                + KEY_USERID + " TEXT NOT NULL, "
                + KEY_USERNAME + " TEXT NOT NULL, "
                + KEY_GENDER + " TEXT NOT NULL, "
                + KEY_AGE + " INTEGER NOT NULL, "
                + KEY_SURGERYTYPE + " TEXT NOT NULL, "
                + KEY_SURGERYDATE + " TEXT NOT NULL, "
                + KEY_CREATEDATE + " TEXT NOT NULL);");
        System.out.print("Created profile table");
        Log.d("DB", "Created profile Table");


        db.execSQL("CREATE TABLE " + TABLE_PROGRESS + " ("
                + KEY_CATID + " INTEGER NOT NULL, "
                + KEY_COMPLETE + " INTEGER DEFAULT 0, "
                + KEY_WEEKNUM + " INTEGER NOT NULL, "
                + KEY_DAYNUM + " INTEGER NOT NULL, "
                + KEY_DAYDATE + " TEXT NOT NULL, "
                + KEY_RANGEDGREE + " INTEGER);");
        System.out.print("Created progress table");
        Log.d("DB", "Created progress Table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROGRESS);
        onCreate(db);
    }
}
