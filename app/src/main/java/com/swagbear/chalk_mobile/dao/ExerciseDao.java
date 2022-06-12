package com.swagbear.chalk_mobile.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.swagbear.chalk_mobile.entities.Exercise;

import java.util.List;

/**
 * Used to manage data from the Exercise table
 */
@Dao
public interface ExerciseDao {

    @Query("select * from exercise")
    List<Exercise> getAll();

    @Query("select * from exercise where id = :id")
    Exercise getById(long id);

    @Insert
    long[] insertAll(Exercise... exercises);

    @Update
    void updateExercise(Exercise exercise);

    @Delete
    void deleteExercise(Exercise exercise);
}
