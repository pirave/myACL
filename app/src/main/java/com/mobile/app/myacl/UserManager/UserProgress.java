package com.mobile.app.myacl.UserManager;

import java.io.Serializable;

/**
 * Created by Alaa on 2/15/2015.
 */

public class UserProgress implements Serializable {
    private int catid;
    private int complete;
    private int weeknum;
    private int daynum;
    private java.util.Date daydate;
    private int rangedgree;


    public int getcatID() {
        return catid;
    }

    public void setID(int iD) {
        this.catid = catid;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public int getWeeknum() {
        return weeknum;
    }

    public void setWeeknum(int weeknum) {
        this.weeknum = weeknum;
    }

    public int getDaynum() {
        return daynum;
    }

    public void setDaynum(int daynum) {
        this.daynum = daynum;
    }

    public java.util.Date getdDaydate() {
        return daydate;
    }

    public void setDaydate(java.util.Date daydate) {
        this.daydate = daydate;
    }

    public int getRange() {
        return rangedgree;
    }

    public void setRange(int rangedgree) {
        this.rangedgree = rangedgree;
    }

}