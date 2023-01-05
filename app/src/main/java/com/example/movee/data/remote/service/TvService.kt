package com.example.movee.data.remote.service

import com.example.movee.data.remote.model.CreditsResponse
import com.example.movee.data.remote.model.tv.PopularTvResponse
import com.example.movee.data.remote.model.tv.TopRatedTvResponse
import com.example.movee.data.remote.model.tv.TvDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TvService {

    @GET(POPULAR_TV)
    suspend fun getPopularTv(): Response<PopularTvResponse>

    @GET(TOP_RATED_TV)
    suspend fun getTopRatedTv(): Response<TopRatedTvResponse>

    @GET(TV_DETAIL)
    suspend fun getTvDetails(
        @Path("tv_id") tvSeriesId: Int
    ): Response<TvDetailResponse>

    @GET(TV_CREDITS)
    suspend fun getTvCredits(
        @Path("tv_id") tvSeriesId: Int
    ): Response<CreditsResponse>

    companion object {
        const val POPULAR_TV = "tv/popular"
        const val TOP_RATED_TV = "tv/top_rated"
        const val TV_DETAIL = "tv/{tv_id}"
        const val TV_CREDITS = "tv/{tv_id}/credits"
    }
}