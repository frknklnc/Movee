package com.example.movee.data.remote.service

import com.example.movee.data.model.actor.CastCreditResponse
import com.example.movee.data.model.actor.CastDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CastService {

    @GET("person/{person_id}")
    suspend fun actorDetail(
        @Path("person_id") castId: Int,
        @Query("api_key") api_key: String) : Response<CastDetailResponse>


    @GET("person/{person_id}/combined_credits")
    suspend fun actorCredit(
        @Path("person_id") castId: Int,
        @Query("api_key") api_key: String) : Response<CastCreditResponse>


}