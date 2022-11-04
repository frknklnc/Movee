package com.example.movee.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movee.ui.view.DetailScreen
import com.example.movee.ui.view.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.MainScreen.route) {
        composable(route = Route.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Route.DetailScreen.route) {
            DetailScreen()
        }
    }

}