package com.swagbear.chalk_mobile.dependency_injection;

import android.app.Application;
import android.content.Context;

import com.swagbear.chalk_mobile.database.ChalkDatabase;
import com.swagbear.chalk_mobile.service.ChalkService;
import com.swagbear.chalk_mobile.service.ChalkServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    public ChalkService provideChalkService(ChalkDatabase db) {
        return new ChalkServiceImpl(db);
    }
}
