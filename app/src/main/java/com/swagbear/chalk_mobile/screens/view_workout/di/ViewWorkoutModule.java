package com.swagbear.chalk_mobile.screens.view_workout.di;

import com.swagbear.chalk_mobile.screens.view_workout.mvp.ViewWorkoutContract;
import com.swagbear.chalk_mobile.screens.view_workout.mvp.ViewWorkoutModel;
import com.swagbear.chalk_mobile.screens.view_workout.mvp.ViewWorkoutPresenter;
import com.swagbear.chalk_mobile.service.ChalkService;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewWorkoutModule {

    @Provides
    public ViewWorkoutContract.Presenter provideViewWorkoutPresenter(ViewWorkoutContract.Model model) {
        return new ViewWorkoutPresenter(model);
    }

    @Provides
    public ViewWorkoutContract.Model provideViewWorkoutModel(ChalkService chalkService) {
        return new ViewWorkoutModel(chalkService);
    }
}
