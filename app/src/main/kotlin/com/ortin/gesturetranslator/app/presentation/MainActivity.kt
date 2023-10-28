package com.ortin.gesturetranslator.app.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView
import com.ortin.gesturetranslator.R
import com.ortin.gesturetranslator.databinding.ActivityMainBinding
import com.ortin.gesturetranslator.domain.usecases.SaveLoadSettingsUseCase
import dagger.hilt.android.AndroidEntryPoint
import java.util.Objects
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var saveLoadSettingsUseCase: SaveLoadSettingsUseCase

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController = (Objects.requireNonNull<Fragment?>(supportFragmentManager.findFragmentById(R.id.nav_host_fragment)) as NavHostFragment).navController
    private var drawerLayout: DrawerLayout? = binding.drawerLayoutId
    private var navigationView: NavigationView? = binding.menuNavigationView

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        if (saveLoadSettingsUseCase.getTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        init()
        initListeners()
    }

    private fun init() {
        navController = (Objects.requireNonNull(
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        ) as NavHostFragment).navController
        drawerLayout = binding.drawerLayoutId
        navigationView = binding.menuNavigationView
        //NavigationUI.setupWithNavController(navigationView, navController);
        hideControl()
    }

    private fun initListeners() {
        binding.topBar.menuBtn.setOnClickListener {
            if (drawerLayout?.isOpen == true) drawerLayout?.close()
            else drawerLayout?.open()
        }

        navController.addOnDestinationChangedListener(
            object : NavController.OnDestinationChangedListener {
                override fun onDestinationChanged(
                    controller: NavController,
                    destination: NavDestination,
                    arguments: Bundle?
                ) {
                    if (destination.id != R.id.logotypeFragment) {
                        showControl()
                        controller.removeOnDestinationChangedListener(this)
                    }
                }
            }
        )

        navigationView?.setNavigationItemSelectedListener { item: MenuItem ->
            if (item.itemId != navController.currentDestination?.id) {
                if (item.itemId == R.id.mainFragment) {
                    navController.navigate(R.id.mainFragment)
                } else if (item.itemId == R.id.gestureListFragment) {
                    if (navController.currentDestination?.id == R.id.mainFragment) {
                        navController.navigate(R.id.action_mainFragment_to_gestureListFragment)
                    } else {
                        navController.navigate(R.id.gestureListFragment)
                    }
                } else if (item.itemId == R.id.settingsFragment) {
                    if (navController.currentDestination?.id == R.id.mainFragment) {
                        navController.navigate(R.id.action_mainFragment_to_settingsFragment)
                    } else {
                        navController.navigate(R.id.settingsFragment)
                    }
                } else if (item.itemId == R.id.informationFragment) {
                    if (navController.currentDestination?.id == R.id.mainFragment) {
                        navController.navigate(R.id.action_mainFragment_to_informationFragment)
                    } else {
                        navController.navigate(R.id.informationFragment)
                    }
                } else if (item.itemId == R.id.bugReportFragment) {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfYhL5HzYcU939IDzZaLKuvblHfcme5FoPHW5-qPmvCA-5obg/viewform?usp=sf_link")
                    )
                    startActivity(intent)
                }
                drawerLayout?.close()
                return@setNavigationItemSelectedListener true
            }
            false
        }
    }

    private fun hideControl() {
        binding.topBar.root.visibility = View.GONE
        drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun showControl() {
        binding.topBar.root.visibility = View.VISIBLE
        drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    @Deprecated("")
    override fun onBackPressed() {
        val previousEntry = navController.previousBackStackEntry
        Log.e("MainActivity", "onBackPressed: previousEntry = $previousEntry")
        if (previousEntry == null) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}
