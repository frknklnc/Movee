package com.example.movee.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.repository.TvSeriesRepository
import com.example.movee.uimodels.tvseries.PopularTvSeriesUiModel
import com.example.movee.uimodels.tvseries.TopRatedTvSeriesUiModel
import com.example.movee.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvSeriesViewModel @Inject constructor(private val repository: TvSeriesRepository): ViewModel() {

    val popularTvSeriesList = MutableStateFlow<List<PopularTvSeriesUiModel>>(listOf())
    val topRatedTvSeriesList = MutableStateFlow<List<TopRatedTvSeriesUiModel>>(listOf())

    init {
        loadPopularTvSeries()
        loadTopRatedTvSeries()
    }

    private fun loadPopularTvSeries(){
        viewModelScope.launch {
            val result = repository.getPopularTvSeries()
            when(result){
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

    private fun loadTopRatedTvSeries(){
        viewModelScope.launch {
            val result = repository.getTopRatedTvSeries()
            when(result){
                is Resource.Success -> {
                    result.data?.let {
                        topRatedTvSeriesList.value = it
                    }
                }
                is  Resource.Error -> {
                    result.message?.let {

                    }
                }
            }
        }
    }
}