package com.example.myfitnessapp.ui.SplashScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfitnessapp.R;
import com.example.myfitnessapp.data.local.WorkOut;
import com.example.myfitnessapp.ui.LoginScreen.LoginActivity;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    SplashViewModel splashViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashViewModel= new SplashViewModel();
        splashViewModel.getLocalWorkOutList().observe(this, new Observer<List<WorkOut>>() {
            @Override
            public void onChanged(List<WorkOut> workOuts) {
                Log.d("xxxx",workOuts.size()+"");
                splashViewModel.saveWorkouts(workOuts);
                goToLogin();
            }
        });

    }
    void goToLogin(){
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

}
