package com.mobile.app.myacl.UserDatabase;

import java.io.Serializable;

/**
 * Created by Alaa on 2/15/2015.
 */

public class UserProfile implements Serializable {
    private int ID;
    private String username;
    private String gender;
    private int age;
    private String surgerytype;
    private java.util.Date surgerydate;
    private java.util.Date createdate;



    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        this.ID = iD;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender){
        this.gender=gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    public String getSurgeryType() {
        return surgerytype;
    }
    public void setSurgeryType(String surgerytype){
        this.surgerytype=surgerytype;
    }
    public java.util.Date getSurgeryDate() {
        return surgerydate;
    }
    public void setSurgeryDate(java.util.Date surgerydate) {
        this.surgerydate = surgerydate;
    }
    public java.util.Date getCreateDate() {
        return createdate;
    }
    public void setCreateDate(java.util.Date createdate) {
        this.createdate = createdate;
    }

}
