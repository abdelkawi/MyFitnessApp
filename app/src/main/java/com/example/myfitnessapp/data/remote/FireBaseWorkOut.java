package com.example.myfitnessapp.data.remote;

import com.example.myfitnessapp.data.local.WorkOut;

public class FireBaseWorkOut {
    String name;
    String affectedMuscle;
    String function;

    public FireBaseWorkOut(String name, String affectedMuscle, String function) {
        this.name = name;
        this.affectedMuscle = affectedMuscle;
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAffectedMuscle() {
        return affectedMuscle;
    }

    public void setAffectedMuscle(String affectedMuscle) {
        this.affectedMuscle = affectedMuscle;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
    public WorkOut toWorkout(){
        WorkOut workOut = new WorkOut();
        workOut.setAffectedMuscle(affectedMuscle);
        workOut.setFunction(function);
        workOut.setName(name);
        return workOut;
    }
}
