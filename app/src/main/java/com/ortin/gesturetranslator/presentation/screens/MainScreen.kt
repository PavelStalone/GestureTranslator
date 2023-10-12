package com.ortin.gesturetranslator.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(currRoute: String?, currItem: Int) {
    var topBarTitle = ""
    var showBackArrow = false
    var selectedItem = currItem
    val navController = rememberNavController()

//    var selectedItem by remember { mutableIntStateOf(0) }
//    val currRoute = navBackStackEntry?.destination?.route
//    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()

    if (currRoute != null) {
        if (settingsScreenRoutes.contains(currRoute)) {
            topBarTitle = settingsScreens.filter { i -> i.route == currRoute }[0].title
            showBackArrow = true
        } else {
            topBarTitle = bottomBarScreens.filter { i -> i.route == currRoute }[0].title
            showBackArrow = false
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                ),
                title = { Text(text = topBarTitle) },
                navigationIcon = {
                    if (showBackArrow) {
                        IconButton(onClick = { 
                            navController.navigateUp()
                        }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                bottomBarScreens.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(item.route)
                        },
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NavGraph(navController, currRoute!!)
        }
    }
}