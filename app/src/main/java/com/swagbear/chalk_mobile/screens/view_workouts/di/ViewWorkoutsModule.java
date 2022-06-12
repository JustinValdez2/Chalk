package com.swagbear.chalk_mobile.screens.view_workouts.di;

import com.swagbear.chalk_mobile.screens.view_workouts.mvp.ViewWorkoutsContract;
import com.swagbear.chalk_mobile.screens.view_workouts.mvp.ViewWorkoutsModel;
import com.swagbear.chalk_mobile.screens.view_workouts.mvp.ViewWorkoutsPresenter;
import com.swagbear.chalk_mobile.service.ChalkService;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewWorkoutsModule {

    @Provides
    public ViewWorkoutsContract.Presenter provideViewWorkoutsPresenter(ViewWorkoutsContract.Model model) {
        return new ViewWorkoutsPresenter(model);
    }

    @Provides
    public ViewWorkoutsContract.Model provideViewWorkoutsModel(ChalkService chalkService) {
        return new ViewWorkoutsModel(chalkService);
    }
}
