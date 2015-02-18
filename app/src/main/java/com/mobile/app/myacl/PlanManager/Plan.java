package com.mobile.app.myacl.PlanManager;

import android.os.Parcel;
import android.os.Parcelable;

import com.mobile.app.myacl.ProtocolManager.Week;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pirave on 15-02-17.
 */
public class Plan implements Parcelable, Serializable{
    private String type;
    private List<Week> weeks;

    public Plan(String type) {
        this.type = type;
        this.weeks = new ArrayList<Week>();
    }

    public Plan(String type, List<Week> weeks) {
        this.type = type;
        this.weeks = weeks;
    }

    public String getType() {
        return type;
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public void addWeek(Week w){
        weeks.add(w);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
