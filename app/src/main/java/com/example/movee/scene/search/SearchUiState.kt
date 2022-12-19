package com.example.movee.scene.search

import com.example.movee.uimodels.SearchUiModel

data class SearchUiState(
    val isLoading: Boolean = false,
    val data: List<SearchUiModel> = emptyList(),
    val error: String? = null
) {
    fun removeList() = copy(
        data = listOf(),
        isLoading = false,
        error = null
    )

    fun showLoading() = copy(
        isLoading = true,
        error = null
    )

    fun showError(message: String) = copy(
        isLoading = false,
        data = emptyList(),
        error = message
    )

}