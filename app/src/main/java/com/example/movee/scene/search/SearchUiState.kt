package com.example.movee.scene.search

import com.example.movee.uimodels.SearchUiModel

data class SearchUiState(
    val isLoading: Boolean = false,
    val data: List<SearchUiModel> = emptyList(),
    val error: String = ""
){
    fun removeList() = copy(
        data = listOf()
    )
}