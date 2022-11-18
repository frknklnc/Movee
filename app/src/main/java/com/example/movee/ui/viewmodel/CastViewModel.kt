package com.example.movee.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.repository.CastRepository
import com.example.movee.uimodels.actor.CastCreditUiModel
import com.example.movee.uimodels.actor.CastDetailModel
import com.example.movee.uimodels.actor.CastDetailUiModel
import com.example.movee.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CastViewModel @Inject constructor(
    private val repository: CastRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    val uiState = MutableStateFlow<CastDetailModel?>(null)

    init {
        fetchData(checkNotNull(savedStateHandle.get<Int>("castId")))
    }

    private fun fetchData(castId: Int) {
        viewModelScope.launch {
            val actorDetailDeferred = async {
                when (val result = repository.getActorDetail(castId = castId)) {
                    is Resource.Success -> {
                        result.data
                    }
                    else -> null
                }
            }

            val actorCreditDeferred = async {
                when (val result = repository.getActorCredit(castId = castId)) {
                    is Resource.Success -> {
                        result.data
                    }
                    else -> null
                }
            }

            toActorDetailModel(actorDetailDeferred.await(), actorCreditDeferred.await())

        }
    }

    private fun toActorDetailModel(
        actorDetail: CastDetailUiModel?,
        creditList: List<CastCreditUiModel>?
    ) {
        uiState.value = CastDetailModel(
            actorDetailUiModel = actorDetail,
            creditList = creditList ?: listOf()
        )
    }
}