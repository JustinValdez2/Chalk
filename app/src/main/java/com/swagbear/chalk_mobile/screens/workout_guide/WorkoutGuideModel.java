package com.swagbear.chalk_mobile.screens.workout_guide;

import com.swagbear.chalk_mobile.entities.Workout;
import com.swagbear.chalk_mobile.service.ChalkService;

public class WorkoutGuideModel implements WorkoutGuideContract.Model {

    private ChalkService chalkService;

    private Workout workoutForGuide;

    private int activeExercisePosition;

    public WorkoutGuideModel(ChalkService chalkService) {
        this.chalkService = chalkService;
    }

    @Override
    public void pullWorkout(int id) {
        workoutForGuide = chalkService.getWorkoutById(id);
    }

    @Override
    public Workout getWorkoutForGuide() {
        return workoutForGuide;
    }

    @Override
    public int getActiveExercisePosition() {
        return activeExercisePosition;
    }

    @Override
    public void setActiveExercisePosition(int activeExercisePosition) {
        this.activeExercisePosition = activeExercisePosition;
    }
}
