package com.example.myfitnessapp.ui.MainScreen;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.R;
import com.example.myfitnessapp.data.local.WorkOut;

public class WorkOutViewHolder extends RecyclerView.ViewHolder {
    TextView nameTV, affectedMuscleTV;
    ImageView workoutIV;
    public void bind(WorkOut workOut){
        nameTV.setText(workOut.getName());
        affectedMuscleTV.setText(workOut.getAffectedMuscle());
    }

    public WorkOutViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTV = itemView.findViewById(R.id.tv_workout_name);
        affectedMuscleTV = itemView.findViewById(R.id.tv_affected_muscle);
        workoutIV = itemView.findViewById(R.id.iv_workout);
    }

}
