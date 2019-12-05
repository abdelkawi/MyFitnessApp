package com.example.myfitnessapp.ui.LoginScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.myfitnessapp.R;
import com.example.myfitnessapp.ui.MainScreen.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText userNameEt;
    Button loginBtn;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameEt = findViewById(R.id.et_username);
        loginBtn = findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(this);
        loginViewModel = new LoginViewModel();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginViewModel.verifyName(userNameEt.getText().toString());
                loginViewModel.getIsUserLogged().observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean) {
                            finish();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    }
                });
                break;
        }
    }

}
