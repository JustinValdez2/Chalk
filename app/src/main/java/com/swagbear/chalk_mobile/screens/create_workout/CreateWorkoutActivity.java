package com.swagbear.chalk_mobile.screens.create_workout;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.swagbear.chalk_mobile.R;
import com.swagbear.chalk_mobile.adapters.AddExerciseAdapter;
import com.swagbear.chalk_mobile.dependency_injection.App;
import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.screens.view_workouts.ViewWorkoutsActivity;
import com.swagbear.chalk_mobile.utilities.Validators;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateWorkoutActivity extends AppCompatActivity  implements CreateWorkoutContract.View{

    @Inject
    CreateWorkoutContract.Presenter presenter;

    @BindView(R.id.txtWorkoutName)
    EditText workoutName;

    @BindView(R.id.txtIntensity)
    EditText workoutIntensity;

    @BindView(R.id.btnSaveWorkout)
    Button saveWorkoutButton;

    @BindView(R.id.btnCancelSaveWorkout)
    Button cancelWorkoutButton;

    @BindView(R.id.btnAddExercise)
    Button addExerciseButton;

    @BindView(R.id.lstAddExerciseList)
    ListView addExerciseList;

    @BindView(R.id.txtWorkoutDescription)
    EditText workoutDescription;

    @BindView(R.id.btnWorkoutIsFavorite)
    ToggleButton isFavorite;

    @BindView(R.id.btnDeleteWorkout)
    ImageButton deleteWorkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);
        setTitle(R.string.create_workout_title);

        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        Validators.resetErrorList();

        Intent intent = getIntent();
        int workoutId = intent.getIntExtra("workoutId",-1);

        boolean isEditing = workoutId != -1;
        if(isEditing) {
            presenter.populateWorkoutInfoForEdit(workoutId);
        }
        setDeleteButtonVisibility(isEditing);
    }

    @Override
    public void navigateToViewWorkoutsScreen() {
        try {
            Intent destination = new Intent(this, ViewWorkoutsActivity.class);
            destination.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(destination);
        } catch(Exception e) {
            notifyUser("Error navigating to view workouts screen");
        }
    }

    @Override
    public void updateWorkoutExerciseList(List<Exercise> exerciseList) {
        AddExerciseAdapter exerciseListArrayAdapter = new AddExerciseAdapter(this,R.layout.add_exercise_adapter_view, exerciseList);
        addExerciseList.setAdapter(exerciseListArrayAdapter);
    }

    @OnClick(R.id.btnSaveWorkout)
    public void saveWorkoutClicked() {
        presenter.saveWorkout();
    }

    @OnClick(R.id.btnAddExercise)
    public void addExerciseClicked() {
        LayoutInflater li = LayoutInflater.from(this);

        View addExercisePrompt = li.inflate(R.layout.prompt_add_exercise, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setView(addExercisePrompt);

        final Spinner exerciseSpinner = addExercisePrompt.findViewById(R.id.ddlAddExercise);

        List<Exercise> exercises = presenter.getSelectExerciseList();

        ArrayAdapter<Exercise> exerciseListArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, exercises);
        exerciseSpinner.setAdapter(exerciseListArrayAdapter);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Presenter add exercise to workouts exercise list
                        presenter.addExercise((Exercise)exerciseSpinner.getSelectedItem());
                        presenter.updateAddExerciseList();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @OnClick(R.id.btnCancelSaveWorkout)
    public void btnOnCancelButtonClicked() {
        presenter.cancelSaveWorkout();
    }


    public void btnOnDeleteExerciseClicked(View view) {
        Exercise deleteExercise = (Exercise)  addExerciseList.getAdapter().getItem(
                addExerciseList.getPositionForView(view));

        presenter.deleteExercise(deleteExercise);
        presenter.updateAddExerciseList();
    }

    @Override
    public String getWorkoutName() {
        return workoutName.getText().toString();
    }

    @Override
    public void setWorkoutName(String workoutName) {
        this.workoutName.setText(workoutName);
    }

    @Override
    public String getIntensity() {
        //Todo: add error handling in case it can't parse
        return workoutIntensity.getText().toString();
    }

    @Override
    public void setIntensity(String intensity) {
        this.workoutIntensity.setText(intensity);
    }


    @Override
    public boolean isFavorite() {
        return isFavorite.isChecked();
    }

    @Override
    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite.setChecked(isFavorite);
    }

    @Override
    public String getWorkoutDescription() {
        return workoutDescription.getText().toString();
    }

    @Override
    public void setWorkoutDescription(String workoutDescription) {
        this.workoutDescription.setText(workoutDescription);
    }

    @OnClick(R.id.btnDeleteWorkout)
    public void deleteWorkoutOnClick(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Confirm delete workout");
        dialogBuilder.setMessage("Are you sure you want to delete \"" + workoutName.getText() + "\"?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.deleteWorkoutClicked();
            }
        });

        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialogBuilder.show();
    }

    @Override
    public void setDeleteButtonVisibility(boolean visible) {
        deleteWorkoutButton.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void notifyUser(String msg) {
        Toast messageToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        messageToast.show();
    }


}
