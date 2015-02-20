package com.mobile.app.myacl.PlanManager;

import android.os.Parcel;
import android.os.Parcelable;

import com.mobile.app.myacl.ProtocolManager.Week;
import com.mobile.app.myacl.UserManager.UserProfile;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by pirave on 15-02-17.
 */
public class Plan implements Parcelable, Serializable{
    private String type;
    private TreeMap<Date,Week> weeks;

    public Plan() {
        this.type = UserProfile.getInstance().getSurgeryType();
        this.weeks = new TreeMap<>();
    }

    public Plan(String type, TreeMap<Date, Week> weeks) {
        this.type = type;
        this.weeks = weeks;
    }

    public String getType() {
        return type;
    }

    public Map<Date, Week> getWeeks() {
        return weeks;
    }

    public void addWeek(Date d, Week w){
        weeks.put(d, w);
    }

    public Week getWeekByDate(Date d){
        return weeks.floorEntry(d).getValue();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
