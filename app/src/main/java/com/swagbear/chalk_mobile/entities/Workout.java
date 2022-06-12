package com.swagbear.chalk_mobile.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by valju on 7/12/2018.
 */
@Entity
public class Workout {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String description;

    private int intensity;

    private boolean favorite;

    @Ignore
    private Map<Exercise, List<ChalkSet>> exerciseSetList;

    public Workout() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public Map<Exercise, List<ChalkSet>> getExerciseSetList() {
        return exerciseSetList;
    }

    public void setExerciseSetList(Map<Exercise, List<ChalkSet>> exerciseSetList) {
        this.exerciseSetList = exerciseSetList;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
