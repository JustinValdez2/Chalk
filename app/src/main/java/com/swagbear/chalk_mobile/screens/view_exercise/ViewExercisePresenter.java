package com.swagbear.chalk_mobile.screens.view_exercise;

import android.support.annotation.Nullable;

import com.swagbear.chalk_mobile.enums.TargetArea;

public class ViewExercisePresenter implements ViewExerciseContract.Presenter {


    @Nullable
    private ViewExerciseContract.View view;
    private ViewExerciseContract.Model model;

    public ViewExercisePresenter(ViewExerciseContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(ViewExerciseContract.View view) {
        this.view = view;
    }


    @Override
    public void displayExercise(int id) {
        model.retrieveExercise(id);
        view.setViewExerciseName(model.getExercise().getName());
        view.setViewExerciseDescription(model.getExercise().getInstructions());
        view.setViewExerciseType(model.getExercise().getExerciseType().getName());

        StringBuilder targetAreas = new StringBuilder();
        for(TargetArea area: model.getExercise().getTargetAreas()) {
            targetAreas.append(area + ", ");
            targetAreas.deleteCharAt(targetAreas.lastIndexOf(", "));
        }
        view.setViewExerciseTargetAreas(targetAreas.toString());

    }

    @Override
    public void btnEditExerciseClicked() {
        view.navigateToEditExercise(model.getExercise().getId());

    }

    @Override
    public void navigateToViewExercises() {
        view.navigateToViewExercises();
    }
}
