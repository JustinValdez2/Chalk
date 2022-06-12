package com.swagbear.chalk_mobile.dependency_injection;

import com.swagbear.chalk_mobile.screens.create_workout.CreateWorkoutActivity;
import com.swagbear.chalk_mobile.screens.create_workout.CreateWorkoutModule;
import com.swagbear.chalk_mobile.screens.create_exercise.CreateExerciseActivity;
import com.swagbear.chalk_mobile.screens.create_exercise.di.CreateExerciseModule;

import com.swagbear.chalk_mobile.screens.home_screen.HomeScreenActivity;
import com.swagbear.chalk_mobile.screens.home_screen.di.HomeScreenModule;
import com.swagbear.chalk_mobile.screens.view_exercise.ViewExerciseActivity;
import com.swagbear.chalk_mobile.screens.view_exercise.di.ViewExerciseModule;
import com.swagbear.chalk_mobile.screens.view_exercises.ViewExercisesActivity;
import com.swagbear.chalk_mobile.screens.view_exercises.di.ViewExercisesModule;
import com.swagbear.chalk_mobile.screens.view_workout.ViewWorkoutActivity;
import com.swagbear.chalk_mobile.screens.view_workout.di.ViewWorkoutModule;
import com.swagbear.chalk_mobile.screens.view_workouts.ViewWorkoutsActivity;
import com.swagbear.chalk_mobile.screens.view_workouts.di.ViewWorkoutsModule;
import com.swagbear.chalk_mobile.screens.workout_guide.WorkoutGuideActivity;
import com.swagbear.chalk_mobile.screens.workout_guide.di.WorkoutGuideModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, CreateExerciseModule.class, RoomModule.class
        , ViewExercisesModule.class, ViewExerciseModule.class
        , ViewWorkoutsModule.class, ViewWorkoutModule.class, CreateWorkoutModule.class
        , HomeScreenModule.class, WorkoutGuideModule.class})
public interface ApplicationComponent {

    void inject(CreateExerciseActivity target);

    void inject(ViewExercisesActivity target);

    void inject(ViewExerciseActivity target);

    void inject(ViewWorkoutsActivity target);

    void inject(ViewWorkoutActivity target);

    void inject(CreateWorkoutActivity target);

    void inject(HomeScreenActivity target);

    void inject(WorkoutGuideActivity target);

}
