package com.swagbear.chalk_mobile.screens.create_workout;

import com.swagbear.chalk_mobile.entities.ChalkSet;
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.entities.Workout;
import com.swagbear.chalk_mobile.service.ChalkService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateWorkoutModel implements CreateWorkoutContract.Model {

    private ChalkService chalkService;

    private Workout workout;

    private Map<Exercise,List<ChalkSet>> workoutExercises;

    private List<ChalkSet> chalkSetsToDelete;

    private List<Exercise> allExercises;

    private boolean isEdit;

    public CreateWorkoutModel(ChalkService chalkService) {
        this.chalkService = chalkService;
        workout = new Workout();
        workout.setExerciseSetList(new HashMap<Exercise, List<ChalkSet>>());
    }

    @Override
    public void saveWorkOut(Workout workout) {
        chalkService.createWorkout(workout);
    }

    @Override
    public void updateWorkout() {
        chalkService.updateWorkout(getWorkout());
        chalkService.deleteSets(getChalkSetsToDelete());
    }

    @Override
    public Map<Exercise,List<ChalkSet>> getWorkoutExercises() {
        if(workoutExercises == null) {
            workoutExercises = new HashMap<>();
        }
        return workoutExercises;
    }

    @Override
    public void setWorkoutExercises(Map<Exercise, List<ChalkSet>> workoutExercises) {
        this.workoutExercises = workoutExercises;
    }

    @Override
    public List<Exercise> getAllExercises() {
        if(allExercises == null) {
            allExercises = chalkService.getExerciseList();
        }
        return allExercises;
    }

    @Override
    public List<ChalkSet> getChalkSetsToDelete() {
        if(chalkSetsToDelete == null) {
            chalkSetsToDelete = new ArrayList<>();
        }
        return chalkSetsToDelete;
    }

    @Override
    public boolean isEdit() {
        return isEdit;
    }

    @Override
    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }

    @Override
    public void pullWorkout(int id) {
        workout = this.chalkService.getWorkoutById(id);
    }

    @Override
    public Workout getWorkout() {
        return workout;
    }

    @Override
    public void deleteWorkout(Workout workoutToDelete) {
        chalkService.deleteWorkout(workoutToDelete);
    }
}