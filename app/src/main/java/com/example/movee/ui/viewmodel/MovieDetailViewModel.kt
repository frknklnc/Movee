package com.example.movee.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.repository.MovieRepository
import com.example.movee.uimodels.CreditUiModel
import com.example.movee.uimodels.movie.MovieDetailUiModel
import com.example.movee.uimodels.movie.MovieDetailModel
import com.example.movee.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val uiState = MutableStateFlow<MovieDetailModel?>(null)

    init {
        fetchData(checkNotNull(savedStateHandle.get<Int>("movieId")))

    }

    private fun fetchData(movieId: Int) {
        viewModelScope.launch {
            val castDeferred = async {
                when(val result = repository.getMovieCredits(movieId)){
                    is Resource.Success -> {
                        result.data
                    }
                    else -> null
                }
            }
            val movieDetailDeferred = async {
                when(val result = repository.getMovieDetails(movieId)){
                    is Resource.Success -> {
                        result.data
                    }
                    else -> null
                }
            }
            toMovieDetailModel(movieDetailDeferred.await(), castDeferred.await())
        }
    }

    private fun toMovieDetailModel(movieDetail: MovieDetailUiModel?, cast: List<CreditUiModel>?){
        uiState.value = MovieDetailModel(movieDetailUiModel = movieDetail, castList = cast ?: listOf())
    }
}