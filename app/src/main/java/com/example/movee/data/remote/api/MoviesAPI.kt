package com.example.movee.data.remote.api

import com.example.movee.data.model.NowPlayingMoviesResponse
import com.example.movee.data.model.PopularMoviesResponse
import com.example.movee.data.model.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {
    // https://api.themoviedb.org/
    @GET("3/movie/popular")
    suspend fun popularMovies(
        @Query("api_key") api_key: String): Response<PopularMoviesResponse>

    @GET("3/movie/now_playing")
    suspend fun nowPlayingMovies(
        @Query("api_key") api_key: String) : Response<NowPlayingMoviesResponse>

    @GET("3/movie/{movie_id}")
    suspend fun movieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String) : Response<MovieDetailResponse>
}