package com.swagbear.chalk_mobile.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.swagbear.chalk_mobile.enums.ExerciseType;
import com.swagbear.chalk_mobile.enums.TargetArea;

import java.util.ArrayList;

/**
 * Created by valju on 7/12/2018.
 */

@Entity
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private ExerciseType exerciseType;

    private String instructions;

    private boolean favorite;

    private boolean userDefined;

    private ArrayList<TargetArea> targetAreas;


    public Exercise() {

    }

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

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isUserDefined() {
        return userDefined;
    }

    public void setUserDefined(boolean userDefined) {
        this.userDefined = userDefined;
    }

    public ArrayList<TargetArea> getTargetAreas() {
        return targetAreas;
    }

    public void setTargetAreas(ArrayList<TargetArea> targetAreas) {
        this.targetAreas = targetAreas;
    }

    @Override
    public String toString() {
        return name;
    }
}
