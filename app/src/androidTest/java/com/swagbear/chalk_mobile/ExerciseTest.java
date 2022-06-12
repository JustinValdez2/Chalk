package com.swagbear.chalk_mobile;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.swagbear.chalk_mobile.dao.ExerciseDao;
import com.swagbear.chalk_mobile.database.ChalkDatabase;
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.enums.ExerciseType;
import com.swagbear.chalk_mobile.enums.TargetArea;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests the DAO that contains all of the queries for accessing the exercise table
 *
 * Created by Justin on 8/29/2018.
 */
@RunWith(AndroidJUnit4.class)
public class ExerciseTest {

    private ExerciseDao mExerciseDao;
    public ChalkDatabase mDb;

    @Before
    public void createDB() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context,ChalkDatabase.class).build();
        mExerciseDao = mDb.exerciseDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void addExercise() throws Exception {
        Exercise exercise = insertExercise();
        Assert.assertNotNull(exercise.getId());
    }

    @Test
    public void getAllExercises() {
        insertExercise();
        insertExercise();
        List<Exercise> exercises = mExerciseDao.getAll();

        Assert.assertEquals(exercises.size(), 2);
    }

    @Test
    public void getExerciseById() {
        int id = insertExercise().getId();
        int searchId = mExerciseDao.getById(id).getId();

        Assert.assertEquals(id, searchId);
    }

    @Test
    public void updateExercise() {
        Exercise exercise = insertExercise();
        exercise.setInstructions("This was updated");
        mExerciseDao.updateExercise(exercise);

        Exercise searchExercise = mExerciseDao.getById(exercise.getId());
        Assert.assertEquals(searchExercise.getInstructions(), "This was updated");
    }

    @Test
    public void deleteExercise() throws Exception {
        Exercise exercise = insertExercise();
        int id = exercise.getId();
        deleteExercise(exercise);

        Exercise searchExercise = mExerciseDao.getById(id);
        Assert.assertNull(searchExercise);
    }

    private Exercise insertExercise() {
        Exercise exercise = new Exercise();
        exercise.setExerciseType(ExerciseType.WEIGHT);
        exercise.setFavorite(true);
        exercise.setInstructions("Do this");
        exercise.setName("Bench");
        exercise.setUserDefined(false);

        ArrayList<TargetArea> targetAreas = new ArrayList<>();
        targetAreas.add(TargetArea.BACK);
        targetAreas.add(TargetArea.BICEPS);
        exercise.setTargetAreas(targetAreas);


        int id = (int) mExerciseDao.insertAll(exercise)[0];

        exercise.setId(id);

        return exercise;
    }

    private void deleteExercise(Exercise exercise) {

        mExerciseDao.deleteExercise(exercise);
    }
}
