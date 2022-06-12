package com.swagbear.chalk_mobile.screens.view_exercise;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.swagbear.chalk_mobile.R;
import com.swagbear.chalk_mobile.dependency_injection.App;
import com.swagbear.chalk_mobile.screens.create_exercise.CreateExerciseActivity;
import com.swagbear.chalk_mobile.screens.view_exercises.ViewExercisesActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewExerciseActivity extends AppCompatActivity implements ViewExerciseContract.View {

    @BindView(R.id.txtViewExerciseName)
    TextView txtViewExerciseName;

    @BindView(R.id.txtViewExerciseDescription)
    TextView txtViewExerciseDescription;

    @BindView(R.id.txtViewExerciseType)
    TextView txtViewExerciseType;

    @BindView(R.id.txtViewExerciseTargetAreas)
    TextView txtViewExerciseTargetAreas;

    @Inject
    public ViewExerciseContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);
        setTitle(R.string.app_name);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.navigateToViewExercises();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        Intent intent = getIntent();
        int exerciseId = intent.getIntExtra("exerciseId",-1);

        presenter.displayExercise(exerciseId);
    }

    @Override
    public void navigateToViewExercises() {
        try {
            Intent destination = new Intent(this, ViewExercisesActivity.class);
            destination.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(destination);
        } catch(Exception e) {
        }
    }

    @Override
    public void navigateToEditExercise(int exerciseId) {
        try {
            Intent destination = new Intent(this, CreateExerciseActivity.class);
            destination.putExtra("exerciseId",exerciseId);
            startActivity(destination);
        } catch(Exception e) {
//            Log.e(TAG, "An error occurred while navigating to the View Exercise Screen",e);
        }
    }


    @OnClick(R.id.btnEditExercise)
    public void editExerciseOnClick(){
        presenter.btnEditExerciseClicked();
    }

    @Override
    public void setViewExerciseName(String name) {
        txtViewExerciseName.setText(name);
    }

    @Override
    public void setViewExerciseDescription(String description) {
        txtViewExerciseDescription.setText(description);
    }

    @Override
    public void setViewExerciseType(String type) {
        txtViewExerciseType.setText(type);
    }

    @Override
    public void setViewExerciseTargetAreas(String areas) {
        txtViewExerciseTargetAreas.setText(areas);
    }
}
