package com.example.movee.navigation

import com.example.movee.R


interface BottomNavigationItem{
    val icon : Int
    val title: String
}

sealed class Route(val route: String){
    object MainScreen : Route("main_screen"), BottomNavigationItem {
        override val icon: Int =  R.drawable.ic_movie
        override val title: String = "Movies"
    }

    object TvSeriesScreen : Route("tv_series_screen"), BottomNavigationItem {
        override val  icon = R.drawable.ic_tvseries
        override val title: String = "Tv Series"
    }

    object MovieDetailScreen: Route("movie_detail_screen")
    object TvSeriesDetailScreen: Route("tv_series_detail_screen")
}
