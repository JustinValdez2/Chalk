package com.swagbear.chalk_mobile.screens.view_workouts.mvp;

import com.swagbear.chalk_mobile.entities.Workout;
import com.swagbear.chalk_mobile.service.ChalkService;

import java.util.ArrayList;
import java.util.List;

public class ViewWorkoutsModel implements ViewWorkoutsContract.Model {

    private ChalkService chalkService;

    private List<Workout> workoutList;

    public ViewWorkoutsModel(ChalkService chalkService) {
        this.chalkService = chalkService;
    }

    @Override
    public void retrieveWorkouts() {
        workoutList = chalkService.getWorkoutList();
    }

    @Override
    public List<Workout> getWorkouts() {
        if(workoutList == null) {
            workoutList = new ArrayList<>();
        }
        return workoutList;
    }
}
