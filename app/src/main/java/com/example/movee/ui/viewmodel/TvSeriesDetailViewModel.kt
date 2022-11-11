package com.example.movee.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.repository.TvSeriesRepository
import com.example.movee.uimodels.tvseries.TvSeriesDetailUiModel
import com.example.movee.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvSeriesDetailViewModel @Inject constructor(
    private val repository: TvSeriesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val tvSeriesDetails = MutableStateFlow<TvSeriesDetailUiModel?>(null)

    init {
        loadTvSeriesDetails(checkNotNull(savedStateHandle.get<Int>("tvSeriesId")))
    }

    private fun loadTvSeriesDetails(tvSeriesId: Int) {
        viewModelScope.launch {
            val result = repository.getTvSeriesDetails(tvSeriesId = tvSeriesId)
            when (result) {
                is Resource.Success -> {
                    result.data.let {
                        tvSeriesDetails.value = it
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