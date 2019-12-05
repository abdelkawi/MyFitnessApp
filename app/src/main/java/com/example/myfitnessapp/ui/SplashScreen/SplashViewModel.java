package com.example.myfitnessapp.ui.SplashScreen;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfitnessapp.data.local.WorkOut;
import com.example.myfitnessapp.repository.LocalDataSource;
import com.example.myfitnessapp.repository.RemoteDataSource;

import java.util.List;

public class SplashViewModel extends ViewModel {
    RemoteDataSource remoteDataSource;
    LocalDataSource localDataSource ;

    public SplashViewModel() {
        remoteDataSource = new RemoteDataSource();
        localDataSource= new LocalDataSource();
    }

    public LiveData<List<WorkOut>> getLocalWorkOutList() {
        return remoteDataSource.getWorkouts();
    }
    public void saveWorkouts(List<WorkOut> workOuts){
        localDataSource.saveWorkouts(workOuts);
    }
}
