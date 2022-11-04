package com.example.movee.navigation

sealed class Route(val route: String){
    object MainScreen: Route("main_screen")
    object DetailScreen: Route("detail_screen")
}
