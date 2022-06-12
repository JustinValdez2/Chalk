package com.swagbear.chalk_mobile.screens.create_workout;

import com.swagbear.chalk_mobile.service.ChalkService;

import dagger.Module;
import dagger.Provides;

@Module
public class CreateWorkoutModule {

    @Provides
    public CreateWorkoutContract.Presenter provideCreateWorkoutPresenter(CreateWorkoutContract.Model model) {
        return  new CreateWorkoutPresenter(model);
    }

    @Provides
    public CreateWorkoutContract.Model provideCreateWorkoutModel(ChalkService chalkService) {
        return  new CreateWorkoutModel(chalkService);
    }
}
