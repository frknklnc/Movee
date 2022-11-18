package com.example.movee.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.repository.TvRepository
import com.example.movee.uimodels.CreditUiModel
import com.example.movee.uimodels.tv.TvDetailModel
import com.example.movee.uimodels.tv.TvDetailUiModel
import com.example.movee.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(
    private val repository: TvRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val uiState = MutableStateFlow<TvDetailModel?>(null)

    init {
        fetchData(checkNotNull(savedStateHandle.get<Int>("tvSeriesId")))
    }

    private fun fetchData(tvSeriesId: Int) {
        viewModelScope.launch {
            val castsDeferred = async {
                when (val result = repository.getTvCredits(tvSeriesId = tvSeriesId)) {
                    is Resource.Success -> {
                        result.data
                    }
                    else -> null
                }
            }

            val tvDetailDeferred = async {
                when (val result = repository.getTvDetails(tvSeriesId = tvSeriesId)) {
                    is Resource.Success -> {
                        result.data
                    }
                    else -> null
                }
            }

            toTvDetailModel(tvDetailDeferred.await(), castsDeferred.await())

        }
    }

    private fun toTvDetailModel(
        tvDetail: TvDetailUiModel?,
        casts: List<CreditUiModel>?
    ) {
        uiState.value = TvDetailModel(
            tvDetailUiModel = tvDetail,
            castList = casts ?: listOf()
        )
    }
}