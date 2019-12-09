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
    boolean isFav;
    String image;
    String description;
    String video;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

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
