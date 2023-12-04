package com.ortin.gesturetranslator.app.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ortin.gesturetranslator.feature.presentation.navigation.MainApplicationScreenFlow
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme

@Composable
fun GestureTranslatorScreenFlow(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        modifier = modifier.padding(paddingValues),
        startDestination = GestureTranslatorScreen.AuthScreenFlow.route
    ) {

        /**
         * Authorization screen flow
         */
        composable(
            route = GestureTranslatorScreen.AuthScreenFlow.route
        ) {
            GestureTranslatorTheme {
                Surface {
                    // TODO: Add @Composable func for AuthScreen flow
                }
            }
        }

        /**
         * Main application screen flow
         */
        composable(
            route = GestureTranslatorScreen.MainApplicationScreenFlow.route
        ) {
            GestureTranslatorTheme {
                Surface {
                    MainApplicationScreenFlow()
                }
            }
        }
    }
}
