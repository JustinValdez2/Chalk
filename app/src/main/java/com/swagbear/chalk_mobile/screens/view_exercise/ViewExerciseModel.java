package com.swagbear.chalk_mobile.screens.view_exercise;

import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.service.ChalkService;

public class ViewExerciseModel implements ViewExerciseContract.Model {


    private Exercise exercise;

    private ChalkService chalkService;

    public ViewExerciseModel(ChalkService chalkService) {
        this.chalkService = chalkService;
    }

    @Override
    public Exercise getExercise() {
        return exercise;
    }

    @Override
    public void retrieveExercise(int id) {
        Exercise exercise = chalkService.getExerciseById(id);

        if (exercise == null) {
            exercise = new Exercise();
        }

        this.exercise = exercise;
    }
}
