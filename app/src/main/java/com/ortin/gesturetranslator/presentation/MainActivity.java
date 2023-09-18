package com.ortin.gesturetranslator.presentation;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.navigation.NavigationView;
import com.ortin.gesturetranslator.R;
import com.ortin.gesturetranslator.databinding.ActivityMainBinding;
import com.ortin.gesturetranslator.domain.usecases.SaveLoadSettingsUseCase;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;

    @Inject
    SaveLoadSettingsUseCase saveLoadSettingsUseCase;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (saveLoadSettingsUseCase.getTheme())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();
        initListeners();
    }

    private void init() {
        navController = ((NavHostFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment))).getNavController();

        drawerLayout = binding.drawerLayoutId;
        navigationView = binding.menuNavigationView;

        //NavigationUI.setupWithNavController(navigationView, navController);
        hideControl();
    }

    private void initListeners() {
        binding.topBar.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isOpen()) drawerLayout.close();
                else drawerLayout.open();
            }
        });

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() != R.id.logotypeFragment) {
                    showControl();
                    navController.removeOnDestinationChangedListener(this);
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() != navController.getCurrentDestination().getId()) {
                if (item.getItemId() == R.id.mainFragment) {
                    navController.navigate(R.id.mainFragment);
                } else if (item.getItemId() == R.id.gestureListFragment) {
                    if (navController.getCurrentDestination().getId() == R.id.mainFragment) {
                        navController.navigate(R.id.action_mainFragment_to_gestureListFragment);
                    } else {
                        navController.navigate(R.id.gestureListFragment);
                    }
                } else if (item.getItemId() == R.id.settingsFragment) {
                    if (navController.getCurrentDestination().getId() == R.id.mainFragment) {
                        navController.navigate(R.id.action_mainFragment_to_settingsFragment);
                    } else {
                        navController.navigate(R.id.settingsFragment);
                    }
                } else if (item.getItemId() == R.id.informationFragment) {
                    if (navController.getCurrentDestination().getId() == R.id.mainFragment) {
                        navController.navigate(R.id.action_mainFragment_to_informationFragment);
                    } else {
                        navController.navigate(R.id.informationFragment);
                    }
                }

                drawerLayout.close();
                return true;
            }
            return false;
        });
    }

    private void hideControl() {
        binding.topBar.getRoot().setVisibility(View.GONE);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private void showControl() {
        binding.topBar.getRoot().setVisibility(View.VISIBLE);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void onBackPressed() {
        var previousEntry = navController.getPreviousBackStackEntry();
        Log.e("MainActivity", "onBackPressed: previousEntry = " + previousEntry);

        if (previousEntry == null) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}