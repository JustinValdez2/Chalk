package com.swagbear.chalk_mobile.utilities;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.swagbear.chalk_mobile.enums.ExerciseType;
import com.swagbear.chalk_mobile.enums.TargetArea;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Converters {

    @TypeConverter
    public static ExerciseType fromIntToExerciseType(int type){
        return ExerciseType.getExerciseTypeById(type);
    }

    @TypeConverter
    public static int fromExerciseTypeToInt(ExerciseType type){
        return type.getId();
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public  String fromTargetAreasToJson(ArrayList<TargetArea> targetAreas) {
        return new Gson().toJson(targetAreas);
    }

    @TypeConverter
    public ArrayList<TargetArea> fromJsonToTargetAreasList(String json) {
        if(json == null || json.isEmpty()){

            return new ArrayList<TargetArea>();
        }

        Type listType = new TypeToken<ArrayList<TargetArea>>() {}.getType();

        return new Gson().fromJson(json,listType);
    }

}
