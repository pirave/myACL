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
import java.util.Date;
import java.util.List;


@SuppressLint("SimpleDateFormat")
public class UserDB {


    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
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

        ContentValues cv = setContentUserProfile(userprofile);

        return mDB.insert(UserDBHandler.TABLE_PROFILE, null, cv);
    }

    private ContentValues setContentUserProfile(UserProfile userprofile) {

        ContentValues cv = new ContentValues();
        cv.put(UserDBHandler.KEY_USERID, userprofile.getID());
        cv.put(UserDBHandler.KEY_USERNAME, userprofile.getUsername());
        cv.put(UserDBHandler.KEY_GENDER, userprofile.getGender());
        cv.put(UserDBHandler.KEY_AGE, userprofile.getAge());
        cv.put(UserDBHandler.KEY_SURGERYTYPE, userprofile.getSurgeryType());
        cv.put(UserDBHandler.KEY_SURGERYDATE, dateFormat.format(userprofile.getSurgeryDate()));
        cv.put(UserDBHandler.KEY_CREATEDATE, dateFormat.format(userprofile.getCreateDate()));

        return cv;
    }

    public void openUserProfile(UserProfile userProfile){
        String[] columns = new String[] {
                UserDBHandler.KEY_USERID,
                UserDBHandler.KEY_USERNAME,
                UserDBHandler.KEY_GENDER,
                UserDBHandler.KEY_AGE,
                UserDBHandler.KEY_SURGERYTYPE,
                UserDBHandler.KEY_SURGERYDATE,
                UserDBHandler.KEY_CREATEDATE
        };
        Cursor c = mDB.query(UserDBHandler.TABLE_PROFILE, columns, null, null, null,
                null, null);

        int iUserid = c.getColumnIndex(UserDBHandler.KEY_USERID);
        int iUsername = c.getColumnIndex(UserDBHandler.KEY_USERNAME);
        int iGender = c.getColumnIndex(UserDBHandler.KEY_GENDER);
        int iAge = c.getColumnIndex(UserDBHandler.KEY_AGE);
        int iSurgerytype = c.getColumnIndex(UserDBHandler.KEY_SURGERYTYPE);
        int iSurgerydate = c.getColumnIndex(UserDBHandler.KEY_SURGERYDATE);
        int iCreatedate = c.getColumnIndex(UserDBHandler.KEY_CREATEDATE);

        try {
            c.moveToFirst();
            userProfile.setID(c.getString(iUserid));
            userProfile.setUsername(c.getString(iUsername));
            userProfile.setGender(c.getString(iGender));
            userProfile.setAge(c.getInt(iAge));
            userProfile.setSurgeryType(c.getString(iSurgerytype));
            try {
                userProfile.setSurgeryDate(dateFormat.parse(c.getString(iSurgerydate)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                userProfile.setCreateDate(dateFormat.parse(c.getString(iCreatedate)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch(Exception e){
            return;
        }

    }

    public long updateProfileEntry(UserProfile userprofile) throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cvUpdate = setContentUserProfile(userprofile);
        return mDB.update(UserDBHandler.TABLE_PROFILE, cvUpdate,
                UserDBHandler.KEY_USERID + "=" + userprofile.getID(), null);
    }

    public void deleteProfileEntry(long lRow1) throws SQLException {
        // TODO Auto-generated method stub
        mDB.delete(UserDBHandler.TABLE_PROFILE, UserDBHandler.KEY_USERID + "=" + lRow1, null);
    }

    /** USER PROGRESS DATA METHODS **/

    public long createProgressEntry(UserProgress userProgress) {
        // TODO Auto-generated method stub

        ContentValues cv = setContentUserProgress(userProgress);

        return mDB.insert(UserDBHandler.TABLE_PROGRESS, null, cv);
    }


    private ContentValues setContentUserProgress(UserProgress userProgress) {

        ContentValues cv = new ContentValues();
        cv.put(UserDBHandler.KEY_CATID, userProgress.getCatID());
        cv.put(UserDBHandler.KEY_COMPLETE, userProgress.isComplete() ? 1 : 0);
        cv.put(UserDBHandler.KEY_WEEKNUM, userProgress.getWeekNum());
        cv.put(UserDBHandler.KEY_DAYNUM, userProgress.getDayNum());
        cv.put(UserDBHandler.KEY_DAYDATE, dateFormat.format(userProgress.getDate()));
        if (userProgress.getRangeDegree() != null)
            cv.put(UserDBHandler.KEY_RANGEDGREE, userProgress.getRangeDegree());
        else
            cv.putNull(UserDBHandler.KEY_RANGEDGREE);

        return cv;
    }

    public List<UserProgress> getProgressData(Date date) {
        // TODO Date Conversion
        String[] columns = new String[] {
                UserDBHandler.KEY_CATID,
                UserDBHandler.KEY_COMPLETE,
                UserDBHandler.KEY_WEEKNUM,
                UserDBHandler.KEY_DAYNUM,
                UserDBHandler.KEY_DAYDATE,
                UserDBHandler.KEY_RANGEDGREE
        };
        Cursor c = mDB.query(
                UserDBHandler.TABLE_PROGRESS,
                columns,
                UserDBHandler.KEY_DAYDATE + "=?",
                new String[]{new SimpleDateFormat("dd-MM-yyyy").format(date)},
                null, null, null);
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
            userprogress.setCatID(c.getInt(iCatid));
            userprogress.setComplete(c.getInt(iComplete) > 0);
            userprogress.setWeekNum(c.getInt(iWeeknum));
            userprogress.setDayNum(c.getInt(iDaynum));
            try {
                userprogress.setDate(dateFormat.parse(c.getString(iDaydate)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            userprogress.setRangeDegree(c.getInt(iRangedgree));


            list.add(userprogress);
        }
        return list;
    }

    public long updateProgressEntry(UserProgress userprogress) throws SQLException {
        // TODO Auto-generated method stub
        ContentValues cvUpdate = setContentUserProgress(userprogress);
        return mDB.update(UserDBHandler.TABLE_PROGRESS, cvUpdate,
                UserDBHandler.KEY_CATID + "=" + userprogress.getCatID()
                + " AND " + UserDBHandler.KEY_WEEKNUM + "=" + userprogress.getWeekNum()
                + " AND " + UserDBHandler.KEY_DAYNUM + "=" + userprogress.getDayNum(), null);
    }


}

