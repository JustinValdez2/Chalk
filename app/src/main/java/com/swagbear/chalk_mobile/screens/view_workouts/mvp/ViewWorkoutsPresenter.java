package com.swagbear.chalk_mobile.screens.view_workouts.mvp;

import android.support.annotation.Nullable;

import com.swagbear.chalk_mobile.entities.Workout;

import java.util.List;

public class ViewWorkoutsPresenter implements ViewWorkoutsContract.Presenter {

    @Nullable
    private ViewWorkoutsContract.View view;
    private ViewWorkoutsContract.Model model;

    public ViewWorkoutsPresenter(ViewWorkoutsContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(ViewWorkoutsContract.View view) {
        this.view = view;
    }

    @Override
    public void populateWorkoutsList() {
        model.retrieveWorkouts();
        List<Workout> workouts = model.getWorkouts();
        view.displayWorkouts(workouts);
    }

    @Override
    public void addWorkoutClicked() {
        view.navigateToCreateWorkout();
    }

    @Override
    public void workoutItemClicked(Workout workout) {
        view.navigateToViewWorkout(workout.getId());
    }
}
