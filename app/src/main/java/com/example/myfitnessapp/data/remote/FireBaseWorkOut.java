package com.example.myfitnessapp.data.remote;

import com.example.myfitnessapp.data.local.WorkOut;

public class FireBaseWorkOut {
    String name;
    String affectedMuscle;
    String video;
    String description;
    boolean isIsFav;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String function;
    String image;

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsFav() {
        return isIsFav;
    }

    public void setIsFav(boolean fav) {
        isIsFav = fav;
    }

    public FireBaseWorkOut(String name, String affectedMuscle, String function
            , String image, String video, String description, boolean isIsFav) {
        this.name = name;
        this.affectedMuscle = affectedMuscle;
        this.function = function;
        this.image = image;
        this.video = video;
        this.isIsFav= isIsFav;
        this.description = description;
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

    public WorkOut toWorkout() {
        WorkOut workOut = new WorkOut();
        workOut.setAffectedMuscle(affectedMuscle);
        workOut.setFunction(function);
        workOut.setName(name);
        workOut.setImage(image);
        workOut.setDescription(description);
        workOut.setVideo(video);
        workOut.setIsFav(isIsFav);
        return workOut;
    }
}
