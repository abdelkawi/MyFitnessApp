package com.example.myfitnessapp.ui.MusclesScreen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfitnessapp.data.remote.FireBaseMuscle;
import com.example.myfitnessapp.repository.LocalDataSource;
import com.example.myfitnessapp.repository.RemoteDataSource;

import java.util.List;

public class MusclesViewModel extends ViewModel {

    LocalDataSource localDataSource;
    public  MusclesViewModel(){
        localDataSource= new LocalDataSource();
    }
    public LiveData<List<FireBaseMuscle>> getMuscles(){
        return localDataSource.getMuscles();
    }

}
