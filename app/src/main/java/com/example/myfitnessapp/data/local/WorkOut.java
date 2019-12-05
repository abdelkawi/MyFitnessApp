package com.example.myfitnessapp.data.local;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WorkOut {
    @PrimaryKey
    @NonNull
    String name;
    String affectedMuscle;
    String function;

    @NonNull
    public String getName() {
        return name;
    }

    public String getAffectedMuscle() {
        return affectedMuscle;
    }

    public String getFunction() {
        return function;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAffectedMuscle(String affectedMuscle) {
        this.affectedMuscle = affectedMuscle;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
