package com.example.myfitnessapp.ui.WorkOutsList;

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
import com.example.myfitnessapp.data.local.SharedPreferneceUtils;
import com.example.myfitnessapp.data.local.WorkOut;
import com.example.myfitnessapp.ui.Interfaces.AddToFavorites;
import com.example.myfitnessapp.ui.Interfaces.OnWorkoutClicked;
import com.example.myfitnessapp.ui.MainScreen.WorkoutAdapter;
import com.example.myfitnessapp.ui.WorkOutDetails.WorkOutDetailsFragment;

import java.util.ArrayList;
import java.util.List;

public class WorkOutListFragment extends Fragment implements AddToFavorites , OnWorkoutClicked {
    WorkOutListViewModel workOutListViewModel;
    RecyclerView workOutRV;
    SharedPreferneceUtils sharedPreferneceUtils;
    List<WorkOut> currentList= new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workouts_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        workOutRV = view.findViewById(R.id.rv_workouts);
        sharedPreferneceUtils = new SharedPreferneceUtils(getContext());
        workOutRV.setLayoutManager(new GridLayoutManager(getContext(), 2));
        workOutListViewModel = new WorkOutListViewModel();
        workOutListViewModel.getWorkOutsByMuscle(getArguments().getString("muscleName")).observe(getActivity(), new Observer<List<WorkOut>>() {
            @Override
            public void onChanged(List<WorkOut> workOuts) {
                workOutRV.setAdapter(new WorkoutAdapter(workOuts, WorkOutListFragment.this,WorkOutListFragment.this));
                currentList= workOuts;
            }
        });
    }

    @Override
    public void addToFavorites(WorkOut workOut) {
        workOut.setFav(!workOut.isFav());
        workOutListViewModel.updateFavorites(sharedPreferneceUtils.getUserName(), workOut);
    }

    @Override
    public void onWorkOutClicked(WorkOut workOut) {
        WorkOutDetailsFragment fragment = new WorkOutDetailsFragment();
        fragment.setCurrentList(currentList);
        Bundle bundle = new Bundle();
        bundle.putString("name", workOut.getName());
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager()
                .beginTransaction().replace(R.id.main_container, fragment, "details")
                .addToBackStack("details")
                .commit();
    }
}
