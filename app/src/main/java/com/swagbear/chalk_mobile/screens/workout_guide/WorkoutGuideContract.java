package com.swagbear.chalk_mobile.screens.workout_guide;

import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.entities.Workout;

import java.util.List;

public interface WorkoutGuideContract {

    interface Model {

        void pullWorkout(int id);

        Workout getWorkoutForGuide();

        int getActiveExercisePosition();

        void setActiveExercisePosition(int activeExercisePosition);

    }

    interface View {

        void setWorkoutName(String workoutName);

        void setExerciseList(List<Exercise> exerciseList);

        void nextExerciseButtonClicked();

        void nextWorkout(int position);

        void navigateToHomeScreen();

        void changeNextButtonText();

    }

    interface Presenter {

        void setView(View view);

        void populateWorkoutInfo(int workoutID);

        void progressThroughWorkout();

    }
}
