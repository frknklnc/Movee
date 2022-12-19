package com.example.movee.data.remote.service

import com.example.movee.data.remote.model.actor.CastCreditResponse
import com.example.movee.data.remote.model.actor.CastDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CastService {

    @GET(ACTOR_DETAIL)
    suspend fun getActorDetail(
        @Path("person_id") castId: Int
    ): Response<CastDetailResponse>

    @GET(ACTOR_CREDIT)
    suspend fun getActorCredit(
        @Path("person_id") castId: Int
    ): Response<CastCreditResponse>

    companion object {

        const val ACTOR_DETAIL = "person/{person_id}"
        const val ACTOR_CREDIT = "person/{person_id}/combined_credits"

    }
}