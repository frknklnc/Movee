package com.example.movee.data.repository

import android.util.Log
import com.example.movee.data.remote.service.TvService
import com.example.movee.uimodels.CreditUiModel
import com.example.movee.uimodels.tv.PopularTvUiModel
import com.example.movee.uimodels.tv.TopRatedTvUiModel
import com.example.movee.uimodels.tv.TvDetailUiModel
import com.example.movee.util.Resource
import javax.inject.Inject

class TvRepository @Inject constructor(
    private val service: TvService,
) {

    suspend fun getPopularTv(): Resource<List<PopularTvUiModel>> {
        return try {
            val response = service.getPopularTv()
            if (response.isSuccessful) {
                response.body()!!.let { res ->
                    return@let Resource.Success(res.tvSeries.map { it.toUiModel() })
                }
            } else {
                Resource.Error("Error.")
            }
        } catch (e: Exception) {
            Resource.Error("${e.message}")
        }

    }

    suspend fun getTopRatedTv(): Resource<List<TopRatedTvUiModel>> {
        return try {
            val response = service.getTopRatedTv()
            if (response.isSuccessful) {
                response.body()!!.let { res ->
                    return@let Resource.Success(res.tvSeries.map { it.toUiModel() })
                }

            } else {
                Resource.Error("Error.")
            }
        } catch (e: Exception) {
            Resource.Error("${e.message}")
        }
    }

    suspend fun getTvDetails(tvSeriesId: Int): Resource<TvDetailUiModel> {
        return try {
            val response = service.getTvDetails(tvSeriesId = tvSeriesId)
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.e("errorr", response.body().toString())
                    return@let Resource.Success(it.toUiModel())
                } ?: Resource.Error("Error.")

            } else {
                Resource.Error("Error.")
            }
        } catch (e: Exception) {

            Resource.Error("${e.message}")
        }
    }

    suspend fun getTvCredits(tvSeriesId: Int): Resource<List<CreditUiModel>> {
        return try {
            val response = service.getTvCredits(tvSeriesId = tvSeriesId)
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

