package com.example.movee.scene.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.repository.LoginState
import com.example.movee.data.repository.ProfileRepository
import com.example.movee.uimodels.account.AccountUiModel
import com.example.movee.uimodels.favourite.FavouriteMovieUiModel
import com.example.movee.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) :
    ViewModel() {

    val accountDetail = MutableStateFlow<AccountUiModel?>(null)
    val favouriteMovie = MutableStateFlow<List<FavouriteMovieUiModel>>(listOf())

    val hasUser: LoginState
        get() = repository.getLoginState()


    fun loadAccountDetails() {
        viewModelScope.launch {

            val result = repository.getAccountDetails()
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        accountDetail.value = it
                        loadFavouriteMovie(it.accountId)
                    }
                }
                is Resource.Error -> {
                    result.message?.let {
                    }
                }
            }
        }
    }

    private fun loadFavouriteMovie(accountId:Int) {
        viewModelScope.launch {

            val result = repository.getFavouriteMovie(accountId)
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        favouriteMovie.value = it
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