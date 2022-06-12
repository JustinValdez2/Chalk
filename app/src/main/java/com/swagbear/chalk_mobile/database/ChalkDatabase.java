package com.swagbear.chalk_mobile.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.swagbear.chalk_mobile.dao.ExerciseDao;
import com.swagbear.chalk_mobile.dao.WorkoutDao;
import com.swagbear.chalk_mobile.entities.ChalkSet;
import com.swagbear.chalk_mobile.utilities.Converters;
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.entities.Workout;

/**
 * Created by Justin on 8/29/2018.
 */

@Database(version = 1, entities = {Exercise.class, Workout.class, ChalkSet.class}
    ,exportSchema = true)
@TypeConverters({Converters.class})
public abstract class ChalkDatabase extends RoomDatabase {

    private static ChalkDatabase sInstance;
    public abstract ExerciseDao exerciseDao();
    public abstract WorkoutDao workoutDao();
    public static final String DATABASE_NAME = "chalk-db";

    public static ChalkDatabase getChalkDatabase(Context context) {
        if (sInstance == null) {
            sInstance =
                    Room.databaseBuilder(context.getApplicationContext(), ChalkDatabase.class, DATABASE_NAME)
                            .build();
        }
        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

}
