package com.swagbear.chalk_mobile.screens.view_workouts.mvp;

import com.swagbear.chalk_mobile.entities.Workout;

import java.util.List;

public interface ViewWorkoutsContract {

    public interface Model {

        void retrieveWorkouts();

        List<Workout> getWorkouts();

    }

    public interface View {

        void displayWorkouts(List<Workout> workouts);

        void navigateToViewWorkout(int workoutId);

        void navigateToCreateWorkout();

        void createWorkoutButtonClick();

        void workoutListItemClick();

    }

    public interface Presenter {

        void setView(ViewWorkoutsContract.View view);

        void populateWorkoutsList();

        void addWorkoutClicked();

        void workoutItemClicked(Workout workout);



    }

}
