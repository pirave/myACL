package com.mobile.app.myacl.PlanManager;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.mobile.app.myacl.ProtocolManager.Week;
import com.mobile.app.myacl.UserManager.UserProfile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    public List<Date> getWeekDaysByDate(Date startDate){
        List<Date> days = new ArrayList<>();
        ArrayList<Date> keys = new ArrayList(weeks.keySet());
        startDate = weeks.floorKey(startDate);
        int next = keys.indexOf(startDate) + 1;

        Date endDate;
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        if (next < keys.size())
            endDate = keys.get(next);
        else{
            cal.add(Calendar.DATE, 7);
            endDate = cal.getTime();
            cal.add(Calendar.DATE, -7);
        }

        while (cal.getTime().before(endDate)) {
            days.add(cal.getTime());
            cal.add(Calendar.DATE, 1);
        }

        return days;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
