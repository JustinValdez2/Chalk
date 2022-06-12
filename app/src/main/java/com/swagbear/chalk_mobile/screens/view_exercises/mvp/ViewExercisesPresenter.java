package com.swagbear.chalk_mobile.screens.view_exercises.mvp;

import android.support.annotation.Nullable;

import com.swagbear.chalk_mobile.entities.Exercise;

public class ViewExercisesPresenter implements ViewExercisesContract.Presenter {

    @Nullable
    private ViewExercisesContract.View view;
    private ViewExercisesContract.Model model;

    public ViewExercisesPresenter(ViewExercisesContract.Model model) {
        this.model = model;
        this.model.populateExerciseList();
    }


    @Override
    public void setView(ViewExercisesContract.View view) {
        this.view = view;
    }

    @Override
    public void addExerciseClicked() {
        view.navigateToCreateExerciseScreen();
    }

    @Override
    public void fillExerciseList() {
        view.populateExerciseList(model.getExerciseList());
    }

    @Override
    public void exerciseListClicked(Exercise exercise) {
        view.navigateToViewExerciseScreen(exercise.getId());
    }
}
