package com.example.movee.data.remote.service

import com.example.movee.data.remote.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET(SEARCH)
    suspend fun getSearch(
        @Query("query") query: String
    ): Response<SearchResponse>

    companion object {
        const val SEARCH = "search/multi"
    }

}