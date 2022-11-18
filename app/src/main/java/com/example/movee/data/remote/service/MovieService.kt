package com.example.movee.data.remote.service

import com.example.movee.data.model.movie.NowPlayingMovieResponse
import com.example.movee.data.model.movie.PopularMovieResponse
import com.example.movee.data.model.movie.MovieDetailResponse
import com.example.movee.data.model.CreditsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun popularMovies(
        @Query("api_key") api_key: String): Response<PopularMovieResponse>

    @GET("movie/now_playing")
    suspend fun nowPlayingMovies(
        @Query("api_key") api_key: String) : Response<NowPlayingMovieResponse>

    @GET("movie/{movie_id}")
    suspend fun movieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String) : Response<MovieDetailResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun movieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String) : Response<CreditsResponse>

}