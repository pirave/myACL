package com.mobile.app.myacl.ProtocolManager.ExerciseManager;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pirave on 15-02-16.
 */
public class Exercise implements Serializable{
    private List<Step> steps;

    public Exercise(List<Step> steps) {
        this.steps = steps;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
