package com.swagbear.chalk_mobile.screens.home_screen;

import com.swagbear.chalk_mobile.entities.Workout;
import com.swagbear.chalk_mobile.service.ChalkService;

import java.util.List;

public class HomeScreenModel implements HomeScreenContract.Model {

    private ChalkService chalkService;

    private List<Workout> workouts;

    public HomeScreenModel(ChalkService chalkService){
        this.chalkService = chalkService;
        workouts = chalkService.getWorkoutList();
    }

    @Override
    public List<Workout> getWorkouts() {
        return workouts;
    }


}
