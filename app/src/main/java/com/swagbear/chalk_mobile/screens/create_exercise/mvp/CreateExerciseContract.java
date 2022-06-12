package com.swagbear.chalk_mobile.screens.create_exercise.mvp;

import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.enums.ExerciseType;
import com.swagbear.chalk_mobile.enums.TargetArea;

import java.util.ArrayList;

public interface CreateExerciseContract {

    interface Model {

        void saveExercise();

        void updateExercise();

        Exercise getExercise();

        void setExercise(Exercise exercise);

        boolean isEdit();

        void setIsEdit(boolean isEdit);

        void pullExercise(int id);

        void deleteExercise(Exercise exercise);

    }

    interface View {

        void navigateToViewExerciseScreen();

        void navigateToViewExercisesScreen();

        void notifyUser(String msg);

        void displayValidationErrors(String errors);

        String getExerciseName();

        void setExerciseName(String name);

        String getExerciseDescription();

        void setExerciseDescription(String description);

        ExerciseType getExerciseType();

        void setExerciseType(ExerciseType type);

        ArrayList<TargetArea> getTargetAreas();

        void setTargetAreas(ArrayList<TargetArea> targetAreas);

        boolean getIsFavorite();

        void setIsFavorite(boolean isFavorite);

        void onClickDeleteButton();
    }

    interface Presenter {

        void setView(CreateExerciseContract.View view);

        void saveExerciseClicked();

        void cancelClicked();

        void populateExerciseInfoForEdit(int exerciseId);

        void deleteExerciseButtonClicked();

    }

}
