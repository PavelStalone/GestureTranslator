package com.ortin.gesturetranslator.ui.components.navbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ortin.gesturetranslator.ui.components.navbar.NavigationBarItems
import com.ortin.gesturetranslator.ui.components.navbar.navigationBarItems

@Composable
fun BottomNavigationBar(
    items: List<NavigationBarItems>,
    selectedItem: Int = 0,
    modifier: Modifier = Modifier
) {
    NavigationBar {
        items.forEachIndexed { index: Int, item: NavigationBarItems ->
            NavigationBarItem(
                selected = (selectedItem == index),
                onClick = {
                    // selection to this item and navigate
                },
                icon = {
                    if (selectedItem == index)
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
                    Text(
                        item.title
                    )
                }
            )
        }
    }
}

@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NavigationBarPreview() {
    Scaffold(
        content = { innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding)) {}
        },
        bottomBar = {
            BottomNavigationBar(items = navigationBarItems)
        }
    )
}
