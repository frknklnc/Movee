package com.example.movee.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movee.R
import com.example.movee.scene.cast.CastScreen
import com.example.movee.scene.login.LoginScreen
import com.example.movee.scene.map.MapScreen
import com.example.movee.scene.movie.MoviesScreen
import com.example.movee.scene.moviedetail.MovieDetailScreen
import com.example.movee.scene.profile.ProfileScreen
import com.example.movee.scene.search.SearchScreen
import com.example.movee.scene.tv.TvScreen
import com.example.movee.scene.tvdetail.TvDetailScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val scrollState = rememberScrollState()
    val items = listOf(Route.MovieScreen, Route.TvScreen, Route.SearchScreen, Route.ProfileScreen)

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = null,
                                modifier = Modifier.size(27.dp),
                                tint = colorResource(id = R.color.green)
                            )
                        },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.LoginScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = Route.LoginScreen.route
            ) {
                LoginScreen(
                    onNavToHomePage = {
                        navController.navigate(Route.MovieScreen.route) {
                            launchSingleTop = true
                            popUpTo(Route.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                    })
            }
            composable(
                route = Route.MovieScreen.route
            ) {
                MoviesScreen(navController = navController)
            }

            composable(
                Route.MovieDetailScreen.route + "/{movieId}",
                arguments = listOf(navArgument("movieId") {
                    type = NavType.IntType
                })
            ) {
                MovieDetailScreen(navController = navController, scrollState = scrollState)
            }

            composable(
                route = Route.TvScreen.route
            ) {
                TvScreen(navController = navController, scrollState = scrollState)
            }

            composable(
                route = Route.TvDetailScreen.route + "/{tvSeriesId}",
                arguments = listOf(navArgument("tvSeriesId") {
                    type = NavType.IntType
                })
            ) {
                TvDetailScreen(navController = navController, scrollState = scrollState)
            }

            composable(
                route = Route.CastScreen.route + "/{castId}",
                arguments = listOf(navArgument("castId") {
                    type = NavType.IntType
                })
            ) {
                CastScreen(navController = navController, scrollState = scrollState)
            }
            composable(
                route = Route.SearchScreen.route
            ) {
                SearchScreen(navController = navController)
            }

            composable(
                route = Route.ProfileScreen.route
            ) {
                ProfileScreen(navController = navController)
            }
            composable(route = Route.MapScreen.route) {
                MapScreen()
            }
        }
    }
}
