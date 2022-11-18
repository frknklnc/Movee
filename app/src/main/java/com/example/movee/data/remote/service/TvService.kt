package com.example.movee.data.remote.service

import com.example.movee.data.model.CreditsResponse
import com.example.movee.data.model.tv.PopularTvResponse
import com.example.movee.data.model.tv.TopRatedTvResponse
import com.example.movee.data.model.tv.TvDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvService {

    @GET("tv/popular")
    suspend fun popularTv(
        @Query("api_key") api_key: String): Response<PopularTvResponse>

    @GET("tv/top_rated")
    suspend fun topRatedTv(
        @Query("api_key") api_key: String): Response<TopRatedTvResponse>

    @GET("tv/{tv_id}")
    suspend fun tvDetails(
        @Path("tv_id") tvSeriesId: Int,
        @Query("api_key") api_key: String) : Response<TvDetailResponse>

    @GET("tv/{tv_id}/credits")
    suspend fun tvCredits(
        @Path("tv_id") tvSeriesId: Int,
        @Query("api_key") api_key: String) : Response<CreditsResponse>

}