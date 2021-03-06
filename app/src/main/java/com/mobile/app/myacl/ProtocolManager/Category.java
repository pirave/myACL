package com.mobile.app.myacl.ProtocolManager;

import com.mobile.app.myacl.ProtocolManager.ExerciseManager.Exercise;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pirave on 15-02-16.
 */
public class Category implements Serializable{
    private Integer id;
    private String description;
    private List<Exercise> exercises;

    public Category(Integer id, String description, List<Exercise> exercises) {
        this.id = id;
        this.description = description;
        this.exercises = exercises;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
