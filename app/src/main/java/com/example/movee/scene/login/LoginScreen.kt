package com.example.movee.scene.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movee.data.repository.LoginState
import com.example.movee.ui.components.TextItem

@Composable
fun LoginScreen(
    onNavToHomePage: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginUiState = viewModel.loginUiState
    val isError = loginUiState.loginError != null

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        TextItem(text = "Login", fontSize = 32.sp)

        if (isError) {
            TextItem(text = loginUiState.loginError ?: "error")
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState.userName,
            onValueChange = { viewModel.onUserNameChange(it) },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            },
            label = { TextItem(text = "Username") },
            isError = isError
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null)
            },
            label = { TextItem(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError
        )

        Button(onClick = { viewModel.loginUser() }) {
            Text(text = "Sign In")

        }
        Spacer(modifier = Modifier.size(16.dp))

        if (loginUiState.isLoading) {
            CircularProgressIndicator()
        }

        LaunchedEffect(key1 = viewModel.hasUser) {
            if (viewModel.hasUser == LoginState.LOGGED_IN) {
                onNavToHomePage.invoke()
            }
        }
    }
}