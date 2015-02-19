package com.mobile.app.myacl.UserManager;

import java.io.Serializable;

/**
 * Created by Alaa on 2/15/2015.
 */

public final class UserProfile implements Serializable {
    private static UserProfile instance = null;

    private UserProfile() {
        // Exists only to defeat instantiation.
    }

    public static UserProfile getInstance() {
        if(instance == null) {
            instance = new UserProfile();
        }
        return instance;
    }

    private String ID;
    private String username;
    private String gender;
    private int age;
    private String surgerytype;
    private java.util.Date surgerydate;
    private java.util.Date createdate;

    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        instance.ID = iD;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username){
        instance.username=username;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender){
        instance.gender=gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age){
        instance.age=age;
    }
    public String getSurgeryType() {
        return surgerytype;
    }
    public void setSurgeryType(String surgerytype){
        instance.surgerytype=surgerytype;
    }
    public java.util.Date getSurgeryDate() {
        return surgerydate;
    }
    public void setSurgeryDate(java.util.Date surgerydate) {
        instance.surgerydate = surgerydate;
    }
    public java.util.Date getCreateDate() {
        return createdate;
    }
    public void setCreateDate(java.util.Date createdate) {
        instance.createdate = createdate;
    }

}
