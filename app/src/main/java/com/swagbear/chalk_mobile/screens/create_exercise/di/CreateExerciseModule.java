package com.swagbear.chalk_mobile.screens.create_exercise.di;

import com.swagbear.chalk_mobile.screens.create_exercise.mvp.CreateExerciseContract;
import com.swagbear.chalk_mobile.screens.create_exercise.mvp.CreateExerciseModel;
import com.swagbear.chalk_mobile.screens.create_exercise.mvp.CreateExercisePresenter;
import com.swagbear.chalk_mobile.service.ChalkService;

import dagger.Module;
import dagger.Provides;

@Module
public class CreateExerciseModule {

    @Provides
    public CreateExerciseContract.Presenter provideCreateExercisePresenter(CreateExerciseContract.Model model){
        return new CreateExercisePresenter(model);
    }

    @Provides
    public CreateExerciseContract.Model provideCreateExerciseModel(ChalkService chalkService) {
        return new CreateExerciseModel(chalkService);
    }
}
