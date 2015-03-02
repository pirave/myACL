package com.mobile.app.myacl.PlanManager;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.mobile.app.myacl.ProtocolManager.Week;
import com.mobile.app.myacl.UserManager.UserProfile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by pirave on 15-02-17.
 */
public class Plan implements Parcelable, Serializable{
    private String type;
    private TreeMap<Date,Week> weeks;

    public Plan(Context context) {
        this.type = UserProfile.getInstance(context).getSurgeryType();
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

    public List<Week> getWeeksList(){
        return new ArrayList<Week>(weeks.values());
    }

    public Date getLastWeek(){
        return weeks.lastKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
