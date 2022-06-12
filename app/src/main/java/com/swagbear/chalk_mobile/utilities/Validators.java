package com.swagbear.chalk_mobile.utilities;

import com.swagbear.chalk_mobile.entities.Exercise;
import com.swagbear.chalk_mobile.entities.Workout;

public class Validators {

    public static StringBuilder errorList;

    public static void resetErrorList() {
        errorList = new StringBuilder();
    }

    public static boolean isEmptyOrNull(String string) {

        if(string == null) {
            return true;
        }

        if(string.isEmpty()) {
            return true;
        }

        return false;
    }

    public static boolean isValidInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidExercise(Exercise exercise) {
        boolean isValid = true;

        if(exercise == null) {
            getErrorList().append("Error occurred while retrieving exercise information\n");
            isValid = false;
        } else {
            if(exercise.getExerciseType() == null) {
                getErrorList().append("Please select an exercise type\n");
                isValid = false;
            }
            if(isEmptyOrNull(exercise.getInstructions())) {
                getErrorList().append("Please add an exercise description\n");
                isValid = false;
            }
            if(isEmptyOrNull(exercise.getName())){
                getErrorList().append("Please add an exercise name\n");
                isValid = false;
            }
            if(exercise.getTargetAreas() == null || exercise.getTargetAreas().isEmpty()){
                getErrorList().append("Please select a target area\n");
                isValid = false;
            }
        }

        return isValid;
    }

    public static boolean isValidWorkout(Workout workout) {
        boolean isValid = true;

        if(workout == null) {
            getErrorList().append("Error occurred while retrieving workout information\n");
            return false;
        } else {
            if(isEmptyOrNull(workout.getDescription())) {
                isValid = false;
                getErrorList().append("A workout description is required\n");
            }
            if((null == Integer.valueOf(workout.getIntensity()) )
                    || (workout.getIntensity() < 1 || workout.getIntensity() > 10)) {
                isValid = false;
                getErrorList().append("Intensity must be between 1-10\n");
            }
            if(isEmptyOrNull(workout.getName() )) {
                isValid = false;
                getErrorList().append("A workout name is required\n");
            }
//            if(workout.getChalkSetList().size() > 0) {
//                //TODO: Validate exercise list
//            }
        }

        return isValid;
    }

    public static StringBuilder getErrorList() {
        if(errorList == null) {
            errorList = new StringBuilder();
        }
        return errorList;
    }
}
