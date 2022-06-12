package com.swagbear.chalk_mobile.screens.view_workout.mvp;

import android.support.annotation.Nullable;

import com.swagbear.chalk_mobile.entities.ChalkSet;
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.entities.Workout;

import java.util.ArrayList;
import java.util.List;

public class ViewWorkoutPresenter implements ViewWorkoutContract.Presenter {

    @Nullable
    private ViewWorkoutContract.View view;
    private ViewWorkoutContract.Model model;

    public ViewWorkoutPresenter(ViewWorkoutContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(ViewWorkoutContract.View view) {
        this.view = view;
    }

    @Override
    public void displayWorkoutDetails(int workoutId) {
        if (model.getWorkout() == null) {
            model.retrieveWorkout(workoutId);
        }

        Workout workout = model.getWorkout();
        List<Exercise> exercises = new ArrayList<>();
        exercises.addAll(workout.getExerciseSetList().keySet());
        view.setViewWorkoutName(workout.getName());
        view.setViewWorkoutIntensity(workout.getIntensity());
        view.setViewWorkoutDescription(workout.getDescription());
        view.populateChalkSetList(exercises);
    }

    @Override
    public void navigateToViewWorkouts() {
        view.navigateToViewWorkouts();
    }

    @Override
    public void deleteWorkout(int workoutId) {

    }

    @Override
    public void navigateToEditWorkout() {

    }

    @Override
    public void chalkSetListClicked(ChalkSet clickedSet) {
        view.displayClickedChalkSet(clickedSet);
    }

    @Override
    public void editWorkoutClicked() {
        view.navigateToEditWorkout(model.getWorkout().getId());
    }
}
