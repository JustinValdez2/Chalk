package com.swagbear.chalk_mobile.enums;

/**
 * Created by Justin on 8/29/2018.
 */

public enum TargetArea {

    BACK("Back",1),
    SHOULDERS("Shoulders",2),
    CALF("Calf",3),
    CHEST("Chest",4),
    TRICEPS("Triceps",5),
    BICEPS("Biceps",6),
    QUADRICEPS("Quadriceps",7),
    HAMSTRINGS("Hamstrings",8),
    LOWERBACK("Lower Back", 9);

    private String name;
    private int id;

    TargetArea(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static TargetArea getTargetAreaById(int id) {

        for(TargetArea area: TargetArea.values()){
            if(area.getId() == id) {
                return area;
            }
        }

        return null;
    }

    public static TargetArea getTargetAreaByName(String name) {

        for(TargetArea area: TargetArea.values()){
            if(area.getName().toUpperCase().equals(name.toUpperCase())) {
                return area;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
