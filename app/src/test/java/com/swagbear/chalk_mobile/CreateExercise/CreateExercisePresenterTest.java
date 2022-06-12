package com.swagbear.chalk_mobile.CreateExercise;

import com.swagbear.chalk_mobile.screens.create_exercise.mvp.CreateExerciseContract;
import com.swagbear.chalk_mobile.screens.create_exercise.mvp.CreateExerciseModel;
import com.swagbear.chalk_mobile.screens.create_exercise.mvp.CreateExercisePresenter;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CreateExercisePresenterTest {

    private CreateExerciseContract.View mockView;
    private CreateExercisePresenter presenter;

    @Before
    public void setup() {
        CreateExerciseContract.Model mockModel = mock(CreateExerciseModel.class);
        mockView = mock(CreateExerciseContract.View.class);
        presenter = new CreateExercisePresenter(mockModel);
        presenter.setView(mockView);
    }


    @Test
    public void saveExerciseCreatedByUser() {
        presenter.saveExerciseClicked();

        verify(mockView, times(1)).getExerciseDescription();
        verify(mockView, times(1)).getExerciseName();
        verify(mockView, times(1)).getExerciseType();
        verify(mockView, times(1)).getIsFavorite();
        verify(mockView, times(1)).getTargetAreas();
    }


    @Test
    public void cancelIsClickedAndNavigateToViewExercisesScreen() {
        presenter.cancelClicked();

        verify(mockView, times(1)).navigateToViewExerciseScreen();
    }

}
