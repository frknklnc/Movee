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
import com.example.movee.ui.components.TextItem

@Composable
fun LoginScreen(
    onNavToHomePage: () -> Unit,
    onNavToSignUpPage: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginUiState = viewModel.loginUiState
    val isError = loginUiState.loginError != null
    val context = LocalContext.current

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
            label = { TextItem(text = "Email") },
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

        Button(onClick = { viewModel.loginUser(context) }) {
            Text(text = "Sign In")

        }
        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = "Don't have an Account?")
            Spacer(modifier = Modifier.size(8.dp))
            TextButton(onClick = { onNavToSignUpPage.invoke() }) {
                Text(text = "SignUp")
            }
        }
        if (loginUiState.isLoading) {
            CircularProgressIndicator()
        }

        LaunchedEffect(key1 = viewModel.hasUser) {
            if (viewModel.hasUser) {
                onNavToHomePage.invoke()
            }
        }
    }
}


@Composable
fun SignUpScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavToHomePage: () -> Unit,
    onNavToLoginPage: () -> Unit,
) {
    val loginUiState = viewModel.loginUiState
    val isError = loginUiState.signUpError != null
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Sign Up",
        )

        if (isError) {
            Text(
                text = loginUiState.signUpError ?: "unknown error",
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState.userNameSignUp,
            onValueChange = { viewModel.onUsernameChangeSignup(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                )
            },
            label = {
                Text(text = "Email")
            },
            isError = isError
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState.passwordSignUp,
            onValueChange = { viewModel.onPasswordChangeSignup(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                )
            },
            label = {
                Text(text = "Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState.confirmPasswordSignUp,
            onValueChange = { viewModel.onConfirmPasswordChange(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                )
            },
            label = {
                Text(text = "Confirm Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError
        )

        Button(onClick = { viewModel.createUser(context) }) {
            Text(text = "Sign In")
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = "Already have an Account?")
            Spacer(modifier = Modifier.size(8.dp))
            TextButton(onClick = { onNavToLoginPage.invoke() }) {
                Text(text = "Sign In")
            }

        }

        if (loginUiState.isLoading) {
            CircularProgressIndicator()
        }

        LaunchedEffect(key1 = viewModel.hasUser) {
            if (viewModel.hasUser) {
                onNavToHomePage.invoke()
            }
        }

    }
}