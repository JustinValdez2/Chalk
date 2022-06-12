package com.swagbear.chalk_mobile.service;

import com.swagbear.chalk_mobile.entities.ChalkSet;
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.entities.Workout;

import java.util.List;

public interface ChalkService {

    int createExercise(Exercise exercise);

    void updateExercise(Exercise exercise);

    void deleteExercise(Exercise exercise);

    List<Exercise> getExerciseList();

    Exercise getExerciseById(int id);

    int createWorkout(Workout workout);

    void updateWorkout(Workout workout);

    void deleteWorkout(Workout workout);

    List<Workout> getWorkoutList();

    Workout getWorkoutById(int id);

    void addSetToWorkout(ChalkSet setToAdd);

    void deleteSets(List<ChalkSet> set);

}
