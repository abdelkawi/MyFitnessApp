package com.example.myfitnessapp.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myfitnessapp.data.remote.FireBaseMuscle;

import java.util.List;

@Dao
public interface MusclesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMuscle(FireBaseMuscle fireBaseMuscle);

    @Query("SELECT * FROM firebasemuscle")
    LiveData<List<FireBaseMuscle>> getMuscles();
}
