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
import com.example.myfitnessapp.data.local.SharedPreferneceUtils;
import com.example.myfitnessapp.data.local.WorkOut;
import com.example.myfitnessapp.data.remote.FireBaseMuscle;
import com.example.myfitnessapp.ui.LoginScreen.LoginActivity;
import com.example.myfitnessapp.ui.MainScreen.MainActivity;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    SplashViewModel splashViewModel;
    SharedPreferneceUtils sharedPreferneceUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferneceUtils = new SharedPreferneceUtils(this);
        splashViewModel = new SplashViewModel();
        splashViewModel.getWorkOuts().observe(this, new Observer<List<WorkOut>>() {
            @Override
            public void onChanged(List<WorkOut> workOuts) {
                if (workOuts != null && workOuts.size() == 0) {
                    getDataAndSave();
                } else if (sharedPreferneceUtils.getUserName().equals("")) {
                    goToLogin();
                } else {
                    goToMain();
                }
            }
        });


    }

    void goToLogin() {
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    void goToMain() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    void getDataAndSave() {
        splashViewModel.getLocalWorkOutList().observe(this, new Observer<List<WorkOut>>() {
            @Override
            public void onChanged(List<WorkOut> workOuts) {
                Log.d("xxxx", workOuts.size() + "");
                splashViewModel.saveWorkouts(workOuts);
                splashViewModel.getMuscles().observe(SplashActivity.this, new Observer<List<FireBaseMuscle>>() {
                    @Override
                    public void onChanged(List<FireBaseMuscle> fireBaseMuscles) {
                        splashViewModel.saveMuscles(fireBaseMuscles);
                        goToLogin();
                    }
                });
            }
        });

    }

}
