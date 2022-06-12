package com.swagbear.chalk_mobile.screens.create_exercise;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.swagbear.chalk_mobile.R;
import com.swagbear.chalk_mobile.screens.create_exercise.mvp.CreateExerciseContract;
import com.swagbear.chalk_mobile.dependency_injection.App;
import com.swagbear.chalk_mobile.enums.ExerciseType;
import com.swagbear.chalk_mobile.enums.TargetArea;
import com.swagbear.chalk_mobile.screens.view_exercises.ViewExercisesActivity;
import com.swagbear.chalk_mobile.utilities.Validators;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateExerciseActivity extends AppCompatActivity implements CreateExerciseContract.View {

    @Inject
    CreateExerciseContract.Presenter presenter;

    @BindView(R.id.txtExerciseName)
    TextView txtExerciseName;

    @BindView(R.id.txtExerciseDescription)
    TextView txtExerciseDescription;

    //Button to save the exercise created by user input and leave screen
    @BindView(R.id.btnSave)
    Button btnSaveExercise;

    //Button used to cancel the new exercise and leave screen
    @BindView(R.id.btnCancel)
    Button btnCancel;

    //Toggle button used to show if exercise will be a favorite
    @BindView(R.id.btnFavorite)
    ToggleButton btnFavorite;

    @BindView(R.id.exerciseTypeRadioGroup)
    RadioGroup exerciseTypeRadioGroup;

    @BindView(R.id.rdbWeight)
    RadioButton rdbWeight;

    @BindView(R.id.rdbTime)
    RadioButton rdbTime;

    @BindView(R.id.rdbDistance)
    RadioButton rdbDistance;

    @BindView(R.id.btnDeleteExercise)
    ImageButton deleteExercise;

    @BindViews({R.id.chbBack,R.id.chbShoulders,R.id.chbCalf,R.id.chbQuad,R.id.chbTriceps,
            R.id.chbChest,R.id.chbBiceps,R.id.chbHamstrings,R.id.chbLowerBack})
    List<CheckBox> targetAreaCheckBoxes;

    private static final Logger LOGGER = Logger.getLogger("CreateExerciseActivity");

    private static final String TAG = "CreateExerciseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);
        setTitle(R.string.create_exercise_title);
        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        Validators.resetErrorList();

        Intent intent = getIntent();
        int exerciseId = intent.getIntExtra("exerciseId",-1);

        if(exerciseId != -1) {
            presenter.populateExerciseInfoForEdit(exerciseId);
        }
    }

    @Override
    public void navigateToViewExerciseScreen() {
        try {
            Intent destination = new Intent(this, ViewExercisesActivity.class);
            destination.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(destination);
        } catch(Exception e) {
            Log.e(TAG, "An error occurred while navigating to the View Exercise Screen",e);
        }
    }

    @Override
    public void navigateToViewExercisesScreen() {
        try {
            Intent destination = new Intent(this, ViewExercisesActivity.class);
            startActivity(destination);
        } catch(Exception e) {
            Log.e(TAG, "An error occurred while navigating to the View Exercises Screen",e);
        }
    }

    @Override
    public void notifyUser(String msg) {
        Toast notificationToast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        notificationToast.show();
    }

    @Override
    public void displayValidationErrors(String errors) {
        Toast errorToast = Toast.makeText(this, errors, Toast.LENGTH_LONG);
        errorToast.show();
    }

    @Override
    public String getExerciseName() {
        return txtExerciseName.getText().toString();
    }

    @Override
    public String getExerciseDescription() {
        return txtExerciseDescription.getText().toString();
    }


    @OnClick(R.id.btnSave)
    public void setBtnSaveExerciseOnClick() {
        presenter.saveExerciseClicked();
    }

    @OnClick(R.id.btnCancel)
    public void setBtnCancelOnClick() {
        presenter.cancelClicked();
    }

    @Override
    public ExerciseType getExerciseType() {

        switch (exerciseTypeRadioGroup.getCheckedRadioButtonId()) {
            case R.id.rdbWeight:
                return ExerciseType.WEIGHT;
            case R.id.rdbTime:
                return ExerciseType.TIME;
            case R.id.rdbDistance:
                return ExerciseType.DISTANCE;
        }

        return null;
    }

    @Override
    public ArrayList<TargetArea> getTargetAreas() {
        ArrayList<TargetArea> targetAreas = new ArrayList<>();
        for(CheckBox cbArea : targetAreaCheckBoxes){
            if(cbArea.isChecked()) {
                TargetArea area = TargetArea.getTargetAreaByName(cbArea.getText().toString());
                if (area != null) {
                    targetAreas.add(area);
                }
            }
        }
        return targetAreas;
    }

    @Override
    public boolean getIsFavorite() {
        return btnFavorite.isChecked();
    }

    @Override
    public void setExerciseName(String name) {
        txtExerciseName.setText(name);
    }

    @Override
    public void setExerciseDescription(String description) {
        txtExerciseDescription.setText(description);
    }

    @Override
    public void setExerciseType(ExerciseType type) {
        switch (type) {
            case DISTANCE:
                exerciseTypeRadioGroup.check(R.id.rdbDistance);
                break;
            case TIME:
                exerciseTypeRadioGroup.check(R.id.rdbTime);
                break;
            case WEIGHT:
            exerciseTypeRadioGroup.check(R.id.rdbWeight);
                break;

        }
    }

    @Override
    public void setTargetAreas(ArrayList<TargetArea> targetAreas) {

        for(TargetArea area : targetAreas) {
            for(CheckBox checkBox: targetAreaCheckBoxes) {
                if(area.getName().equals(checkBox.getText().toString())){
                    checkBox.setChecked(true);
                }
            }

        }

    }

    @Override
    public void setIsFavorite(boolean isFavorite) {

    }


    @Override
    @OnClick(R.id.btnDeleteExercise)
    public void onClickDeleteButton() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Confirm delete exercise");
        dialogBuilder.setMessage("Are you sure you want to delete \"" + txtExerciseName.getText() + "\"?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.deleteExerciseButtonClicked();
            }
        });

        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialogBuilder.show();
    }

}