package com.example.myfitnessapp.ui.MainScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.R;
import com.example.myfitnessapp.data.local.WorkOut;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    MainViewModel mainViewModel;
    RecyclerView listRv;
    WorkoutAdapter workoutAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listRv = view.findViewById(R.id.rv_workouts);
        listRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mainViewModel = new MainViewModel();
        mainViewModel.getWorkOutLiveData().observe(this, new Observer<List<WorkOut>>() {
            @Override
            public void onChanged(List<WorkOut> workOuts) {
                workoutAdapter = new WorkoutAdapter(workOuts);
                listRv.setAdapter(workoutAdapter);
            }
        });

    }
}
