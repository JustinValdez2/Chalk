package com.swagbear.chalk_mobile.screens.create_workout;

import android.support.annotation.Nullable;

import com.swagbear.chalk_mobile.entities.ChalkSet;
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.entities.Workout;
import com.swagbear.chalk_mobile.utilities.Validators;

import java.util.ArrayList;
import java.util.List;

public class CreateWorkoutPresenter implements CreateWorkoutContract.Presenter {

    @Nullable
    private CreateWorkoutContract.View view;
    private CreateWorkoutContract.Model model;

    public CreateWorkoutPresenter(CreateWorkoutContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(CreateWorkoutContract.View view) {
        this.view = view;
    }

    @Override
    public List<Exercise> getSelectExerciseList() {
        return model.getAllExercises();
    }

    @Override
    public void saveWorkout() {

        createWorkoutFromView();


        if(Validators.isValidWorkout(model.getWorkout())){
            if(model.isEdit()) {
                model.updateWorkout();
            } else {
                model.saveWorkOut(model.getWorkout());
            }
            view.notifyUser("Workout Saved");
            view.navigateToViewWorkoutsScreen();

    }else {
        view.notifyUser(Validators.errorList.toString());
        Validators.resetErrorList();
    }
}

    @Override
    public void updateAddExerciseList() {

        List<Exercise> exercises = new ArrayList<>();
        exercises.addAll(model.getWorkoutExercises().keySet());

        view.updateWorkoutExerciseList(exercises);
    }

    @Override
    public void cancelSaveWorkout() {
        view.navigateToViewWorkoutsScreen();
    }

    @Override
    public void addExercise(Exercise exercise) {

        ChalkSet chalkSet = new ChalkSet();
        chalkSet.setExerciseId(exercise.getId());

        if(model.getWorkoutExercises().containsKey(exercise)) {
            model.getWorkoutExercises().get(exercise).add(chalkSet);
        } else {
            List<ChalkSet> chalkSets = new ArrayList<>();
            chalkSets.add(chalkSet);
            model.getWorkoutExercises().put(exercise, chalkSets);
        }
    }

    @Override
    public void populateWorkoutInfoForEdit(int workoutID) {
        model.setIsEdit(true);
        model.pullWorkout(workoutID);
        view.setIntensity(String.valueOf(model.getWorkout().getIntensity()));
        view.setWorkoutDescription(model.getWorkout().getDescription());
        view.setIsFavorite(model.getWorkout().isFavorite());
        view.setWorkoutName(model.getWorkout().getName());
        model.setWorkoutExercises(model.getWorkout().getExerciseSetList());

        updateAddExerciseList();
    }

    @Override
    public void deleteExercise(Exercise exercise) {

        if(model.isEdit()) {
            List<ChalkSet> chalkSetsToDelete = model.getWorkoutExercises().get(exercise);
            model.getChalkSetsToDelete().addAll(chalkSetsToDelete);
        }

        model.getWorkoutExercises().remove(exercise);
    }

    private void createWorkoutFromView() {
        if (view != null) {
            model.getWorkout().setName(view.getWorkoutName());
            model.getWorkout().setFavorite(view.isFavorite());
            model.getWorkout().setDescription(view.getWorkoutDescription());
            model.getWorkout().setExerciseSetList(model.getWorkoutExercises());

            if(Validators.isValidInteger(view.getIntensity())) {
                model.getWorkout().setIntensity(Integer.parseInt(view.getIntensity()));
            }
        }
    }

    @Override
    public void deleteWorkoutClicked() {
        String workoutName = model.getWorkout().getName();
        try {
            model.deleteWorkout( model.getWorkout());
            view.notifyUser(workoutName + " deleted");
            view.navigateToViewWorkoutsScreen();
        } catch (Exception e) {
            view.notifyUser("Error deleting " + workoutName);
        }

    }
}