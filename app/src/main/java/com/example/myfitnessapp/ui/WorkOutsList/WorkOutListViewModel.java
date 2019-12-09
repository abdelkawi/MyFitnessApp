package com.example.myfitnessapp.ui.WorkOutsList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfitnessapp.data.local.WorkOut;
import com.example.myfitnessapp.repository.LocalDataSource;
import com.example.myfitnessapp.repository.RemoteDataSource;

import java.util.List;

public class WorkOutListViewModel extends ViewModel {

    LocalDataSource localDataSource;

    public WorkOutListViewModel() {
        localDataSource = new LocalDataSource();
        remoteDataSource = new RemoteDataSource();
    }

    public LiveData<List<WorkOut>> getWorkOutsByMuscle(String muscleName) {
        return localDataSource.getWorOutByMuscle(muscleName);
    }
    public void updateFavorites(String userName, WorkOut workOut) {
        if (workOut.isFav())
            remoteDataSource.addToUserFavorites(userName, workOut);
        else remoteDataSource.deleteFromUserFavorites(userName, workOut);
        localDataSource.updateFavorites(workOut);
    }    RemoteDataSource remoteDataSource;

}