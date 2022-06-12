package com.swagbear.chalk_mobile.screens.workout_guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.swagbear.chalk_mobile.R;
import com.swagbear.chalk_mobile.adapters.DisplayExerciseForGuideAdapter;
import com.swagbear.chalk_mobile.dependency_injection.App;
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.screens.home_screen.HomeScreenActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkoutGuideActivity extends AppCompatActivity implements WorkoutGuideContract.View {

    @Inject
    WorkoutGuideContract.Presenter presenter;

    @BindView(R.id.lblWorkoutGuideWorkoutName)
    TextView workoutName;

    @BindView(R.id.lstWorkoutGuideExercises)
    ListView exerciseList;

    @BindView(R.id.btnNext)
    Button nextExerciseButton;

    private  DisplayExerciseForGuideAdapter adapter;

    private int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_guide);
        setTitle(R.string.workout_guide_title);

        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);

        adapter = new DisplayExerciseForGuideAdapter(this, R.layout.display_exercise_adapter_view, new ArrayList<Exercise>());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);

        Intent intent = getIntent();
        int workoutId = intent.getIntExtra("workoutId",-1);

        if(workoutId != -1) {
            presenter.populateWorkoutInfo(workoutId);
        }
    }

    @Override
    public void setWorkoutName(String workoutName) {
        this.workoutName.setText(workoutName);
    }

    @Override
    public void setExerciseList(List<Exercise> exerciseList) {
        adapter.addAll(exerciseList);
        this.exerciseList.setAdapter(adapter);
    }

    @Override
    @OnClick(R.id.btnNext)
    public void nextExerciseButtonClicked() {
        presenter.progressThroughWorkout();
    }


    public void nextWorkout(int position) {
        adapter.setSelectedExerciseIndex(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToHomeScreen() {
        try {
            Intent destination = new Intent(this, HomeScreenActivity.class);
            startActivity(destination);
        } catch(Exception e) {

        }
    }

    @Override
    public void changeNextButtonText() {
        nextExerciseButton.setText(R.string.end_workout);
    }
}
