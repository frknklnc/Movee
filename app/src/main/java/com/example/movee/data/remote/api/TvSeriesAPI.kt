package com.example.movee.data.remote.api

import com.example.movee.data.model.movies.MovieDetailResponse
import com.example.movee.data.model.tvseries.PopularTvSeriesResponse
import com.example.movee.data.model.tvseries.TopRatedTvSeriesResponse
import com.example.movee.data.model.tvseries.TvSeriesDetailReponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvSeriesAPI {

    @GET("3/tv/popular")
    suspend fun popularTvSeries(
        @Query("api_key") api_key: String): Response<PopularTvSeriesResponse>

    @GET("3/tv/top_rated")
    suspend fun topRatedTvSeries(
        @Query("api_key") api_key: String): Response<TopRatedTvSeriesResponse>

    @GET("3/tv/{tv_id}")
    suspend fun tvSeriesDetails(
        @Path("tv_id") tvSeriesId: Int,
        @Query("api_key") api_key: String) : Response<TvSeriesDetailReponse>

}