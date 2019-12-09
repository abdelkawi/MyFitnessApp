package com.example.myfitnessapp.ui.MusclesScreen;

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
import com.example.myfitnessapp.data.remote.FireBaseMuscle;
import com.example.myfitnessapp.ui.WorkOutsList.WorkOutListFragment;
import com.example.myfitnessapp.ui.Interfaces.OnMuscleClicked;

import java.util.List;

public class WorkOutAffectedMuscleFragment extends Fragment implements OnMuscleClicked {

    MusclesViewModel musclesViewModel;
    RecyclerView musclesRV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        musclesRV = view.findViewById(R.id.rv_muscles);
        musclesRV.setLayoutManager(new GridLayoutManager(getContext(), 2));
        musclesViewModel = new MusclesViewModel();
        musclesViewModel.getMuscles().observe(getActivity(), new Observer<List<FireBaseMuscle>>() {
            @Override
            public void onChanged(List<FireBaseMuscle> muscles) {
                if (muscles.size() > 0) {
                    musclesRV.setAdapter(new MuscleItemAdapter(muscles, WorkOutAffectedMuscleFragment.this));
                }
            }
        });
    }


    @Override
    public void onMuscleClicked(FireBaseMuscle fireBaseMuscle) {
        Fragment fragment = new WorkOutListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("muscleName", fireBaseMuscle.getName());
        fragment.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment, "List")
                .addToBackStack("List")
                .commit();
    }
}
