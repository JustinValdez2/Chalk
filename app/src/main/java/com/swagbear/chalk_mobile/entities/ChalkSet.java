package com.swagbear.chalk_mobile.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;


/**
 * ChalkSet Class that holds data that represents sets stored in a workout
 *
 * Entity: Table name is ChalkSet
 * Foreign Keys: When stored this entity will need both and Exercise and Workout to be associated with
 *
 * On Delete: ChalkSet record will be deleted when either its exercise or workout gets deleted
 */
@Entity(foreignKeys = {@ForeignKey(entity = Exercise.class
                                    ,parentColumns = "id", childColumns = "exerciseId", onDelete = CASCADE),
                        @ForeignKey(entity = Workout.class, parentColumns = "id", childColumns = "workoutId", onDelete = CASCADE)}
, indices = {@Index(value = {"exerciseId"}), @Index(value = {"workoutId"})})
public class ChalkSet {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int workoutId;

    private int exerciseId;

    private int order;

    private double distance;

    private long time;

    private float weight;

    private int reps;


    public ChalkSet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
