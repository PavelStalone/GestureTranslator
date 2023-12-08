package com.ortin.gesturetranslator.app.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.navigation.compose.rememberNavController
import com.ortin.gesturetranslator.app.navigation.ApplicationScreenFlow
import com.ortin.gesturetranslator.domain.managers.SettingsManager
import com.ortin.gesturetranslator.main.components.navbar.BottomNavigationBar
import com.ortin.gesturetranslator.main.components.navbar.navigationBarItems
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import dagger.hilt.android.AndroidEntryPoint
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
                    var isBottomBarVisible by remember { mutableStateOf(false) }

                    Scaffold(
                        bottomBar = {
                            AnimatedVisibility(visible = isBottomBarVisible) {
                                BottomNavigationBar(
                                    items = navigationBarItems,
                                    navController = navController
                                )
                            }
                        }
                    ) {
                        ApplicationScreenFlow(
                            onBottomBarVisibilityChange = { state -> isBottomBarVisible = state },
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize(),
                            mainNavController = navController
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
