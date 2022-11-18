package com.example.movee.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.repository.MovieRepository
import com.example.movee.uimodels.movie.NowPlayingMovieUiModel
import com.example.movee.uimodels.movie.PopularMovieUiModel
import com.example.movee.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) :ViewModel() {

    val popularMoviesList = MutableStateFlow<List<PopularMovieUiModel>>(listOf())
    val nowPlayingMoviesList = MutableStateFlow<List<NowPlayingMovieUiModel>>(listOf())

    init {
        loadPopularMovies()
        loadNowPlayingMovies()
    }

    private fun loadPopularMovies(){
        viewModelScope.launch {
            val result = repository.getPopularMoviesList()
            when(result){
                is Resource.Success -> {
                    result.data?.let {
                        popularMoviesList.value = it
                    }
                }
                is Resource.Error ->{
                    result.message?.let {
                    }
                }
            }
        }
    }

    private fun loadNowPlayingMovies(){
        viewModelScope.launch {
            val result = repository.getNowPlayingMoviesList()
            when(result){
                is Resource.Success -> {
                    result.data?.let {
                        nowPlayingMoviesList.value = it

                        Log.e("error","viewmodelSuccess")
                    }
                }

                is Resource.Error ->{
                    result.message?.let {
                        Log.e("error","viewmodelError")
                    }
                }
            }
        }
    }


}