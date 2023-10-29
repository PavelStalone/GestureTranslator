package com.ortin.gesturetranslator.app.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.ortin.gesturetranslator.R
import com.ortin.gesturetranslator.databinding.ActivityMainBinding
import com.ortin.gesturetranslator.domain.usecases.SaveLoadSettingsUseCase
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var saveLoadSettingsUseCase: SaveLoadSettingsUseCase

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val previousEntry = navController.previousBackStackEntry
                Timber.tag("MainActivity").e("onBackPressed: previousEntry = %s", previousEntry)
                if (previousEntry == null) {
                    finish()
                }
            }
        })

        if (saveLoadSettingsUseCase.getTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        setContentView(binding.root)

        init()
        initListeners()
    }

    private fun init() {
        navController = findNavController(binding.navHostFragment.id)
        //NavigationUI.setupWithNavController(navigationView, navController)
        hideControl()
    }

    private fun initListeners() {
        binding.topBar.menuBtn.setOnClickListener {
            if (binding.drawerLayoutId.isOpen) {
                binding.drawerLayoutId.close()
            } else {
                binding.drawerLayoutId.open()
            }
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

        binding.menuNavigationView.setNavigationItemSelectedListener { item: MenuItem ->
            if (item.itemId != navController.currentDestination?.id) {
                when (item.itemId) {
                    R.id.mainFragment -> navController.navigate(R.id.mainFragment)

                    R.id.gestureListFragment -> {
                        if (navController.currentDestination?.id == R.id.mainFragment) {
                            navController.navigate(R.id.action_mainFragment_to_gestureListFragment)
                        } else {
                            navController.navigate(R.id.gestureListFragment)
                        }
                    }

                    R.id.settingsFragment -> {
                        if (navController.currentDestination?.id == R.id.mainFragment) {
                            navController.navigate(R.id.action_mainFragment_to_settingsFragment)
                        } else {
                            navController.navigate(R.id.settingsFragment)
                        }
                    }

                    R.id.informationFragment -> {
                        if (navController.currentDestination?.id == R.id.mainFragment) {
                            navController.navigate(R.id.action_mainFragment_to_informationFragment)
                        } else {
                            navController.navigate(R.id.informationFragment)
                        }
                    }

                    R.id.bugReportFragment -> {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfYhL5HzYcU939IDzZaLKuvblHfcme5FoPHW5-qPmvCA-5obg/viewform?usp=sf_link")
                        )
                        startActivity(intent)
                    }
                }
                binding.drawerLayoutId.close()
                return@setNavigationItemSelectedListener true
            }
            false
        }
    }

    private fun hideControl() {
        binding.topBar.root.visibility = View.GONE
        binding.drawerLayoutId.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun showControl() {
        binding.topBar.root.visibility = View.VISIBLE
        this.binding.drawerLayoutId.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }
}
