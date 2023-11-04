package com.ortin.gesturetranslator.app.presentation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation.findNavController
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        if (saveLoadSettingsUseCase.getTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        setContentView(binding.root)

        hideTopBarAndLockDrawer()
    }

    override fun onStart() {
        super.onStart()
        setUpBackPressedDispatcher()
        setUpDestinationChangedListener()
        setUpDrawerMenuButtonClickListener()
        setUpNavigationItemSelectedListener()
    }

    private fun setUpBackPressedDispatcher() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val previousBackStackEntry =
                    findNavController(binding.navHostFragment).previousBackStackEntry
                Timber.d("onBackPressed: previousEntry = %s", previousBackStackEntry)

                if (previousBackStackEntry == null) {
                    finish()
                }
            }
        })
    }

    private fun setUpDestinationChangedListener() {
        findNavController(binding.navHostFragment).addOnDestinationChangedListener(
            object : NavController.OnDestinationChangedListener {
                override fun onDestinationChanged(
                    controller: NavController,
                    destination: NavDestination,
                    arguments: Bundle?
                ) {
                    Timber.d("onDestinationChanged: ${destination.displayName}")
                    if (destination.id != R.id.logotypeFragment) {
                        showTopBarAndUnlockDrawer()
                        controller.removeOnDestinationChangedListener(this)
                    }
                }
            }
        )
    }

    private fun setUpDrawerMenuButtonClickListener() {
        binding.topBar.menuBtn.setOnClickListener {
            if (binding.drawerLayoutId.isOpen) {
                binding.drawerLayoutId.close()
            } else {
                binding.drawerLayoutId.open()
            }
        }
    }

    private fun setUpNavigationItemSelectedListener() {
        binding.menuNavigationView.setNavigationItemSelectedListener { item: MenuItem ->
            val controller = findNavController(binding.navHostFragment)
            if (item.itemId != controller.currentDestination?.id) {
                when (item.itemId) {
                    R.id.mainFragment -> controller.navigate(R.id.mainFragment)

                    R.id.gestureListFragment -> navigateWithMainFragmentCheck(
                        controller,
                        R.id.action_mainFragment_to_gestureListFragment,
                        R.id.gestureListFragment
                    )

                    R.id.settingsFragment -> navigateWithMainFragmentCheck(
                        controller,
                        R.id.action_mainFragment_to_settingsFragment,
                        R.id.settingsFragment
                    )

                    R.id.informationFragment -> navigateWithMainFragmentCheck(
                        controller,
                        R.id.action_mainFragment_to_informationFragment,
                        R.id.informationFragment
                    )

                    R.id.bugReportFragment -> openBugReportForm()
                }
                binding.drawerLayoutId.close()
                return@setNavigationItemSelectedListener true
            }
            false
        }
    }

    private fun hideTopBarAndLockDrawer() {
        with(binding) {
            topBar.root.isVisible = false
            drawerLayoutId.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }
    }

    private fun showTopBarAndUnlockDrawer() {
        with(binding) {
            topBar.root.isVisible = true
            drawerLayoutId.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }

    private fun navigateWithMainFragmentCheck(
        controller: NavController,
        actionFromMain: Int,
        destinationId: Int
    ) {
        controller.navigate(
            if (controller.currentDestination?.id == R.id.mainFragment) {
                actionFromMain
            } else {
                destinationId
            }
        )
    }

    private fun openBugReportForm() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            "https://docs.google.com/forms/d/e/1FAIpQLSfYhL5HzYcU939IDzZaLKuvblHfcme5FoPHW5-qPmvCA-5obg/viewform?usp=sf_link".toUri()
        )
        startActivity(intent)
    }
}
