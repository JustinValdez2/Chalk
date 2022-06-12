package com.swagbear.chalk_mobile.screens.view_workout.mvp;

import com.swagbear.chalk_mobile.entities.ChalkSet;
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.entities.Workout;

import java.util.List;

public interface ViewWorkoutContract {

    interface Model {

        Workout getWorkout();

        void retrieveWorkout(int workoutId);
    }

    interface View {

        void navigateToViewWorkouts();

        void navigateToEditWorkout(int workoutId);

        void setViewWorkoutName(String workoutName);

        void setViewWorkoutIntensity(int intensity);

        void setViewWorkoutDescription(String workoutDesc);

        void populateChalkSetList(List<Exercise> chalkSets);

        void btnDeleteWorkoutClicked();

        void displayClickedChalkSet(ChalkSet clickedSet);
    }

    interface Presenter {

        void setView(ViewWorkoutContract.View view);

        void displayWorkoutDetails(int workoutId);

        void navigateToViewWorkouts();

        void deleteWorkout(int workoutId);

        void navigateToEditWorkout();

        void chalkSetListClicked(ChalkSet clickedSet);

        void editWorkoutClicked();
    }
}
