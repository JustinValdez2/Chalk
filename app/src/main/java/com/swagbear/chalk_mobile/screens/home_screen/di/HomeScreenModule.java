package com.swagbear.chalk_mobile.screens.home_screen.di;

import com.swagbear.chalk_mobile.screens.home_screen.HomeScreenContract;
import com.swagbear.chalk_mobile.screens.home_screen.HomeScreenModel;
import com.swagbear.chalk_mobile.screens.home_screen.HomeScreenPresenter;
import com.swagbear.chalk_mobile.service.ChalkService;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeScreenModule {

    @Provides
    public HomeScreenContract.Presenter provideHomeScreenPresenter(HomeScreenContract.Model model) {
        return new HomeScreenPresenter(model);
    }

    @Provides
    public HomeScreenContract.Model provideHomeScreenModel(ChalkService chalkService) {
        return new HomeScreenModel(chalkService);
    }

}
