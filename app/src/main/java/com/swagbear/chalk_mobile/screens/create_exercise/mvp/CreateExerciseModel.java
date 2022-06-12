package com.swagbear.chalk_mobile.screens.create_exercise.mvp;

import android.arch.lifecycle.ViewModel;

import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.service.ChalkService;

public class CreateExerciseModel extends ViewModel implements CreateExerciseContract.Model {


    private ChalkService chalkService;

    private Exercise exercise;

    private boolean isEdit;


    public CreateExerciseModel(ChalkService chalkService) {
        this.chalkService = chalkService;
        isEdit = false;
    }


    @Override
    public void saveExercise() {
        if (exercise != null) {
            chalkService.createExercise(exercise);
        }
    }

    @Override
    public void updateExercise() {
        if (exercise != null) {
            chalkService.updateExercise(exercise);
        }
    }

    @Override
    public void pullExercise(int id) {
        Exercise exercise = chalkService.getExerciseById(id);

        if (exercise == null) {
            exercise = new Exercise();
        }

        this.exercise = exercise;
    }

    @Override
    public void deleteExercise(Exercise exercise) {
        chalkService.deleteExercise(exercise);
    }

    @Override
    public Exercise getExercise() {
        return exercise;
    }

    @Override
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    public boolean isEdit() {
        return isEdit;
    }

    @Override
    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }
}
