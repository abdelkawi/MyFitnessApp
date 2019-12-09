package com.example.myfitnessapp.ui.MusclesScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.R;
import com.example.myfitnessapp.data.remote.FireBaseMuscle;
import com.example.myfitnessapp.ui.Interfaces.OnMuscleClicked;

import java.util.List;

public class MuscleItemAdapter extends RecyclerView.Adapter<MuscleItemViewHolder> {
    List<FireBaseMuscle> muscles;
    OnMuscleClicked onMuscleClicked;

    public MuscleItemAdapter(List<FireBaseMuscle> muscles, OnMuscleClicked onMuscleClicked) {
        this.muscles = muscles;
        this.onMuscleClicked = onMuscleClicked;
    }

    @NonNull
    @Override
    public MuscleItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MuscleItemViewHolder(inflater.inflate(R.layout.item_muscle, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MuscleItemViewHolder holder, final int position) {
        holder.bind(muscles.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMuscleClicked.onMuscleClicked(muscles.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return muscles.size();
    }
}
