package com.swagbear.chalk_mobile.service;

import android.os.AsyncTask;

import com.swagbear.chalk_mobile.database.ChalkDatabase;
import com.swagbear.chalk_mobile.entities.ChalkSet;
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.entities.Workout;

import java.util.List;

public class ChalkServiceImpl implements ChalkService {

    private ChalkDatabase chalkDB;

    /**
     * If you need to keep threads running for long periods of time,
     * it is highly recommended you use the various APIs provided by the java.util.concurrent package such as Executor,
     * ThreadPoolExecutor and FutureTask.
     **/

    public ChalkServiceImpl(ChalkDatabase db) {
        this.chalkDB = db;
    }

    @Override
    public int createExercise(final Exercise exercise) {
        Integer exerciseId = -1;

        try {
            exerciseId = new AsyncTask<Void, Void, Integer>() {
                @Override
                protected Integer doInBackground(Void... voids) {
                    Integer exerciseId;
                    exerciseId = (int)chalkDB.exerciseDao().insertAll(exercise)[0];
                    return exerciseId;
                }
            }.execute().get();
        } catch (Exception e){
            //Todo: Add functionality to notify user in case this exception is hit
            return exerciseId;
        }
        return exerciseId;
    }

    @Override
    public void updateExercise(Exercise exercise) {
        new AsyncTask<Exercise, Void, Void>() {
            @Override
            protected Void doInBackground(Exercise... exercise1) {
                chalkDB.exerciseDao().updateExercise(exercise1[0]);
                return null;
            }
        }.execute(exercise);
    }

    @Override
    public void deleteExercise(final Exercise exercise) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                chalkDB.exerciseDao().deleteExercise(exercise);
                return null;
            }
        }.execute();

    }

    @Override
    public List<Exercise> getExerciseList() {
        try {
            return  new AsyncTask<Void, Void, List<Exercise>>() {
                @Override
                protected List<Exercise> doInBackground(Void... voids) {
                    return chalkDB.exerciseDao().getAll();
                }
            }.execute().get();
        }catch (Exception e){
            //Todo: Add functionality to notify user in case this exception is hit
            return null;
        }
    }

    @Override
    public Exercise getExerciseById(int id) {
        try {
            final int finalId = id;

            return  new AsyncTask<Void, Void, Exercise>() {
                @Override
                protected Exercise doInBackground(Void... voids) {
                    return chalkDB.exerciseDao().getById((long)finalId);
                }
            }.execute().get();
        }catch (Exception e){
            //Todo: Add functionality to notify user in case this exception is hit
            return null;
        }
    }

    @Override
    public int createWorkout(final Workout workout) {
        Integer workoutId = -1;

        try {
            workoutId = new AsyncTask<Void, Void, Integer>() {
                @Override
                protected Integer doInBackground(Void... voids) { Integer workoutId;
                workoutId = chalkDB.workoutDao().insertWorkoutWithSets(workout);
                return workoutId;
            }
        }.execute().get();
            } catch (Exception e) {
            return workoutId;
        }

        return workoutId;
    }

    @Override
    public void updateWorkout(final Workout workout) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                chalkDB.workoutDao().updateWorkoutAndChalkSets(workout);
                return null;
            }
        }.execute();
    }

    @Override
    public void deleteWorkout(final Workout workout) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                chalkDB.workoutDao().deleteWorkout(workout);
                return null;
            }
        }.execute();
    }

    @Override
    public List<Workout> getWorkoutList() {
        try {
            return  new AsyncTask<Void, Void, List<Workout>>() {
                @Override
                protected List<Workout> doInBackground(Void... voids) {
                    return chalkDB.workoutDao().getAllWorkoutsAndSets();
                }
            }.execute().get();
        }catch (Exception e){
            //Todo: Add functionality to notify user in case this exception is hit
            return null;
        }
    }

    @Override
    public Workout getWorkoutById(int id) {
        Integer searchId = id;

        try {
            return new AsyncTask<Integer, Void, Workout>() {
                @Override
                protected Workout doInBackground(Integer... searchId) {
                    return chalkDB.workoutDao().getWorkoutWithChalkSets(searchId[0]);
                }
            }.execute(searchId).get();
        }catch(Exception e){
            //Todo: Add functionality to notify user in case this exception is hit
            return null;
        }
    }

    @Override
    public void addSetToWorkout(final ChalkSet setToAdd) {

        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    chalkDB.workoutDao().insertChalkSet(setToAdd);
                    return null;
                }
            }.execute();
        } catch (Exception e) {
            //TODO: Add error handling
        }
    }

    @Override
    public void deleteSets(final List<ChalkSet> chalkSets) {
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    chalkDB.workoutDao().deleteChalkSets(chalkSets);
                    return null;
                }
            }.execute();
        } catch (Exception e) {
            //TODO: Add error handling
        }

    }
}
