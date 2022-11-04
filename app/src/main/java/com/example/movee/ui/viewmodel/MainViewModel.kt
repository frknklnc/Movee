package com.example.movee.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.repository.MoviesRepository
import com.example.movee.domain.model.PopularMovieUiModel
import com.example.movee.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MoviesRepository) :ViewModel() {

    val popularMoviesList = MutableStateFlow<List<PopularMovieUiModel>>(listOf())

    init {
        loadMovies()
    }

    private fun loadMovies(){
        viewModelScope.launch {
            val result = repository.getPopularMoviesList()
            when(result){
                is Resource.Success -> {
                    result.data?.let {
                        popularMoviesList.value = it

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