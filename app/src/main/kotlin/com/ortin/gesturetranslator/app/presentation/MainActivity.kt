package com.ortin.gesturetranslator.app.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.navigation.compose.rememberNavController
import com.ortin.gesturetranslator.databinding.ActivityMainBinding
import com.ortin.gesturetranslator.domain.managers.SettingsManager
import com.ortin.gesturetranslator.main.components.navbars.BottomNavigationBar
import com.ortin.gesturetranslator.main.components.navbars.navigationBarItems
import com.ortin.gesturetranslator.main.navigation.MainApplicationScreenFlow
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var saveSettingsManager: SettingsManager

    private lateinit var binding: ActivityMainBinding

    private lateinit var mGetContent: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        registerPermission()
        checkPermission()

//        if (saveSettingsManager.getSettings().theme) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        } else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        }
//        setContentView(binding.root)
//
//        hideTopBarAndLockDrawer()
    }

    override fun onStart() {
        super.onStart()
//        setUpBackPressedDispatcher()
//        setUpDestinationChangedListener()
//        setUpDrawerMenuButtonClickListener()
//        setUpNavigationItemSelectedListener()
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

    private fun openBugReportForm() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            "https://docs.google.com/forms/d/e/1FAIpQLSfYhL5HzYcU939IDzZaLKuvblHfcme5FoPHW5-qPmvCA-5obg/viewform?usp=sf_link".toUri()
        )
        startActivity(intent)
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
}
