package com.example.movee.data.repository

import com.example.movee.data.remote.service.SearchService
import com.example.movee.uimodels.SearchUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepository @Inject constructor(private val service: SearchService) {

    suspend fun getSearch(query: String): Flow<List<SearchUiModel>> {
        return flow {
            val response = service.getSearch(query = query)
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