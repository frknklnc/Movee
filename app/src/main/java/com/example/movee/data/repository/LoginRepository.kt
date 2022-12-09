package com.example.movee.data.repository

import com.example.movee.data.local.SharedPrefHelper
import com.example.movee.data.remote.model.login.LoginRequestModel
import com.example.movee.data.remote.model.login.SessionRequestModel
import com.example.movee.data.remote.service.ApiService
import com.example.movee.data.remote.service.LoginService
import com.example.movee.uimodels.account.AccountUiModel
import com.example.movee.uimodels.movie.MovieDetailUiModel
import com.example.movee.util.Resource
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val sharedPrefHelper: SharedPrefHelper,
    private val loginService: LoginService,
    private val service: ApiService
) {

    fun getLoginState() =  if (sharedPrefHelper.getSessionId().isNullOrBlank())
    LoginState.LOGGED_OUT else LoginState.LOGGED_IN


    suspend fun login(username: String, password: String) {

        val requestTokenResponse = loginService.createRequestToken()

        val loginRequestTokenResponse =
            loginService.createRequestTokenWithLogin(
                LoginRequestModel(
                    username = username,
                    password = password,
                    requestToken = requestTokenResponse.requestToken
                )
            )

        val sessionResponse = loginService.createSession(
            SessionRequestModel(
                loginRequestTokenResponse.requestToken
            )
        )

        sharedPrefHelper.saveSessionId(sessionResponse.sessionId)

    }

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
}

enum class LoginState {
    LOGGED_IN,
    LOGGED_OUT
}