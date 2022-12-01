package com.example.movee.navigation

import com.example.movee.R


interface BottomNavigationItem{
    val icon : Int
    val title: String
}

sealed class Route(val route: String){
    object MovieScreen : Route("movie_screen"), BottomNavigationItem {
        override val icon: Int =  R.drawable.ic_movie
        override val title: String = "Movies"
    }

    object TvScreen : Route("tv_screen"), BottomNavigationItem {
        override val  icon = R.drawable.ic_tvseries
        override val title: String = "Tv Series"
    }
    object SearchScreen : Route("search_screen"), BottomNavigationItem {
        override val icon: Int = R.drawable.ic_search
        override val title: String = "Search"
    }

    object ProfileScreen : Route("profile_screen"), BottomNavigationItem {
        override val icon: Int = R.drawable.ic_person
        override val title: String = "Profile"
    }

    object MovieDetailScreen: Route("movie_detail_screen")
    object TvDetailScreen: Route("tv_detail_screen")
    object CastScreen: Route("cast_screen")
    object LoginScreen: Route("login_screen")
    object SignUpScreen: Route ("sing_up_screen")
}
