package com.example.myfitnessapp.repository;


import androidx.lifecycle.LiveData;

import com.example.myfitnessapp.data.local.AppDatabase;
import com.example.myfitnessapp.data.local.WorkOut;
import com.example.myfitnessapp.data.remote.FireBaseMuscle;

import java.util.List;


public class LocalDataSource {
    AppDatabase appDatabase;

    public LocalDataSource() {
        appDatabase = AppDatabase.getInstance();
    }

    public void saveWorkouts(List<WorkOut> workOuts) {
        for (WorkOut workOut : workOuts)
            appDatabase.workOutDao().insertWorkOut(workOut);
    }

    public void saveMuscles(List<FireBaseMuscle> muscles) {
        for (FireBaseMuscle muscle : muscles)
            appDatabase.musclesDao().insertMuscle(muscle);
    }

    public LiveData<List<WorkOut>> listLiveData() {
        return appDatabase.workOutDao().getWokrouts();
    }

    public LiveData<List<FireBaseMuscle>> getMuscles() {
        return appDatabase.musclesDao().getMuscles();
    }

    public void updateFavorites(WorkOut workOut) {
        appDatabase.workOutDao().updateWorkOut(workOut);
    }

    public LiveData<List<WorkOut>> getWorOutByMuscle(String muscleName) {
        return appDatabase.workOutDao().getWorkOutsByMuscle(muscleName);
    }

    public LiveData<WorkOut> getWorOutByName(String workoutName) {
        return appDatabase.workOutDao().getWorkOutByName(workoutName);
    }


}
