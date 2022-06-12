package com.swagbear.chalk_mobile.screens.workout_guide.di;

import com.swagbear.chalk_mobile.screens.workout_guide.WorkoutGuideContract;
import com.swagbear.chalk_mobile.screens.workout_guide.WorkoutGuideModel;
import com.swagbear.chalk_mobile.screens.workout_guide.WorkoutGuidePresenter;
import com.swagbear.chalk_mobile.service.ChalkService;

import dagger.Module;
import dagger.Provides;

@Module
public class WorkoutGuideModule {

    @Provides
    public WorkoutGuideContract.Presenter provideCreateExercisePresenter(WorkoutGuideContract.Model model){
        return new WorkoutGuidePresenter(model);
    }

    @Provides
    public WorkoutGuideContract.Model provideCreateExerciseModel(ChalkService chalkService) {
        return new WorkoutGuideModel(chalkService);
    }
}
