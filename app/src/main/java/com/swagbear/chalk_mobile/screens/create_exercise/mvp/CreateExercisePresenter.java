package com.swagbear.chalk_mobile.screens.create_exercise.mvp;

import android.support.annotation.Nullable;

import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.utilities.Validators;

public class CreateExercisePresenter implements CreateExerciseContract.Presenter {

    @Nullable
    private CreateExerciseContract.View view;
    private CreateExerciseContract.Model model;

    public CreateExercisePresenter(CreateExerciseContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(CreateExerciseContract.View view) {
        this.view = view;
    }


    @Override
    public void populateExerciseInfoForEdit(int exerciseId) {
        model.setIsEdit(true);
        model.pullExercise(exerciseId);

        view.setExerciseName(model.getExercise().getName());
        view.setExerciseDescription(model.getExercise().getInstructions());
        view.setExerciseType(model.getExercise().getExerciseType());
        view.setTargetAreas(model.getExercise().getTargetAreas());
    }

    @Override
    public void saveExerciseClicked() {

        createExerciseFromView();
        if (Validators.isValidExercise(model.getExercise())) {
            if (model.isEdit()) {
                model.updateExercise();
                view.notifyUser("Exercise was updated");
                view.navigateToViewExerciseScreen();

            } else {
                model.saveExercise();
                view.notifyUser("Exercise was saved");
                view.navigateToViewExerciseScreen();
            }
        } else {
            view.displayValidationErrors(Validators.errorList.toString());
            Validators.resetErrorList();
        }

    }

    @Override
    public void cancelClicked() {
        view.navigateToViewExerciseScreen();
    }

    private void createExerciseFromView() {
        if (model.getExercise() == null) {
            model.setExercise(new Exercise());
        }

        if (view != null) {
            model.getExercise().setExerciseType(view.getExerciseType());
            model.getExercise().setInstructions(view.getExerciseDescription());
            model.getExercise().setFavorite(view.getIsFavorite());
            model.getExercise().setName(view.getExerciseName());
            model.getExercise().setTargetAreas(view.getTargetAreas());

        }
    }

    @Override
    public void deleteExerciseButtonClicked() {
        String exerciseName = model.getExercise().getName();

        model.deleteExercise(model.getExercise());
        view.notifyUser(exerciseName + " was deleted");
        view.navigateToViewExercisesScreen();
    }
}
