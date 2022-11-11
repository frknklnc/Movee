package com.example.movee.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.repository.MoviesRepository
import com.example.movee.uimodels.movies.MovieDetailUiModel
import com.example.movee.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MoviesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val movieDetails = MutableStateFlow<MovieDetailUiModel?>(null)

    init {
        loadMovieDetails(checkNotNull(savedStateHandle.get<Int>("movieId")))

    }

    private fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val result = repository.getMovieDetails(movieId = movieId)
            when (result) {
                is Resource.Success -> {
                    result.data.let {
                        movieDetails.value = it
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