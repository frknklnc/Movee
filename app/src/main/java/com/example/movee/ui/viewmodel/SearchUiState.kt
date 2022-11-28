package com.example.movee.ui.viewmodel

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