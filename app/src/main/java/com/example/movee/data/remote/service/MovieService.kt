package com.example.movee.data.remote.service

import com.example.movee.data.remote.model.CreditsResponse
import com.example.movee.data.remote.model.movie.MovieDetailResponse
import com.example.movee.data.remote.model.movie.NowPlayingMovieResponse
import com.example.movee.data.remote.model.movie.PopularMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(POPULAR_MOVIE)
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1
    ): Response<PopularMovieResponse>

    @GET(NOW_PLAYING_MOVIE)
    suspend fun getNowPlayingMovies(): Response<NowPlayingMovieResponse>

    @GET(MOVIE_DETAIL)
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): Response<MovieDetailResponse>

    @GET(MOVIE_CREDITS)
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int
    ): Response<CreditsResponse>

    companion object {

        const val POPULAR_MOVIE = "movie/popular"
        const val NOW_PLAYING_MOVIE = "movie/now_playing"
        const val MOVIE_DETAIL = "movie/{movie_id}"
        const val MOVIE_CREDITS = "movie/{movie_id}/credits"
    }
}