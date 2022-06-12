package com.swagbear.chalk_mobile.screens.home_screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;


import com.swagbear.chalk_mobile.R;
import com.swagbear.chalk_mobile.dependency_injection.App;
import com.swagbear.chalk_mobile.entities.Workout;
import com.swagbear.chalk_mobile.screens.view_exercises.ViewExercisesActivity;
import com.swagbear.chalk_mobile.screens.view_workouts.ViewWorkoutsActivity;
import com.swagbear.chalk_mobile.screens.workout_guide.WorkoutGuideActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeScreenActivity extends AppCompatActivity implements HomeScreenContract.View {


    @Inject
    public HomeScreenContract.Presenter presenter;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigation;

    @BindView(R.id.ddlStartWorkout)
    Spinner workoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);
        setTitle(R.string.workout_guide_title);


        //Set the active Activity to the active menu item on the bottom navigation
        bottomNavigation.setSelectedItemId(R.id.menu_guide);

        //Section will handle the bottom navigation functionality
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_exercise:
                        Intent goToViewExercises = new Intent(HomeScreenActivity.this, ViewExercisesActivity.class);
                        goToViewExercises.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(goToViewExercises);
                        break;

                    case R.id.menu_workout:
                        Intent goToViewWorkouts = new Intent(HomeScreenActivity.this, ViewWorkoutsActivity.class);
                        goToViewWorkouts.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(goToViewWorkouts);
                        break;
                }
                    return false;

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.populateWorkoutList();
    }

    @Override
    public void populateWorkoutDropDown(List<Workout> workouts) {
        ArrayAdapter<Workout> workoutArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, workouts);
        workoutList.setAdapter(workoutArrayAdapter);
    }


    @OnClick(R.id.btnLetsGo)
    public void startWorkoutClicked() {
        Workout selectedWorkout = (Workout)workoutList.getSelectedItem();
        presenter.startWorkoutClicked(selectedWorkout.getId());
    }

    @Override
    public void navigateToWorkoutGuide(int workoutId) {
        try {
            Intent destination = new Intent(this, WorkoutGuideActivity.class);
            destination.putExtra("workoutId", workoutId);
            startActivity(destination);
        } catch(Exception e) {
            // Log.e(TAG, "An error occurred while navigating to the View Workout Screen",e);
        }
    }
}
