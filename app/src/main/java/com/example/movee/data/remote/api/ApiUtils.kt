package com.example.movee.data.remote.api

class ApiUtils {

    companion object{

        val BASE_URL = "https://api.themoviedb.org/"

        fun getMovies(): MoviesAPI {
            return RetrofitClient.getClient(BASE_URL).create(MoviesAPI::class.java)
        }
    }
}