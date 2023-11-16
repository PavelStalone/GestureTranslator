package com.ortin.gesturetranslator.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.ortin.gesturetranslator.ui.components.navbar.navigationBarItems.navigationBarItems

@Composable
fun navigationBar(selectedItem: Int) {
    NavigationBar {
        .forEachIndexed { index, item ->
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
        bottomBar = {
            NavigationBar()
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {}
    }
}
