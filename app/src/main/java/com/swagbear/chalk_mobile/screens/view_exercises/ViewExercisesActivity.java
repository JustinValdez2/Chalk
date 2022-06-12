package com.swagbear.chalk_mobile.screens.view_exercises;

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
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.screens.create_exercise.CreateExerciseActivity;
import com.swagbear.chalk_mobile.screens.view_exercise.ViewExerciseActivity;
import com.swagbear.chalk_mobile.screens.view_exercises.mvp.ViewExercisesContract;
import com.swagbear.chalk_mobile.screens.view_workouts.ViewWorkoutsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewExercisesActivity extends AppCompatActivity implements ViewExercisesContract.View{

    @Inject
    public ViewExercisesContract.Presenter presenter;

    @BindView(R.id.btnAddExercise)
    FloatingActionButton addExerciseButton;

    @BindView(R.id.lstExerciseList)
    ListView exerciseList;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercises);
        setTitle(R.string.view_exercises_title);

        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);


        //Set the active Activity to the active menu item on the bottom navigation
        bottomNavigation.setSelectedItemId(R.id.menu_exercise);

        //Section will handle the bottom navigation functionality
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_guide:
                        Intent goToWorkoutGuide = new Intent(ViewExercisesActivity.this, HomeScreenActivity.class);
                        goToWorkoutGuide.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(goToWorkoutGuide);
                        break;

                    case R.id.menu_workout:
                        Intent goToViewWorkouts = new Intent(ViewExercisesActivity.this, ViewWorkoutsActivity.class);
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
        presenter.fillExerciseList();
    }

    @OnClick(R.id.btnAddExercise)
    public void setAddExerciseButtonClick() {
        presenter.addExerciseClicked();
    }

    @Override
    public void populateExerciseList(List<Exercise> exercises) {
        ArrayAdapter<Exercise> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,exercises);
        exerciseList.setAdapter(adapter);

        exerciseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.exerciseListClicked((Exercise)exerciseList.getItemAtPosition(i));
            }
        });

    }

    @Override
    public void navigateToCreateExerciseScreen() {
        try {
            Intent destination = new Intent(this, CreateExerciseActivity.class);
            startActivity(destination);
        } catch(Exception e) {
//            Log.e(TAG, "An error occurred while navigating to the View Exercise Screen",e);
        }
    }

    @Override
    public void navigateToViewExerciseScreen(int exerciseId) {
        try {
            Intent destination = new Intent(this, ViewExerciseActivity.class);
            destination.putExtra("exerciseId",exerciseId);


            startActivity(destination);
        } catch(Exception e) {
//            Log.e(TAG, "An error occurred while navigating to the View Exercise Screen",e);
        }
    }
}
