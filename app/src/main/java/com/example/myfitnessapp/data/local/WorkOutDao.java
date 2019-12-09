package com.example.myfitnessapp.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WorkOutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWorkOut(WorkOut workOut);

    @Query("SELECT * FROM workout")
    LiveData<List<WorkOut>> getWokrouts();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateWorkOut(WorkOut workOut);

    @Query("SELECT * FROM workout WHERE  affectedMuscle LIKE :muscleName")
    LiveData<List<WorkOut>> getWorkOutsByMuscle(String muscleName);


    @Query("SELECT * FROM workout WHERE name LIKE :name")
    LiveData<WorkOut> getWorkOutByName(String name);


}
