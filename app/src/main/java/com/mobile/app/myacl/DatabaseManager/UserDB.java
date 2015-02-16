package com.mobile.app.myacl.DatabaseManager;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.mobile.app.myacl.UserManager.UserProfile;
import com.mobile.app.myacl.UserManager.UserProgress;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@SuppressLint("SimpleDateFormat")
public class UserDB {


    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private UserDBHandler mDBHandler;
    private final Context mContext;
    private SQLiteDatabase mDB;

    public UserDB(Context c) {
        mContext = c;
    }

    public void open() throws SQLException {
        mDBHandler = new UserDBHandler(mContext);
        mDB = mDBHandler.getWritableDatabase();
    }

    public void close() {
        mDBHandler.close();
    }

    public long createProfileEntry(UserProfile userprofile) {
        // TODO Auto-generated method stub

        ContentValues cv = setContentUserprofile(userprofile);

        return mDB.insert(UserDBHandler.TABLE_PROFILE, null, cv);
    }


    private ContentValues setContentUserprofile(UserProfile userprofile) {

        ContentValues cv = new ContentValues();
        cv.put(UserDBHandler.KEY_USERNAME, userprofile.getUsername());
        cv.put(UserDBHandler.KEY_GENDER, userprofile.getGender());
        cv.put(UserDBHandler.KEY_AGE, userprofile.getAge());
        cv.put(UserDBHandler.KEY_SURGERYTYPE, userprofile.getSurgeryType());
        cv.put(UserDBHandler.KEY_SURGERYDATE, dateFormat.format(userprofile.getSurgeryDate()));
        cv.put(UserDBHandler.KEY_CREATEDATE, dateFormat.format(userprofile.getCreateDate()));

        return cv;
    }

    public ArrayList<UserProfile> getProfileData() {
        // TODO Date Conversion
        String[] columns = new String[] {
                UserDBHandler.KEY_ROWID,
                UserDBHandler.KEY_USERNAME,
                UserDBHandler.KEY_GENDER,
                UserDBHandler.KEY_AGE,
                UserDBHandler.KEY_SURGERYTYPE,
                UserDBHandler.KEY_SURGERYDATE,
                UserDBHandler.KEY_CREATEDATE
        };
        Cursor c = mDB.query(UserDBHandler.TABLE_PROFILE, columns, null, null, null,
                null, null);
        ArrayList<UserProfile> list = new ArrayList<UserProfile>();

        int iRow = c.getColumnIndex(UserDBHandler.KEY_ROWID);
        int iUsername = c.getColumnIndex(UserDBHandler.KEY_USERNAME);
        int iGender = c.getColumnIndex(UserDBHandler.KEY_GENDER);
        int iAge = c.getColumnIndex(UserDBHandler.KEY_AGE);
        int iSurgerytype = c.getColumnIndex(UserDBHandler.KEY_SURGERYTYPE);
        int iSurgerydate = c.getColumnIndex(UserDBHandler.KEY_SURGERYDATE);
        int iCreatedate = c.getColumnIndex(UserDBHandler.KEY_CREATEDATE);


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
        return mDB.update(UserDBHandler.TABLE_PROFILE, cvUpdate,
                UserDBHandler.KEY_ROWID + "=" + userprofile.getID(), null);
    }

    public void deleteProfileEntry(long lRow1) throws SQLException {
        // TODO Auto-generated method stub
        mDB.delete(UserDBHandler.TABLE_PROFILE, UserDBHandler.KEY_ROWID + "=" + lRow1, null);
    }


    public long createProgressEntry(UserProgress userprogress) {
        // TODO Auto-generated method stub

        ContentValues cv = setContentUserprogress(userprogress);

        return mDB.insert(UserDBHandler.TABLE_PROGRESS, null, cv);
    }


    private ContentValues setContentUserprogress(UserProgress userprogress) {

        ContentValues cv = new ContentValues();
        cv.put(UserDBHandler.KEY_CATID, userprogress.getcatID());
        cv.put(UserDBHandler.KEY_COMPLETE, userprogress.getComplete());
        cv.put(UserDBHandler.KEY_WEEKNUM, userprogress.getWeeknum());
        cv.put(UserDBHandler.KEY_DAYNUM, userprogress.getDaynum());
        cv.put(UserDBHandler.KEY_DAYDATE, dateFormat.format(userprogress.getdDaydate()));
        cv.put(UserDBHandler.KEY_RANGEDGREE, userprogress.getRange());

        return cv;
    }

    public List<UserProgress> getProgressData() {
        // TODO Date Conversion
        String[] columns = new String[] {
                UserDBHandler.KEY_CATID,
                UserDBHandler.KEY_COMPLETE,
                UserDBHandler.KEY_WEEKNUM,
                UserDBHandler.KEY_DAYNUM,
                UserDBHandler.KEY_DAYDATE,
                UserDBHandler.KEY_RANGEDGREE
        };
        Cursor c = mDB.query(UserDBHandler.TABLE_PROFILE, columns, null, null, null,
                null, null);
        ArrayList<UserProgress> list = new ArrayList<UserProgress>();

        int iCatid = c.getColumnIndex(UserDBHandler.KEY_CATID);
        int iComplete = c.getColumnIndex(UserDBHandler.KEY_COMPLETE);
        int iWeeknum = c.getColumnIndex(UserDBHandler.KEY_WEEKNUM);
        int iDaynum = c.getColumnIndex(UserDBHandler.KEY_DAYNUM);
        int iDaydate = c.getColumnIndex(UserDBHandler.KEY_DAYDATE);
        int iRangedgree = c.getColumnIndex(UserDBHandler.KEY_RANGEDGREE);



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
        return mDB.update(UserDBHandler.TABLE_PROGRESS, cvUpdate,
                UserDBHandler.KEY_CATID + "=" + userprogress.getcatID()
                + " AND " + UserDBHandler.KEY_WEEKNUM + "=" + userprogress.getWeeknum()
                + " AND " + UserDBHandler.KEY_DAYNUM + "=" + userprogress.getDaynum(), null);
    }


}

