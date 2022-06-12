package com.swagbear.chalk_mobile.dependency_injection;

import android.app.Application;

import com.swagbear.chalk_mobile.screens.create_workout.CreateWorkoutModule;
import com.swagbear.chalk_mobile.screens.create_exercise.di.CreateExerciseModule;

import com.swagbear.chalk_mobile.screens.home_screen.di.HomeScreenModule;
import com.swagbear.chalk_mobile.screens.view_exercise.di.ViewExerciseModule;
import com.swagbear.chalk_mobile.screens.view_exercises.di.ViewExercisesModule;
import com.swagbear.chalk_mobile.screens.view_workout.di.ViewWorkoutModule;
import com.swagbear.chalk_mobile.screens.view_workouts.di.ViewWorkoutsModule;
import com.swagbear.chalk_mobile.screens.workout_guide.di.WorkoutGuideModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .createExerciseModule(new CreateExerciseModule())
                .roomModule(new RoomModule(this))
                .viewExercisesModule(new ViewExercisesModule())
                .viewExerciseModule(new ViewExerciseModule())
                .viewWorkoutsModule(new ViewWorkoutsModule())
                .createWorkoutModule(new CreateWorkoutModule())
                .viewWorkoutModule(new ViewWorkoutModule())
                .homeScreenModule(new HomeScreenModule())
                .workoutGuideModule(new WorkoutGuideModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
