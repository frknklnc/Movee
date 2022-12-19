package com.example.movee.data.repository

import com.example.movee.data.local.SharedPrefHelper
import com.example.movee.data.remote.service.AccountService
import com.example.movee.uimodels.account.AccountUiModel
import com.example.movee.uimodels.favourite.FavouriteMovieUiModel
import com.example.movee.util.Resource
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val service: AccountService,
    private val sharedPrefHelper: SharedPrefHelper
) {

    fun getLoginState() = if (sharedPrefHelper.getSessionId().isNullOrBlank())
        LoginState.LOGGED_OUT else LoginState.LOGGED_IN

    suspend fun getAccountDetails(): Resource<AccountUiModel> {
        return try {
            val response = service.getAccountDetail()
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    return@let Resource.Success(res.toUiModel())
                } ?: Resource.Error("Error.")

            } else {
                Resource.Error("Error.")
            }
        } catch (e: Exception) {

            Resource.Error("${e.message}")
        }
    }

    suspend fun getFavouriteMovie(accountId: Int): Resource<List<FavouriteMovieUiModel>> {
        return try {
            val response = service.getFavouriteMovie(accountId = accountId)
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    return@let Resource.Success(res.results.map { it.toUiModel() })
                } ?: Resource.Error("Error.")

            } else {
                Resource.Error("Error.")
            }
        } catch (e: Exception) {

            Resource.Error("${e.message}")
        }
    }
}
