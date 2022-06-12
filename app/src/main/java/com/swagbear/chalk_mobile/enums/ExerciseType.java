package com.swagbear.chalk_mobile.enums;

/**
 * Created by valju on 7/12/2018.
 */

public enum ExerciseType {

    WEIGHT(1,"Weight"),
    TIME(2,"Time"),
    DISTANCE(3,"Distance");

    private int id;
    private String name;

     ExerciseType(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public static ExerciseType getExerciseTypeById(int value) {

        for(ExerciseType type: ExerciseType.values()){
            if(type.getId() == value) {
                return type;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
