package com.mobile.app.myacl.ProtocolManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by pirave on 15-02-16.
 */
public class Week implements Serializable{
    private int num;
    private List<Goal> goals;
    private Map<Integer, Category> categories;//List<Category> categories;
    private Date date;

    public Week(int num, List<Goal> goals, Map<Integer, Category> categories) {
        this.num = num;
        this.goals = goals;
        this.categories = categories;
    }

    public int getNum() {
        return num;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public Map<Integer, Category> getCategories() {
        return categories;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Category> getCategoryList() {
        return new ArrayList<>(categories.values());
    }
}
