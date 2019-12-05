package com.example.myfitnessapp.application;

import android.app.Application;


import com.example.myfitnessapp.data.local.AppDatabase;
import com.google.firebase.FirebaseApp;

public class App extends Application {
    AppDatabase appDatabase ;
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext());
        appDatabase = AppDatabase.getInstance(getApplicationContext());

    }
}
