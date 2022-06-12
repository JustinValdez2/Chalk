package com.swagbear.chalk_mobile.screens.view_exercise;

import com.swagbear.chalk_mobile.entities.Exercise;

public interface ViewExerciseContract {

    interface Model {
        Exercise getExercise();

        void retrieveExercise(int id);
    }

    interface View {

        void navigateToViewExercises();

        void navigateToEditExercise(int exerciseId);

        void setViewExerciseName(String name);

        void setViewExerciseDescription(String description);

        void setViewExerciseType(String type);

        void setViewExerciseTargetAreas(String areas);

    }

    interface Presenter {

        void setView(ViewExerciseContract.View view);

        void displayExercise(int id);

        void navigateToViewExercises();

        void btnEditExerciseClicked();
    }
}
