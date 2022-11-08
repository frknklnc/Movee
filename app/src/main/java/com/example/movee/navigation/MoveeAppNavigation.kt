package com.example.movee.navigation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movee.ui.view.DetailScreen
import com.example.movee.ui.view.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val scrollState = rememberScrollState()
    NavHost(navController = navController, startDestination = Route.MainScreen.route) {
        composable(route = Route.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(
            Route.DetailScreen.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.IntType
            })
        ){
            DetailScreen(navController = navController, scrollState = scrollState)

    }
}

}