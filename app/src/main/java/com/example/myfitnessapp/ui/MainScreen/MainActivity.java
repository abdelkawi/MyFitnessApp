package com.example.myfitnessapp.ui.MainScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myfitnessapp.R;

import com.example.myfitnessapp.data.local.WorkOut;
import com.example.myfitnessapp.ui.Favorites.MyFavoritesFragment;
import com.example.myfitnessapp.ui.MusclesScreen.WorkOutAffectedMuscleFragment;

import java.util.List;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    SwipeRefreshLayout swipeRefreshLayout;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = findViewById(R.id.refresh);
        mainViewModel = new MainViewModel();
        swipeRefreshLayout.setOnRefreshListener(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, new MainFragment(), "Main")
                .addToBackStack("Main")
                .commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        switch (itemThatWasClickedId) {
            case R.id.muscles:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new WorkOutAffectedMuscleFragment(), "Muscles")
                        .addToBackStack("Muscles")
                        .commit();
                break;
            case R.id.my_favorites:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new MyFavoritesFragment(), "Favorites")
                        .addToBackStack("Favorites")
                        .commit();
                break;
        }
        return true;
    }

    @Override
    public void onRefresh() {
        new UpdateData().execute();
        swipeRefreshLayout.setRefreshing(false);
    }

    class UpdateData extends AsyncTask<Void, Void, LiveData<List<WorkOut>>> {

        @Override
        protected LiveData<List<WorkOut>> doInBackground(Void... voids) {
            return mainViewModel.getRemoteWorkOutList();
        }

        @Override
        protected void onPostExecute(final LiveData<List<WorkOut>> listLiveData) {
            super.onPostExecute(listLiveData);
            listLiveData.observe(MainActivity.this, new Observer<List<WorkOut>>() {
                @Override
                public void onChanged(List<WorkOut> workOuts) {
                    mainViewModel.saveWorkouts(workOuts);
                }
            });
        }
    }
}
