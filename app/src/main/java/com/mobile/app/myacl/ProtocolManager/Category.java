package com.mobile.app.myacl.ProtocolManager;

import com.mobile.app.myacl.ProtocolManager.ExerciseManager.Exercise;

import java.util.List;

/**
 * Created by pirave on 15-02-16.
 */
public class Category {
    private String description;
    private List<Exercise> exercises;

    public Category(String description, List<Exercise> exercises) {
        this.description = description;
        this.exercises = exercises;
    }

    public String getDescription() {
        return description;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
