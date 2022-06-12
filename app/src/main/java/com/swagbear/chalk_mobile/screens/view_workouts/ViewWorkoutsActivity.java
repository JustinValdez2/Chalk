package com.swagbear.chalk_mobile.screens.view_workouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.swagbear.chalk_mobile.R;
import com.swagbear.chalk_mobile.screens.home_screen.HomeScreenActivity;
import com.swagbear.chalk_mobile.dependency_injection.App;
import com.swagbear.chalk_mobile.entities.Workout;
import com.swagbear.chalk_mobile.screens.create_workout.CreateWorkoutActivity;
import com.swagbear.chalk_mobile.screens.view_exercises.ViewExercisesActivity;
import com.swagbear.chalk_mobile.screens.view_workout.ViewWorkoutActivity;
import com.swagbear.chalk_mobile.screens.view_workouts.mvp.ViewWorkoutsContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewWorkoutsActivity extends AppCompatActivity  implements ViewWorkoutsContract.View {

    @Inject
    public ViewWorkoutsContract.Presenter presenter;

    @BindView(R.id.btnAddWorkout)
    FloatingActionButton addWorkoutButton;

    @BindView(R.id.lstWorkoutList)
    ListView workoutList;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workouts);
        setTitle(R.string.view_workouts_title);
        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);


        //Set the active Activity to the active menu item on the bottom navigation
        bottomNavigation.setSelectedItemId(R.id.menu_workout);

        //Section will handle the bottom navigation functionality
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_exercise:
                        Intent goToViewWorkouts = new Intent(ViewWorkoutsActivity.this, ViewExercisesActivity.class);
                        goToViewWorkouts.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(goToViewWorkouts);
                        break;
                    case R.id.menu_guide:
                        Intent goToWorkoutGuide = new Intent(ViewWorkoutsActivity.this, HomeScreenActivity.class);
                        goToWorkoutGuide.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(goToWorkoutGuide);
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
        presenter.populateWorkoutsList();
    }

    @Override
    public void displayWorkouts(List<Workout> workouts) {
        ArrayAdapter<Workout> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workouts);
        workoutList.setAdapter(adapter);

        workoutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Workout selectedWorkout = (Workout) adapterView.getItemAtPosition(i);
                presenter.workoutItemClicked(selectedWorkout);
            }
        });

    }

    @OnClick(R.id.btnAddWorkout)
    public void setAddWorkoutButtonClick() {
        presenter.addWorkoutClicked();
    }


    @Override
    public void navigateToViewWorkout(int workoutId) {
        try {
            Intent destination = new Intent(this, ViewWorkoutActivity.class);
            destination.putExtra("workoutId", workoutId);
            startActivity(destination);
        } catch(Exception e) {
            // Log.e(TAG, "An error occurred while navigating to the View Workout Screen",e);
        }
    }

    @Override
    public void navigateToCreateWorkout() {
        try {
            Intent destination = new Intent(this, CreateWorkoutActivity.class);
            startActivity(destination);
        } catch(Exception e) {

        }
    }

    @Override
    public void createWorkoutButtonClick() {

    }

    @Override
    public void workoutListItemClick() {

    }
}
