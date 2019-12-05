package com.example.myfitnessapp.repository;


import androidx.lifecycle.LiveData;

import com.example.myfitnessapp.data.local.AppDatabase;
import com.example.myfitnessapp.data.local.WorkOut;

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
    public LiveData<List<WorkOut>> listLiveData (){
        return appDatabase.workOutDao().getWokrouts();
    }


}
