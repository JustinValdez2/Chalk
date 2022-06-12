package com.swagbear.chalk_mobile.screens.home_screen;

import com.swagbear.chalk_mobile.entities.Workout;

import java.util.List;

public interface HomeScreenContract {

    interface Model{

        List<Workout> getWorkouts();

    }

    interface View {

        void navigateToWorkoutGuide(int workoutId);

        void populateWorkoutDropDown(List<Workout> workouts);

    }

    interface Presenter {

        void setView(HomeScreenContract.View view);

        void populateWorkoutList();

        void startWorkoutClicked(int selectedWorkoutId);

    }
}
