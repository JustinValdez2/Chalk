package com.swagbear.chalk_mobile.screens.view_exercises.di;

import com.swagbear.chalk_mobile.screens.view_exercises.mvp.ViewExercisesContract;
import com.swagbear.chalk_mobile.screens.view_exercises.mvp.ViewExercisesPresenter;
import com.swagbear.chalk_mobile.screens.view_exercises.mvp.ViewExercisesModel;
import com.swagbear.chalk_mobile.service.ChalkService;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewExercisesModule {

    @Provides
    public ViewExercisesContract.Presenter provideViewExercisePresenter(ViewExercisesContract.Model model) {
        return new ViewExercisesPresenter(model);
    }

    @Provides
    public ViewExercisesContract.Model provideViewExerciseModel(ChalkService chalkService) {
        return new ViewExercisesModel(chalkService);
    }
}
