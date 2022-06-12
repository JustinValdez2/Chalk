package com.swagbear.chalk_mobile.dependency_injection;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.swagbear.chalk_mobile.database.ChalkDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private ChalkDatabase chalkDatabase;

    public RoomModule(Application application) {
        chalkDatabase = Room.databaseBuilder(application, ChalkDatabase.class, "ChalkDB").build();
    }

    @Singleton
    @Provides
    ChalkDatabase providesChalkDatabase() {
        return chalkDatabase;
    }

}
