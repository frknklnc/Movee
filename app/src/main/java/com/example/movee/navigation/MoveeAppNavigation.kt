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
import com.example.movee.ui.view.*

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val scrollState = rememberScrollState()
    val items = listOf(Route.MainScreen, Route.TvSeriesScreen)

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
            startDestination = Route.MainScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Route.MainScreen.route) {
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

            composable(route = Route.TvSeriesScreen.route) {
                TvScreen(navController = navController, scrollState = scrollState)
            }

            composable(
                route = Route.TvDetailScreen.route + "/{tvSeriesId}",
                arguments = listOf(navArgument("tvSeriesId") {
                    type = NavType.IntType
                })
            ) {
                TvSDetailScreen(navController = navController, scrollState = scrollState)
            }

            composable(
                route = Route.CastScreen.route + "/{castId}",
                arguments = listOf(navArgument("castId") {
                    type = NavType.IntType
                })
            ) {
                CastScreen(navController = navController, scrollState = scrollState)
            }
        }
    }

}
