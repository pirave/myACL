package com.mobile.app.myacl.UserManager;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Alaa on 2/15/2015.
 */

public class UserProgress implements Serializable {
    private int catID;
    private boolean complete;
    private int weekNum;
    private int dayNum;
    private Date date;
    private Integer rangeDegree;

    public UserProgress() {

    }

    public UserProgress(int catID, boolean complete, int weekNum, int dayNum, Date date) {
        this.catID = catID;
        this.complete = complete;
        this.weekNum = weekNum;
        this.dayNum = dayNum;
        this.date = date;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getRangeDegree() {
        return rangeDegree;
    }

    public void setRangeDegree(Integer rangeDegree) {
        this.rangeDegree = rangeDegree;
    }
}