package com.ortin.gesturetranslator.main.components.navbars

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    items: List<NavigationBarItem>,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier
    ) {
        items.forEach { item: NavigationBarItem ->
            NavigationBarItem(
                selected = (currentRoute == item.route),
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(NavigationBarItem.Home.route)
                        }
                    }
                },
                icon = {
                    if (currentRoute == item.route)
                        Icon(
                            painter = painterResource(id = item.iconFilled),
                            contentDescription = item.title
                        )
                    else
                        Icon(
                            painter = painterResource(id = item.iconOutlined),
                            contentDescription = item.title
                        )
                },
                label = {
                    Text(item.title)
                }
            )
        }
    }
}
