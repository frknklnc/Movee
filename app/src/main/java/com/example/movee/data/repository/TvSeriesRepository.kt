package com.example.movee.data.repository

import android.util.Log
import com.example.movee.data.remote.api.TvSeriesAPI
import com.example.movee.uimodels.movies.MovieDetailUiModel
import com.example.movee.uimodels.tvseries.PopularTvSeriesUiModel
import com.example.movee.uimodels.tvseries.TopRatedTvSeriesUiModel
import com.example.movee.uimodels.tvseries.TvSeriesDetailUiModel
import com.example.movee.util.Constants.API_KEY
import com.example.movee.util.Resource
import javax.inject.Inject

class TvSeriesRepository @Inject constructor(private val api: TvSeriesAPI) {

    suspend fun getPopularTvSeries(): Resource<List<PopularTvSeriesUiModel>> {
        return try {
            val response = api.popularTvSeries(api_key = API_KEY)
            if (response.isSuccessful) {
                response.body()!!.let { res ->
                    return@let Resource.Success(res.tvSeries.map { it.toUiModel() })
                }
            } else {
                Resource.Error("Error.")
            }
        } catch (e: Exception) {
            e.message
            Resource.Error("Error.")
        }

    }

    suspend fun getTopRatedTvSeries(): Resource<List<TopRatedTvSeriesUiModel>> {
        return try {
            val response = api.topRatedTvSeries(api_key = API_KEY)
            if (response.isSuccessful) {
                response.body()!!.let { res ->
                    return@let Resource.Success(res.tvSeries.map { it.toUiModel() })
                }

            } else {
                Resource.Error("Error.")
            }
        } catch (e: Exception) {
            Resource.Error("Error.")
        }
    }

    suspend fun getTvSeriesDetails(tvSeriesId: Int): Resource<TvSeriesDetailUiModel> {
        return try {
            val response = api.tvSeriesDetails(api_key = API_KEY, tvSeriesId = tvSeriesId)
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.e("errorr",response.body().toString())
                    return@let Resource.Success(it.toUiModel())
                } ?: Resource.Error("Error.")

            } else {
                Resource.Error("Error.")
            }
        } catch (e: Exception) {

            Resource.Error("${e.message}")
        }
    }
}