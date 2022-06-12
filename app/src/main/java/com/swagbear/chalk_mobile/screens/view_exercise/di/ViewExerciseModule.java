package com.swagbear.chalk_mobile.screens.view_exercise.di;

import com.swagbear.chalk_mobile.screens.view_exercise.ViewExerciseContract;
import com.swagbear.chalk_mobile.screens.view_exercise.ViewExerciseModel;
import com.swagbear.chalk_mobile.screens.view_exercise.ViewExercisePresenter;
import com.swagbear.chalk_mobile.service.ChalkService;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewExerciseModule {

    @Provides
    public ViewExerciseContract.Presenter provideViewExercisePresenter(ViewExerciseContract.Model model) {
        return new ViewExercisePresenter(model);
    }

    @Provides
    public ViewExerciseContract.Model provideViewExerciseModel(ChalkService chalkService) {
        return new ViewExerciseModel(chalkService);
    }
}
