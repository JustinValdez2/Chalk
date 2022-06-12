package com.swagbear.chalk_mobile;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.swagbear.chalk_mobile.dao.ExerciseDao;
import com.swagbear.chalk_mobile.dao.WorkoutDao;
import com.swagbear.chalk_mobile.database.ChalkDatabase;
import com.swagbear.chalk_mobile.entities.ChalkSet;
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.entities.Workout;
import com.swagbear.chalk_mobile.enums.ExerciseType;
import com.swagbear.chalk_mobile.enums.TargetArea;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class WorkoutChalkSetTest {

    public ChalkDatabase mDb;

    private ExerciseDao mExerciseDao;
    private WorkoutDao mWorkoutDao;

    @Before
    public void createDB() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context,ChalkDatabase.class).build();
        mExerciseDao = mDb.exerciseDao();
        mWorkoutDao = mDb.workoutDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void insertWorkout() {
        Workout workout = createWorkout(3);
        Assert.assertNotNull(workout.getId());
    }

    @Test
    public void getAllWorkouts() {
        createWorkout(3);
        createWorkout(3);

        ArrayList<Workout> workouts = new ArrayList<>(mWorkoutDao.getAllWorkoutsAndSets());

        Assert.assertEquals(workouts.size(),2);
    }


    @Test
    public void getWorkoutById() {
        Workout workout = createWorkout(3);
        Workout searchWorkout = mWorkoutDao.getWorkoutWithChalkSets(workout.getId());

        Assert.assertEquals(workout.getId(), searchWorkout.getId());
    }


    @Test
    public void updateWorkout() {
        Workout workout = createWorkout(3);
        workout.setName("Updated Workout");
        mWorkoutDao.updateWorkout(workout);

        Workout searchWorkout = mWorkoutDao.getWorkoutWithChalkSets(workout.getId());
        Assert.assertEquals(searchWorkout.getName(),"Updated Workout");
    }

    @Test
    public void deleteWorkout() {
        Workout workout = createWorkout(3);
        int id = workout.getId();
        mWorkoutDao.deleteWorkout(workout);

        Workout searchWorkout = mWorkoutDao.getWorkoutWithChalkSets(id);

        Assert.assertNull(searchWorkout);
    }


    private Workout createWorkout(int setNumber) {
        Workout workout = new Workout();
        Exercise exercise = insertExercise(ExerciseType.WEIGHT);
        Map<Exercise,List<ChalkSet>> exercises = new HashMap<>();

        ArrayList<ChalkSet> chalkSets = new ArrayList<>();

        for(int i = 1; i <= setNumber; i++) {
            chalkSets.add(createChalkSet(exercise.getId(), i));
        }
        exercises.put(exercise, chalkSets);
        workout.setExerciseSetList(exercises);

        mWorkoutDao.insertWorkoutWithSets(workout);

        return workout;
    }

    private ChalkSet createChalkSet(int exerciseId, int order) {
        ChalkSet chalkSet = new ChalkSet();
        chalkSet.setReps(10);
        chalkSet.setWeight(100);
        chalkSet.setOrder(order);
        chalkSet.setExerciseId(exerciseId);

        return chalkSet;
    }

    private Exercise insertExercise(ExerciseType type) {
        Exercise exercise = new Exercise();
        exercise.setExerciseType(type);
        exercise.setFavorite(true);
        exercise.setInstructions("Do this");
        exercise.setName(type.getName() + "_" + "Exercise");
        exercise.setUserDefined(false);

        ArrayList<TargetArea> targetAreas = new ArrayList<>();
        targetAreas.add(TargetArea.BACK);
        targetAreas.add(TargetArea.BICEPS);
        exercise.setTargetAreas(targetAreas);

        int id = (int) mExerciseDao.insertAll(exercise)[0];

        exercise.setId(id);

        return exercise;
    }
}
