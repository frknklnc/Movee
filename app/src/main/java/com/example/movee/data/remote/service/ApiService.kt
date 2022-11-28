package com.example.movee.data.remote.service

import com.example.movee.data.model.CreditsResponse
import com.example.movee.data.model.SearchResponse
import com.example.movee.data.model.actor.CastCreditResponse
import com.example.movee.data.model.actor.CastDetailResponse
import com.example.movee.data.model.movie.MovieDetailResponse
import com.example.movee.data.model.movie.NowPlayingMovieResponse
import com.example.movee.data.model.movie.PopularMovieResponse
import com.example.movee.data.model.tv.PopularTvResponse
import com.example.movee.data.model.tv.TopRatedTvResponse
import com.example.movee.data.model.tv.TvDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //movie

    @GET("movie/popular")
    suspend fun popularMovies(
        @Query("api_key") api_key: String
    ): Response<PopularMovieResponse>

    @GET("movie/now_playing")
    suspend fun nowPlayingMovies(
        @Query("api_key") api_key: String
    ): Response<NowPlayingMovieResponse>

    @GET("movie/{movie_id}")
    suspend fun movieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String
    ): Response<MovieDetailResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun movieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String
    ): Response<CreditsResponse>

    //Tv

    @GET("tv/popular")
    suspend fun popularTv(
        @Query("api_key") api_key: String
    ): Response<PopularTvResponse>

    @GET("tv/top_rated")
    suspend fun topRatedTv(
        @Query("api_key") api_key: String
    ): Response<TopRatedTvResponse>

    @GET("tv/{tv_id}")
    suspend fun tvDetails(
        @Path("tv_id") tvSeriesId: Int,
        @Query("api_key") api_key: String
    ): Response<TvDetailResponse>

    @GET("tv/{tv_id}/credits")
    suspend fun tvCredits(
        @Path("tv_id") tvSeriesId: Int,
        @Query("api_key") api_key: String
    ): Response<CreditsResponse>

    //Cast

    @GET("person/{person_id}")
    suspend fun actorDetail(
        @Path("person_id") castId: Int,
        @Query("api_key") api_key: String
    ): Response<CastDetailResponse>


    @GET("person/{person_id}/combined_credits")
    suspend fun actorCredit(
        @Path("person_id") castId: Int,
        @Query("api_key") api_key: String
    ): Response<CastCreditResponse>

    //Search

    @GET("search/multi")
    suspend fun search(
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): Response<SearchResponse>


}