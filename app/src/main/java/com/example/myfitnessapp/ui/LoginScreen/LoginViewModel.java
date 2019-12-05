package com.example.myfitnessapp.ui.LoginScreen;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myfitnessapp.repository.RemoteDataSource;

public class LoginViewModel {
    RemoteDataSource remoteDataSource;
    MutableLiveData<Boolean> userIsLogged;

    public LoginViewModel() {
        this.remoteDataSource = new RemoteDataSource();
    }

    public void verifyName(String user) {
        userIsLogged= remoteDataSource.verifyOrAddUser(user);

    }
     public LiveData<Boolean> getIsUserLogged(){
         return userIsLogged;
     }


}
