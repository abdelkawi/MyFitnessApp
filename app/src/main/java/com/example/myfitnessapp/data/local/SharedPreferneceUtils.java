package com.example.myfitnessapp.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferneceUtils {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SharedPreferneceUtils(Context context) {
        sharedPreferences = context.getSharedPreferences("UserName", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getUserName() {
        return sharedPreferences.getString("userName", "");
    }

    public void setUserName(String userName) {
        editor.putString("userName", userName);
        editor.commit();
    }

}
