package com.uranodev.affirmation_app.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uranodev.affirmation_app.ui.screens.AffirmationsScreen
import com.uranodev.affirmation_app.ui.screens.CreateAffirmationScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.LIST.routeName
    ) {
        composable(route = Screen.LIST.routeName) {
            AffirmationsScreen() {
                navController.navigate(Screen.CREATE.routeName)
            }
        }
        composable(route = Screen.CREATE.routeName) {
            CreateAffirmationScreen(
                onCloseScreen = {
                    navController.navigateUp()
                }
            )
        }
    }
}