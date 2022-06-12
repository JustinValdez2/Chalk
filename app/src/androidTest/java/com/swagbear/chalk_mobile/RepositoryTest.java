package com.swagbear.chalk_mobile;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.swagbear.chalk_mobile.database.ChalkDatabase;
import com.swagbear.chalk_mobile.entities.ChalkSet;
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.entities.Workout;
import com.swagbear.chalk_mobile.enums.ExerciseType;
import com.swagbear.chalk_mobile.enums.TargetArea;
import com.swagbear.chalk_mobile.service.ChalkService;
import com.swagbear.chalk_mobile.service.ChalkServiceImpl;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class RepositoryTest {

    public ChalkDatabase mDb;
    ChalkService mChalkService;

    @Before
    public void createDB() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context,ChalkDatabase.class).build();
        mChalkService =  new ChalkServiceImpl(mDb);
    }

    @Test
    public void testChalkServiceImplCreateAnExercise() {
        Exercise exercise = createExercise();
        int exerciseId = mChalkService.createExercise(exercise);

        assertTrue(exerciseId > -1);
    }

    @Test
    public void testChalkServiceImplGetExerciseById() {
        Exercise exercise = createExercise();
        int exerciseId = mChalkService.createExercise(exercise);
        Exercise searchExercise = mChalkService.getExerciseById(exerciseId);

        assertNotNull(searchExercise);
    }

    @Test
    public void testChalkServiceImplUpdateExercise() {
        Exercise exercise = createExercise();
        exercise.setId(mChalkService.createExercise(exercise));
        exercise.setName("TEST");
        mChalkService.updateExercise(exercise);
        Exercise searchExercise = mChalkService.getExerciseById(exercise.getId());

        assertTrue(searchExercise.getName().equals("TEST"));
    }

    @Test
    public void testChalkServiceImplDeleteExercise() {
        Exercise exercise = createExercise();
        exercise.setId(mChalkService.createExercise(exercise));
        mChalkService.deleteExercise(exercise);
        Exercise searchExercise = mChalkService.getExerciseById(exercise.getId());

        assertNull(searchExercise);
    }

    @Test
    public void testChalkServiceImplCreateWorkout() {
        Exercise exercise = createExercise();
        exercise.setId(mChalkService.createExercise(exercise));
        List<ChalkSet> chalkSets = createChalkSets(exercise,3);
        Map<Exercise, List<ChalkSet>> workoutExercises = new HashMap<>();
        workoutExercises.put(exercise,chalkSets);

        Workout workout = createWorkout(workoutExercises);

        workout.setId(mChalkService.createWorkout(workout));
        assertTrue(workout.getId() > -1);
    }

    @Test
    public void testChalkServiceImplUpdateWorkout() {
        Exercise exercise = createExercise();
        exercise.setId(mChalkService.createExercise(exercise));
        List<ChalkSet> chalkSets = createChalkSets(exercise,3);
        Map<Exercise, List<ChalkSet>> workoutExercises = new HashMap<>();
        workoutExercises.put(exercise,chalkSets);

        Workout workout = createWorkout(workoutExercises);

        workout.setId(mChalkService.createWorkout(workout));
        workout.setName("TEST NAME");
        mChalkService.updateWorkout(workout);

        Workout searchWorkout = mChalkService.getWorkoutById(workout.getId());
        assertTrue(searchWorkout.getName().equals("TEST NAME"));
    }

    @Test
    public void testChalkServiceImplDeleteWorkout() {
        Exercise exercise = createExercise();
        exercise.setId(mChalkService.createExercise(exercise));
        List<ChalkSet> chalkSets = createChalkSets(exercise,3);
        Map<Exercise, List<ChalkSet>> workoutExercises = new HashMap<>();
        workoutExercises.put(exercise,chalkSets);

        Workout workout = createWorkout(workoutExercises);

        workout.setId(mChalkService.createWorkout(workout));

        mChalkService.deleteWorkout(workout);

        Workout searchWorkout = mChalkService.getWorkoutById(workout.getId());
        assertNull(searchWorkout);
    }

    @Test
    public void testChalkServiceImplGetWorkoutList() {
        Exercise exercise = createExercise();
        exercise.setId(mChalkService.createExercise(exercise));
        List<ChalkSet> chalkSets = createChalkSets(exercise,3);
        List<ChalkSet> chalkSets2 = createChalkSets(exercise,3);
        Map<Exercise, List<ChalkSet>> workoutExercises = new HashMap<>();
        Map<Exercise, List<ChalkSet>> workoutExercises2 = new HashMap<>();
        workoutExercises.put(exercise,chalkSets);
        workoutExercises2.put(exercise,chalkSets2);

        Workout workout = createWorkout(workoutExercises);

        Workout workout2 = createWorkout(workoutExercises2);
        workout.setId(mChalkService.createWorkout(workout));
        workout2.setId(mChalkService.createWorkout(workout2));

        List<Workout> workouts = mChalkService.getWorkoutList();

        assertTrue(workouts.size() == 2);
    }

    @Test
    public void testAddSetToWorkout() {
        Exercise exercise = insertExercise(ExerciseType.WEIGHT);
        ChalkSet chalkSet = createChalkSet(exercise.getId(), 1);

        Workout workout = createWorkout(null);
        int workoutId = mChalkService.createWorkout(workout);
        chalkSet.setWorkoutId(workoutId);

        mChalkService.addSetToWorkout(chalkSet);
        workout = mChalkService.getWorkoutById(workoutId);

        assertTrue(workout.getExerciseSetList().size() == 1);
    }

    @Test
    public void testRemoveSetFromWorkout() {
        Exercise exercise = insertExercise(ExerciseType.WEIGHT);
        List<ChalkSet> chalkSets = createChalkSets(exercise,1);
        Map<Exercise, List<ChalkSet>> workoutExercises = new HashMap<>();
        workoutExercises.put(exercise,chalkSets);

        Workout workout = createWorkout(workoutExercises);
        workout.setId(mChalkService.createWorkout(workout));

        List<ChalkSet> deleteSets = workout.getExerciseSetList().get(exercise);

        mChalkService.deleteSets(deleteSets);

        workout = mChalkService.getWorkoutById(workout.getId());

        assertTrue(workout.getExerciseSetList().isEmpty());
    }

    private Exercise createExercise() {
        Exercise exercise = new Exercise();
        exercise.setExerciseType(ExerciseType.WEIGHT);
        exercise.setFavorite(true);
        exercise.setInstructions("Do this");
        exercise.setName("Bench");
        exercise.setUserDefined(false);

        List<TargetArea> targetAreas = new ArrayList<>();
        targetAreas.add(TargetArea.CHEST);
        targetAreas.add(TargetArea.TRICEPS);
        exercise.setTargetAreas((ArrayList<TargetArea>) targetAreas);

        return exercise;
    }

    private Workout createWorkout(Map<Exercise,List<ChalkSet>> chalkSets) {
        Workout workout = new Workout();
        workout.setName("Back and Bicep");
        workout.setExerciseSetList(chalkSets);

        return workout;
    }

    private List<ChalkSet> createChalkSets(int numberOfSets) {
        int exerciseId = insertExercise(ExerciseType.WEIGHT).getId();

        List<ChalkSet> chalkSets = new ArrayList<>();
        for(int i = 1; i <= numberOfSets; i++) {
            chalkSets.add(createChalkSet(exerciseId, i));
        }
        return  chalkSets;
    }

    private List<ChalkSet> createChalkSets(Exercise exercise ,int numberOfSets) {
        List<ChalkSet> chalkSets = new ArrayList<>();
        for(int i = 1; i <= numberOfSets; i++) {
            chalkSets.add(createChalkSet(exercise.getId(), i));
        }
        return  chalkSets;
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

        int id = (int) mDb.exerciseDao().insertAll(exercise)[0];

        exercise.setId(id);

        return exercise;
    }
}
