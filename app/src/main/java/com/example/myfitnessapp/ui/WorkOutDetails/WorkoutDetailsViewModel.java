package com.example.myfitnessapp.ui.WorkOutDetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfitnessapp.data.local.WorkOut;
import com.example.myfitnessapp.repository.LocalDataSource;
import com.example.myfitnessapp.repository.RemoteDataSource;

public class WorkoutDetailsViewModel extends ViewModel {
    LocalDataSource localDataSource;
    RemoteDataSource remoteDataSource ;


    public WorkoutDetailsViewModel(){
        localDataSource = new LocalDataSource();
        remoteDataSource = new RemoteDataSource();
    }

    public LiveData<WorkOut> getWorkOut(String workOutName){
        return localDataSource.getWorOutByName(workOutName);
    }
    public void updateFavorites(String userName, WorkOut workOut) {
        if (workOut.isFav())
            remoteDataSource.addToUserFavorites(userName, workOut);
        else remoteDataSource.deleteFromUserFavorites(userName, workOut);
        localDataSource.updateFavorites(workOut);
    }

}
