package com.example.movee.scene.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.local.SharedPrefHelper
import com.example.movee.data.repository.LoginRepository
import com.example.movee.data.repository.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val helper:SharedPrefHelper
) : ViewModel() {


    val hasUser: LoginState
        get() = repository.getLoginState()

    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun onUserNameChange(username: String) {
        loginUiState = loginUiState.copy(userName = username)
    }

    fun onPasswordChange(password: String) {
        loginUiState = loginUiState.copy(password = password)
    }

    private fun validateLoginForm() =
        loginUiState.userName.isNotBlank() && loginUiState.password.isNotBlank()


    fun loginUser() = viewModelScope.launch {
        try {
            if (!validateLoginForm()) {
                throw IllegalArgumentException("Email and password can not be empty")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            loginUiState = loginUiState.copy(loginError = null)
            repository.login(
                loginUiState.userName,
                loginUiState.password
            )

            Log.e("sessionId", helper.getSessionId().toString())


        } catch (e: Exception) {
            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()

        } finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }
}