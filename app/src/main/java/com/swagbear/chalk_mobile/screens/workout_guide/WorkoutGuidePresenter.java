package com.swagbear.chalk_mobile.screens.workout_guide;

import android.support.annotation.Nullable;

import com.swagbear.chalk_mobile.entities.Exercise;

import java.util.ArrayList;
import java.util.List;

public class WorkoutGuidePresenter implements WorkoutGuideContract.Presenter {

    @Nullable
    WorkoutGuideContract.View view;
    WorkoutGuideContract.Model model;

    private final int NEXT_EXERCISE = 1;

    public WorkoutGuidePresenter(WorkoutGuideContract.Model model) {
        this.model = model;
        model.setActiveExercisePosition(0);
    }

    @Override
    public void setView(WorkoutGuideContract.View view) {
        this.view = view;
    }

    @Override
    public void populateWorkoutInfo(int workoutID) {
        model.pullWorkout(workoutID);

        view.setWorkoutName(model.getWorkoutForGuide().getName());

        List<Exercise> exercises = new ArrayList<>();
        exercises.addAll(model.getWorkoutForGuide().getExerciseSetList().keySet());
        view.setExerciseList(exercises);
    }

    @Override
    public void progressThroughWorkout() {
        model.setActiveExercisePosition(model.getActiveExercisePosition() + NEXT_EXERCISE);

        if (model.getActiveExercisePosition() > model.getWorkoutForGuide().getExerciseSetList().size() - 1) {
            view.navigateToHomeScreen();
        } else {
            if (model.getActiveExercisePosition() == model.getWorkoutForGuide().getExerciseSetList().size() - 1) {
                view.changeNextButtonText();
            }
            view.nextWorkout(model.getActiveExercisePosition());
        }
    }
}
