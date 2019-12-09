package com.example.myfitnessapp.ui.MainScreen;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.R;
import com.example.myfitnessapp.data.local.WorkOut;
import com.example.myfitnessapp.ui.Interfaces.AddToFavorites;
import com.example.myfitnessapp.ui.Interfaces.OnWorkoutClicked;
import com.squareup.picasso.Picasso;

public class WorkOutViewHolder extends RecyclerView.ViewHolder {
    TextView nameTV, affectedMuscleTV;
    ImageView workoutIV, addToFavoritesIV;


    public void bind(final WorkOut workOut, final AddToFavorites addToFavorites) {
        nameTV.setText(workOut.getName());
        affectedMuscleTV.setText(workOut.getAffectedMuscle());
        if (workOut.isFav())
            addToFavoritesIV.setImageResource(R.drawable.ic_add_to_favorites_on);
        else addToFavoritesIV.setImageResource(R.drawable.ic_add_to_favorites_off);
        addToFavoritesIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFavorites.addToFavorites(workOut);
                bind(workOut, addToFavorites);
            }
        });



        Picasso.get().setLoggingEnabled(true);
        Picasso.get().load(workOut.getImage()).into(workoutIV);
    }

    public WorkOutViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTV = itemView.findViewById(R.id.tv_workout_name);
        affectedMuscleTV = itemView.findViewById(R.id.tv_affected_muscle);
        workoutIV = itemView.findViewById(R.id.iv_workout);
        addToFavoritesIV = itemView.findViewById(R.id.iv_add_to_fav);
    }

}
