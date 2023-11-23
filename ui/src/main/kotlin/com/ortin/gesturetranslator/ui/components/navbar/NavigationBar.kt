package com.ortin.gesturetranslator.ui.components.navbar

import androidx.compose.foundation.layout.padding
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
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme

@Composable
fun BottomNavigationBar(
    items: List<NavigationBarItems>,
    modifier: Modifier = Modifier,
    selectedItem: Int = 0
) {
    NavigationBar(
        modifier = modifier
    ) {
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
fun NavigationBarPreview() {
    GestureTranslatorTheme {
        Surface {
            Scaffold(
                content = { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {}
                },
                bottomBar = {
                    BottomNavigationBar(items = navigationBarItems)
                }
            )
        }
    }
}
