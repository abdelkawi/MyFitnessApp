package com.example.myfitnessapp.ui.widget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViewsService;

import static com.example.myfitnessapp.ui.Constants.SHARED_PREF;
import static com.example.myfitnessapp.ui.Constants.WORKOUT_SHARED_PREF;


public class MyWidgetRemoteViewsService extends RemoteViewsService {
SharedPreferences sharedPreferences;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        sharedPreferences = getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        String name= sharedPreferences.getString(WORKOUT_SHARED_PREF,"");
        return new RemoteViewService(name,getApplicationContext());
    }
}
