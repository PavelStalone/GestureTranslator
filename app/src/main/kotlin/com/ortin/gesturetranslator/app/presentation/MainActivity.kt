package com.ortin.gesturetranslator.app.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation.findNavController
import androidx.navigation.compose.rememberNavController
import com.ortin.gesturetranslator.R
import com.ortin.gesturetranslator.databinding.ActivityMainBinding
import com.ortin.gesturetranslator.domain.managers.SettingsManager
import com.ortin.gesturetranslator.main.components.navbar.BottomNavigationBar
import com.ortin.gesturetranslator.main.components.navbar.navigationBarItems
import com.ortin.gesturetranslator.main.navigation.MainApplicationScreenFlow
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var saveSettingsManager: SettingsManager

    private lateinit var mGetContent: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (saveSettingsManager.getSettings().theme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        registerPermission()
        checkPermission()
    }

    private fun registerPermission() {
        mGetContent = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { result ->
            if (result) {
                checkPermission()
            } else {
                mGetContent.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            mGetContent.launch(Manifest.permission.CAMERA)
        } else {
            setContent {
                GestureTranslatorTheme {
                    val navController = rememberNavController()

                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = navigationBarItems,
                                navController = navController
                            )
                        }
                    ) {
                        MainApplicationScreenFlow(
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize(),
                            navController = navController
                        )
                    }
                }
            }
        }
    }

    private fun openBugReportForm() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            "https://docs.google.com/forms/d/e/1FAIpQLSfYhL5HzYcU939IDzZaLKuvblHfcme5FoPHW5-qPmvCA-5obg/viewform?usp=sf_link".toUri()
        )
        startActivity(intent)
    }
}
