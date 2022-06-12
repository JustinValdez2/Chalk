package com.swagbear.chalk_mobile.screens.home_screen;

import android.support.annotation.Nullable;

public class HomeScreenPresenter implements HomeScreenContract.Presenter {

    @Nullable
    private HomeScreenContract.View view;
    private HomeScreenContract.Model model;

    public HomeScreenPresenter(HomeScreenContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(HomeScreenContract.View view) {
        this.view = view;
    }

    @Override
    public void populateWorkoutList() {
        view.populateWorkoutDropDown(model.getWorkouts());
    }

    @Override
    public void startWorkoutClicked(int selectedWorkoutId) {
        view.navigateToWorkoutGuide(selectedWorkoutId);
    }
}
