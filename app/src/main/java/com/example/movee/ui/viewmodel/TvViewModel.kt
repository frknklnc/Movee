package com.example.movee.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.repository.TvRepository
import com.example.movee.uimodels.tv.PopularTvUiModel
import com.example.movee.uimodels.tv.TopRatedTvUiModel
import com.example.movee.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(private val repository: TvRepository) : ViewModel() {

    val popularTvSeriesList = MutableStateFlow<List<PopularTvUiModel>>(listOf())
    val topRatedTvSeriesList = MutableStateFlow<List<TopRatedTvUiModel>>(listOf())

    init {
        loadPopularTvSeries()
        loadTopRatedTvSeries()
    }

    private fun loadPopularTvSeries() {
        viewModelScope.launch {
            val result = repository.getPopularTv()
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        popularTvSeriesList.value = it
                    }
                }
                is Resource.Error -> {
                    result.message?.let {
                    }
                }
            }
        }
    }

    private fun loadTopRatedTvSeries() {
        viewModelScope.launch {
            val result = repository.getTopRatedTv()
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        topRatedTvSeriesList.value = it
                    }
                }
                is Resource.Error -> {
                    result.message?.let {

                    }
                }
            }
        }
    }
}