package com.swagbear.chalk_mobile.screens.view_exercises.mvp;

import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.service.ChalkService;

import java.util.ArrayList;
import java.util.List;

public class ViewExercisesModel implements ViewExercisesContract.Model {

    private ChalkService chalkService;

    private List<Exercise> exercises;

    public ViewExercisesModel(ChalkService chalkService) {
        this.chalkService = chalkService;
    }

    @Override
    public void populateExerciseList() {
        this.exercises = chalkService.getExerciseList();
    }

    @Override
    public List<Exercise> getExerciseList() {
        return this.exercises;
    }
}
