package com.example.myfitnessapp.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myfitnessapp.data.remote.FireBaseMuscle;

@Database(entities = {WorkOut.class, FireBaseMuscle.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;
    private static final String DATABASE_NAME = "my-fitness-app";
    private static Object LOCK = new Object();

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext()
                        , AppDatabase.class, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return sInstance;
    }
    public abstract WorkOutDao workOutDao();
    public abstract MusclesDao musclesDao();

    public static AppDatabase getInstance() {
        return sInstance;
    }
}

