package com.example.myfitnessapp.ui.MusclesScreen;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.R;
import com.example.myfitnessapp.data.remote.FireBaseMuscle;
import com.squareup.picasso.Picasso;

public class MuscleItemViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    ImageView imageView;
    public void bind(FireBaseMuscle fireBaseMuscle){
        name.setText(fireBaseMuscle.getName());
        Picasso.get().load(fireBaseMuscle.getImage()).into(imageView);
    }
    public MuscleItemViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_muscle);
        imageView= itemView.findViewById(R.id.iv_muscle);
    }
}
