package com.example.gesturetranslator.presentation;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gesturetranslator.R;
import com.example.gesturetranslator.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    public NavController navController;
    public NavHostFragment navHostFragment;
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        Constant.MAIN = this;

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        drawerLayout = binding.drawerLayoutId;
        navigationView = binding.menuNavigationView;

        initListeners();
    }

    private void initListeners() {
        navigationView.setNavigationItemSelectedListener(this);

        binding.topBar.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info_btn:
                navController.navigate(R.id.action_mainFragment_to_informationFragment);
                break;
            case R.id.home_btn:
//                navController.pop
        }

        drawerLayout.close();
        return false;
    }
}