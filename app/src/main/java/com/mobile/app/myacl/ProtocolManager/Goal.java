package com.mobile.app.myacl.ProtocolManager;

import java.io.Serializable;

/**
 * Created by pirave on 15-02-16.
 */
public class Goal implements Serializable{
    private String description;

    public Goal(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
