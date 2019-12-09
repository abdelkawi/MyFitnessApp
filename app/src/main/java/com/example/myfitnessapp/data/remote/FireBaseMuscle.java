package com.example.myfitnessapp.data.remote;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FireBaseMuscle {

    @PrimaryKey
    @NonNull
    String name;
    String image;

    public FireBaseMuscle(@NonNull String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
