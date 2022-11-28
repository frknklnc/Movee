package com.example.movee.data.remote.service

import com.example.movee.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search/multi")
    suspend fun search(
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): Response<SearchResponse>

}