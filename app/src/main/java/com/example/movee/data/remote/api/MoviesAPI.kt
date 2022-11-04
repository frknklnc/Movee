package com.example.movee.data.remote.api

import com.example.movee.data.model.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {
    // https://api.themoviedb.org/
    @GET("3/movie/popular")
    suspend fun popularMovies(@Query("api_key") api_key: String): Response<PopularMoviesResponse>
}