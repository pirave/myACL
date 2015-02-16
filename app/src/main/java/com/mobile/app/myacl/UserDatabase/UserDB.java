package com.mobile.app.myacl.UserDatabase;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


@SuppressLint("SimpleDateFormat")
public class UserDB {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_username = "username";
    public static final String KEY_gender = "gender";
    public static final String KEY_age = "age";
    public static final String KEY_surgerytype = "surgerytype";
    public static final String KEY_surgerydate = "surgerydate";
    public static final String KEY_createdate = "createdate";


    public static final String KEY_catid = "catid";
    public static final String KEY_complete = "complete";
    public static final String KEY_weeknum = "weeknum";
    public static final String KEY_daynum = "daynum";
    public static final String KEY_daydate = "daydate";
    public static final String KEY_rangedgree = "rangedgree";


    private static final String DATABASE_NAME = "ACLUserDB";
    private static final String DATABASE_TABLE1 = "profiletable";
    private static final String DATABASE_TABLE2 = "progresstable";
    private static final int DATABASE_VERSION = 1;


    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL("CREATE TABLE " + DATABASE_TABLE1 + " (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_username + " TEXT NOT NULL, "
                    + KEY_gender + " TEXT NOT NULL, "
                    + KEY_age + " INTEGER NOT NULL, "
                    + KEY_surgerytype + " TEXT NOT NULL, "
                    + KEY_surgerydate + " TEXT NOT NULL, "
                    + KEY_createdate + " TEXT NOT NULL);");
            System.out.print("Created profile table");
            Log.d("DB", "Created profile Table");


            db.execSQL("CREATE TABLE " + DATABASE_TABLE2 + " (" + KEY_catid + " INTEGER NOT NULL, "
                    + KEY_complete + " INTEGER NOT NULL, "
                    + KEY_weeknum + " INTEGER NOT NULL, "
                    + KEY_daynum + " INTEGER NOT NULL, "
                    + KEY_daydate + " TEXT NOT NULL, "
                    + KEY_rangedgree + " INTEGER);");
            System.out.print("Created progress table");
            Log.d("DB", "Created progress Table");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE1);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE2);
            onCreate(db);
        }
    }

    public UserDB(Context c) {
        ourContext = c;
    }

    public UserDB open() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }

    public long createProfileEntry(UserProfile userprofile) {
        // TODO Auto-generated method stub

        ContentValues cv = setContentUserprofile(userprofile);

        return ourDatabase.insert(DATABASE_TABLE1, null, cv);
    }


    private ContentValues setContentUserprofile(UserProfile userprofile) {

        ContentValues cv = new ContentValues();
        cv.put(KEY_username, userprofile.getUsername());
        cv.put(KEY_gender, userprofile.getGender());
        cv.put(KEY_age, userprofile.getAge());
        cv.put(KEY_surgerytype, userprofile.getSurgeryType());
        cv.put(KEY_surgerydate, dateFormat.format(userprofile.getSurgeryDate()));
        cv.put(KEY_createdate, dateFormat.format(userprofile.getCreateDate()));

        return cv;
    }

    public ArrayList<UserProfile> getProfileData() {
        // TODO Date Conversion
        String[] columns = new String[] { KEY_ROWID, KEY_username,
                KEY_gender, KEY_age,KEY_surgerytype,KEY_surgerydate, KEY_createdate};
        Cursor c = ourDatabase.query(DATABASE_TABLE1, columns, null, null, null,
                null, null);
        ArrayList<UserProfile> list = new ArrayList<UserProfile>();

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iUsername = c.getColumnIndex(KEY_username);
        int iGender = c.getColumnIndex(KEY_gender);
        int iAge = c.getColumnIndex(KEY_age);
        int iSurgerytype = c.getColumnIndex(KEY_surgerytype);
        int iSurgerydate = c.getColumnIndex(KEY_surgerydate);
        int iCreatedate = c.getColumnIndex(KEY_createdate);


        UserProfile userprofile;
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            userprofile = new UserProfile();
            userprofile.setID(c.getInt(iRow));
            userprofile.setUsername(c.getString(iUsername));
            userprofile.setGender(c.getString(iGender));
            userprofile.setAge(c.getInt(iAge));
            userprofile.setSurgeryType(c.getString(iSurgerytype));
            try {
                userprofile.setSurgeryDate(dateFormat.parse(c.getString(iSurgerydate)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                userprofile.setCreateDate(dateFormat.parse(c.getString(iCreatedate)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            list.add(userprofile);
        }
        return list;
    }

    public long updateProfileEntry(UserProfile userprofile) throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cvUpdate = setContentUserprofile(userprofile);
        return ourDatabase.update(DATABASE_TABLE1, cvUpdate,
                KEY_ROWID + "=" + userprofile.getID(), null);
    }

    public void deleteProfileEntry(long lRow1) throws SQLException {
        // TODO Auto-generated method stub
        ourDatabase.delete(DATABASE_TABLE1, KEY_ROWID + "=" + lRow1, null);
    }


    public long createProgressEntry(UserProgress userprogress) {
        // TODO Auto-generated method stub

        ContentValues cv = setContentUserprogress(userprogress);

        return ourDatabase.insert(DATABASE_TABLE2, null, cv);
    }


    private ContentValues setContentUserprogress(UserProgress userprogress) {

        ContentValues cv = new ContentValues();
        cv.put(KEY_catid, userprogress.getcatID());
        cv.put(KEY_complete, userprogress.getComplete());
        cv.put(KEY_weeknum, userprogress.getWeeknum());
        cv.put(KEY_daynum, userprogress.getDaynum());
        cv.put(KEY_daydate, dateFormat.format(userprogress.getdDaydate()));
        cv.put(KEY_rangedgree, userprogress.getRange());

        return cv;
    }

    public ArrayList<UserProgress> getProgressData() {
        // TODO Date Conversion
        String[] columns = new String[] { KEY_catid, KEY_complete,
                KEY_weeknum, KEY_daynum,KEY_daydate,KEY_rangedgree};
        Cursor c = ourDatabase.query(DATABASE_TABLE1, columns, null, null, null,
                null, null);
        ArrayList<UserProgress> list = new ArrayList<UserProgress>();

        int iCatid = c.getColumnIndex(KEY_catid);
        int iComplete = c.getColumnIndex(KEY_complete);
        int iWeeknum = c.getColumnIndex(KEY_weeknum);
        int iDaynum = c.getColumnIndex(KEY_daynum);
        int iDaydate = c.getColumnIndex(KEY_daydate);
        int iRangedgree = c.getColumnIndex(KEY_rangedgree);



        UserProgress userprogress;
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            userprogress = new UserProgress();
            userprogress.setID(c.getInt(iCatid));
            userprogress.setComplete(c.getInt(iComplete));
            userprogress.setWeeknum(c.getInt(iWeeknum));
            userprogress.setDaynum(c.getInt(iDaynum));
            try {
                userprogress.setDaydate(dateFormat.parse(c.getString(iDaydate)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            userprogress.setRange(c.getInt(iRangedgree));


            list.add(userprogress);
        }
        return list;
    }

    public long updateProgressEntry(UserProgress userprogress) throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cvUpdate = setContentUserprogress(userprogress);
        return ourDatabase.update(DATABASE_TABLE2, cvUpdate,
                KEY_catid + "=" + userprogress.getcatID()
                + " AND " + KEY_weeknum + "=" + userprogress.getWeeknum()
                + " AND " + KEY_daynum + "=" + userprogress.getDaynum(), null);
    }


}

