package com.example.gesturetranslator.presentation;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gesturetranslator.R;
import com.example.gesturetranslator.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    public NavController navController;
    public NavHostFragment navHostFragment;
    public DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        Constant.MAIN = this;

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//ВОТ ОТ СЮДА
        drawerLayout = binding.drawerLayoutId;

//    ДО СЮДА)
    }


}