package com.swagbear.chalk_mobile.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.swagbear.chalk_mobile.entities.ChalkSet;
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.entities.Workout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Used to manage data from the Workout table
 */
@Dao
public abstract class WorkoutDao {

    /**
     * Scripts used to manage Workout
     */
    @Query("select * from workout")
    abstract List<Workout> getAllWorkouts();

    @Query("select * from workout where id = :workoutId")
    abstract Workout getWorkoutById(long workoutId);

    @Insert
    abstract long[] insertWorkout(Workout... workouts);

    @Update
    public abstract void updateWorkout(Workout workout);

    @Delete
    public abstract void deleteWorkout(Workout workout);

    /**
     * Scripts used to manage ChalkSet list for Workout
     */
    @Query("select * from ChalkSet where workoutId = :workoutId")
    abstract List<ChalkSet> getAllChalkSetsForWorkout(int workoutId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insertChalkSet(ChalkSet set);

    @Update()
    public abstract void updateChalkSet(ChalkSet set);

    @Delete
    public abstract void deleteChalkSet(ChalkSet... set);

    @Delete
    public abstract void deleteChalkSets(List<ChalkSet> set);

    @Query("select * from exercise where id = :id")
    public abstract Exercise getExerciseById(long id);

    /**
     * Used to insert a workout and its sets into the respective tables
     * @param workout Workout object that contains Chalksets
     * @return the id of the workout after it has be inserted in the table
     */
    public int insertWorkoutWithSets(Workout workout) {

        Map<Exercise,List<ChalkSet >> chalkSets = workout.getExerciseSetList();

        int workoutId = (int) insertWorkout(workout)[0];
        workout.setId(workoutId);

        if(null != chalkSets && !chalkSets.isEmpty()) {
            for(List<ChalkSet> chalkSetsList : chalkSets.values()) {
                for (ChalkSet chalkSet : chalkSetsList) {
                    chalkSet.setWorkoutId(workoutId);
                    int id = (int) insertChalkSet(chalkSet);
                    chalkSet.setId(id);
                }
            }
        }

        //Todo: Rollback created workout call if there is an error with the sets
        return  workoutId;
    }

    public void updateWorkoutAndChalkSets(Workout workout) {
        Map<Exercise, List<ChalkSet>> chalkSets = workout.getExerciseSetList();
        updateWorkout(workout);

        if (null != chalkSets && !chalkSets.isEmpty()) {
            for (List<ChalkSet> chalkSetsList : chalkSets.values()) {
                for (ChalkSet chalkSet : chalkSetsList) {
                    chalkSet.setWorkoutId(workout.getId());
                    int id = (int) insertChalkSet(chalkSet);
                    chalkSet.setId(id);
                }
            }
        }
    }

    /**
     * Used to retrieve a workout with all of its chalksets
     * @param id the id of the workout to retrieve
     * @return Workout requested
     */
    public Workout getWorkoutWithChalkSets(int id) {
        Workout workout = getWorkoutById(id);
        Map<Exercise,List<ChalkSet>> workoutChalkSetList;

        if(null != workout) {
            List<ChalkSet> allChalkSets = new ArrayList<>(getAllChalkSetsForWorkout(workout.getId()));
            workoutChalkSetList = convertChalkSetListToMap(allChalkSets);
            workout.setExerciseSetList(workoutChalkSetList);
        }

        return workout;
    }

    public List<Workout> getAllWorkoutsAndSets() {
        List<Workout>workouts = getAllWorkouts();

        for(Workout workout: workouts) {
            List<ChalkSet> chalkSets = new ArrayList<>(getAllChalkSetsForWorkout(workout.getId()));
            workout.setExerciseSetList(convertChalkSetListToMap(chalkSets));
        }

        return workouts;
    }

    private Map<Exercise,List<ChalkSet>> convertChalkSetListToMap(List<ChalkSet> chalkSets) {
        Map<Exercise,List<ChalkSet>> workoutChalkSetList = new HashMap<>();

        Exercise exercise = new Exercise();
        for(ChalkSet chalkSet : chalkSets) {
            if(chalkSet.getExerciseId()!= exercise.getId()){
                exercise = getExerciseById(chalkSet.getExerciseId());
            }
            if(!workoutChalkSetList.containsKey(exercise)) {
                 workoutChalkSetList.put(exercise, new ArrayList<ChalkSet>());
            }
            workoutChalkSetList.get(exercise).add(chalkSet);

        }
        return workoutChalkSetList;
    }
}
