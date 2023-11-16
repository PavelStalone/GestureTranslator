package com.ortin.gesturetranslator.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun navigationBar(selectedItem: Int) {
    NavigationBar {
        bottomBarScreens.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = (selectedItem == index),
                onClick = {
                    // selection to this item and navigate
                },
                icon = {
                    if (selectedItem == index)
                        Icon(item.icon_filled, contentDescription = item.title)
                    else
                        Icon(item.icon_outlined, contentDescription = item.title)
                },
                label = {
                    Text(item.title)
                }
            )
        }
    }
}
