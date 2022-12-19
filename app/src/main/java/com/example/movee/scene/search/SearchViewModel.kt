package com.example.movee.scene.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    var query = MutableStateFlow("")
        private set


    private fun makeSearch() {
        viewModelScope.launch {
            query.debounce(500).filter { str ->
                if (str.isEmpty() || str.length < 3) {
                    _uiState.update {
                        it.removeList()
                    }
                    return@filter false
                } else {
                    return@filter true
                }
            }.distinctUntilChanged().flatMapLatest { str ->
                _uiState.update {
                    it.showLoading()
                }
                repository.getSearch(str)
            }.catch { error ->
                _uiState.update {
                    it.showError(error.message ?: "Something went wrong")
                }

            }.collect { list ->
                _uiState.update {
                    it.copy(
                        data = list,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun handleQueryChange(newQuery: String) {
        query.value = newQuery
        makeSearch()
    }
}