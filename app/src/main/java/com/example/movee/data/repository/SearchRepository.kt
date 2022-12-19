package com.example.movee.data.repository

import com.example.movee.data.remote.service.ApiService
import com.example.movee.uimodels.SearchUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepository @Inject constructor(private val service: ApiService) {

    suspend fun getSearch(query: String): Flow<List<SearchUiModel>> {
        return flow {
            val response = service.search(query = query)
            if (response.isSuccessful) {
                response.body()!!.let { res ->
                    emit(res.results.map { it.toUiModel() })
                }
            } else {
                emit(listOf())
            }
        }
    }
}