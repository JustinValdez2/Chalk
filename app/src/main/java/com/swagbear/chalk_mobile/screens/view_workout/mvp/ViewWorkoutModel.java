package com.swagbear.chalk_mobile.screens.view_workout.mvp;

import com.swagbear.chalk_mobile.entities.Workout;
import com.swagbear.chalk_mobile.service.ChalkService;

public class ViewWorkoutModel implements ViewWorkoutContract.Model {

    private ChalkService chalkService;

    private Workout workout;

    public ViewWorkoutModel(ChalkService chalkService) {
        this.chalkService = chalkService;
    }

    @Override
    public Workout getWorkout() {
        return workout;
    }

    @Override
    public void retrieveWorkout(int workoutId) {
        workout = chalkService.getWorkoutById(workoutId);
    }
}
