package com.swagbear.chalk_mobile.screens.create_workout;

import com.swagbear.chalk_mobile.entities.ChalkSet;
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.entities.Workout;

import java.util.List;
import java.util.Map;

public interface CreateWorkoutContract {

    interface Model {

        void saveWorkOut(Workout workout);

        Workout getWorkout();

        Map<Exercise,List<ChalkSet>> getWorkoutExercises();

        void setWorkoutExercises(Map<Exercise,List<ChalkSet>> workoutExercises);

        List<ChalkSet> getChalkSetsToDelete();

        List<Exercise> getAllExercises();

        boolean isEdit();

        void setIsEdit(boolean isEdit);

        void pullWorkout(int id);

        void updateWorkout();

        void deleteWorkout(Workout workoutToDelete);
    }

    interface View {

        void navigateToViewWorkoutsScreen();

        String getWorkoutName();

        void setWorkoutName(String workoutName);

        String getIntensity();

        void setIntensity(String intensity);

        boolean isFavorite();

        void setIsFavorite(boolean isFavorite);

        String getWorkoutDescription();

        void setWorkoutDescription(String workoutDescription);

        void updateWorkoutExerciseList(List<Exercise> exerciseList);

        void notifyUser(String msg);

        void setDeleteButtonVisibility(boolean visible);

    }

    interface Presenter {

        void setView(CreateWorkoutContract.View view);

        List<Exercise> getSelectExerciseList();

        void saveWorkout();

        void cancelSaveWorkout();

        void addExercise(Exercise exercise);

        void deleteExercise(Exercise exercise);

        void updateAddExerciseList();

        void populateWorkoutInfoForEdit(int workoutID);

        void deleteWorkoutClicked();
    }
}
