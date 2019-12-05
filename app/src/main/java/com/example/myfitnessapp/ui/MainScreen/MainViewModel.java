package com.example.myfitnessapp.ui.MainScreen;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfitnessapp.data.local.WorkOut;
import com.example.myfitnessapp.repository.LocalDataSource;

import java.util.List;


public class MainViewModel extends ViewModel {
    LocalDataSource localDataSource;
    LiveData<List<WorkOut>> listLiveData;

    public MainViewModel() {
        this.localDataSource = new LocalDataSource();
        listLiveData =localDataSource.listLiveData();
    }

    public LiveData<List<WorkOut>> getWorkOutLiveData() {
        return listLiveData;
    }
}
