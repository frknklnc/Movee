package com.example.movee.data.repository

import com.example.movee.data.remote.service.ApiService
import com.example.movee.uimodels.actor.CastCreditUiModel
import com.example.movee.uimodels.actor.CastDetailUiModel
import com.example.movee.util.Constants.API_KEY
import com.example.movee.util.Resource
import javax.inject.Inject

class CastRepository @Inject constructor(private val service: ApiService) {

    suspend fun getActorDetail(castId: Int): Resource<CastDetailUiModel> {
        return try {
            val response = service.actorDetail(api_key = API_KEY, castId = castId)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it.toUiModel())
                } ?: Resource.Error("Error.")
            } else {
                Resource.Error("Error.")

            }
        } catch (e: Exception) {
            Resource.Error("${e.message}")

        }
    }

    suspend fun getActorCredit(castId: Int): Resource<List<CastCreditUiModel>> {
        return try {
            val response = service.actorCredit(api_key = API_KEY, castId = castId)
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    return@let Resource.Success(res.cast.map { it.toUiModel() })
                } ?: Resource.Error("Error.")
            } else {
                Resource.Error("Error.")
            }
        } catch (e: Exception) {
            Resource.Error("${e.message}")
        }

    }
}