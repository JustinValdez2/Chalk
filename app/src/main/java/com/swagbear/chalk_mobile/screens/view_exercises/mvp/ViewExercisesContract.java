package com.swagbear.chalk_mobile.screens.view_exercises.mvp;

import com.swagbear.chalk_mobile.entities.Exercise;

import java.util.ArrayList;
import java.util.List;

public interface ViewExercisesContract {

     interface Model {

        void populateExerciseList();

        List<Exercise> getExerciseList();
    }

     interface View {

        void populateExerciseList(List<Exercise> exercises);

        void navigateToCreateExerciseScreen();

        void navigateToViewExerciseScreen(int exerciseId);

    }

     interface Presenter {

        void setView(ViewExercisesContract.View view);

        void addExerciseClicked();

        void fillExerciseList();

        void exerciseListClicked(Exercise exercise);
    }

}
