package com.example.movee.data.repository

import com.example.movee.data.local.SharedPrefHelper
import com.example.movee.data.remote.model.login.LoginRequestModel
import com.example.movee.data.remote.model.login.SessionRequestModel
import com.example.movee.data.remote.service.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val sharedPrefHelper: SharedPrefHelper,
    private val loginService: LoginService,
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
}

enum class LoginState {
    LOGGED_IN,
    LOGGED_OUT
}