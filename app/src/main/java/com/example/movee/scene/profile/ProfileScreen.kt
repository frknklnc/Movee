package com.example.movee.scene.profile

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movee.data.repository.LoginState
import com.example.movee.navigation.Route
import com.example.movee.scene.login.LoginViewModel

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()) {

    val accountDetail = viewModel.accountDetail.collectAsState()

    LaunchedEffect(key1 = viewModel.hasUser) {
        if (viewModel.hasUser == LoginState.LOGGED_IN) {
            viewModel.loadAccountDetails()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        accountDetail.value?.let { detail->
            Text(text = "Welcome\n" + detail.username)
            Text(text = detail.country)
        }

        Button(onClick = {navController.navigate(Route.MapScreen.route)}) {
            Text(text = "Map")
            
        }
    }
}