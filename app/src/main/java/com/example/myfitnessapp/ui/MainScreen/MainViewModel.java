package com.example.myfitnessapp.ui.MainScreen;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfitnessapp.data.local.WorkOut;
import com.example.myfitnessapp.repository.LocalDataSource;
import com.example.myfitnessapp.repository.RemoteDataSource;

import java.util.List;


public class MainViewModel extends ViewModel {
    LocalDataSource localDataSource;
    RemoteDataSource remoteDataSource;
    LiveData<List<WorkOut>> listLiveData;
    static  LiveData<List<WorkOut>> currentList ;

    public MainViewModel() {
        this.localDataSource = new LocalDataSource();
        remoteDataSource = new RemoteDataSource();
        listLiveData = localDataSource.listLiveData();
    }

    public LiveData<List<WorkOut>> getWorkOutLiveData() {
        return listLiveData;
    }

    public void updateFavorites(String userName, WorkOut workOut) {
        if (workOut.isIsFav())
            remoteDataSource.addToUserFavorites(userName, workOut);
        else remoteDataSource.deleteFromUserFavorites(userName, workOut);
        localDataSource.updateFavorites(workOut);
    }

    public LiveData<List<WorkOut>> getUserFavorites(String userName){
        return remoteDataSource.getUserWorkouts(userName);
    }
    public LiveData<List<WorkOut>> getRemoteWorkOutList() {
        return remoteDataSource.getWorkouts();
    }

    public void saveWorkouts(List<WorkOut> workOuts) {
        localDataSource.saveWorkouts(workOuts);
    }
}
