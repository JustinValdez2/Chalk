package com.swagbear.chalk_mobile.screens.view_workout;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.swagbear.chalk_mobile.R;
import com.swagbear.chalk_mobile.dependency_injection.App;
import com.swagbear.chalk_mobile.entities.ChalkSet;
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.screens.create_workout.CreateWorkoutActivity;
import com.swagbear.chalk_mobile.screens.view_workout.mvp.ViewWorkoutContract;
import com.swagbear.chalk_mobile.screens.view_workouts.ViewWorkoutsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewWorkoutActivity extends AppCompatActivity implements ViewWorkoutContract.View {

    @BindView(R.id.txtViewWorkoutName)
    TextView txtViewWorkoutName;

    @BindView(R.id.txtViewWorkoutIntensity)
    TextView txtViewWorkoutIntensity;

    @BindView(R.id.txtViewWorkoutDesc)
    TextView txtViewWorkoutDesc;

    @BindView(R.id.listViewWorkoutSets)
    ListView listViewWorkoutSets;

    @Inject
    public ViewWorkoutContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);
        setTitle(R.string.app_name);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);

        Intent intent = getIntent();
        int workoutId = intent.getIntExtra("workoutId", -1);
        presenter.displayWorkoutDetails(workoutId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.navigateToViewWorkouts();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void navigateToViewWorkouts() {
        try {
            Intent destination = new Intent(this, ViewWorkoutsActivity.class);
            destination.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(destination);
        } catch(Exception e) {
        }
    }

    @Override
    public void navigateToEditWorkout(int workoutId) {
        try {
            Intent destination = new Intent(this, CreateWorkoutActivity.class);
            destination.putExtra("workoutId", workoutId);
            startActivity(destination);
        } catch(Exception e) {
        }
    }

    @Override
    public void setViewWorkoutName(String workoutName) {
        txtViewWorkoutName.setText(workoutName);
    }

    @Override
    public void setViewWorkoutIntensity(int intensity) {
        txtViewWorkoutIntensity.setText(String.format("%d", intensity));
    }

    @Override
    public void setViewWorkoutDescription(String workoutDesc) {
        txtViewWorkoutDesc.setText(workoutDesc);
    }

    @Override
    public void populateChalkSetList(List<Exercise> exercises) {
        ArrayAdapter<Exercise> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exercises);
        listViewWorkoutSets.setAdapter(adapter);

        listViewWorkoutSets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.chalkSetListClicked((ChalkSet) listViewWorkoutSets.getItemAtPosition(i));
            }
        });
    }

    @OnClick(R.id.btnEditWorkout)
    public void editWorkoutOnClick(){
        presenter.editWorkoutClicked();
    }

    @Override
    public void btnDeleteWorkoutClicked() {
        //TODO add delete button?  Or should this be in CreateWorkout when editing?
    }

    @Override
    public void displayClickedChalkSet(ChalkSet clickedSet) {
        //TODO pop-up or fragment with chalkSet details
    }
}
