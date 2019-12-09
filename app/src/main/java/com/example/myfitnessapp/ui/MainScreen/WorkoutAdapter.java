package com.example.myfitnessapp.ui.MainScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.R;
import com.example.myfitnessapp.data.local.WorkOut;
import com.example.myfitnessapp.ui.Interfaces.AddToFavorites;
import com.example.myfitnessapp.ui.Interfaces.OnWorkoutClicked;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkOutViewHolder> {
    List<WorkOut> list;
    AddToFavorites addToFavorites;
    OnWorkoutClicked onWorkoutClicked;

    public WorkoutAdapter(List<WorkOut> list, AddToFavorites addToFavorites, OnWorkoutClicked onWorkoutClicked) {
        this.list = list;
        this.addToFavorites = addToFavorites;
        this.onWorkoutClicked = onWorkoutClicked;
    }

    @NonNull
    @Override
    public WorkOutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new WorkOutViewHolder(inflater.inflate(R.layout.item_workout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkOutViewHolder holder, final int position) {
        holder.bind(list.get(position), addToFavorites);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onWorkoutClicked.onWorkOutClicked(list.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
