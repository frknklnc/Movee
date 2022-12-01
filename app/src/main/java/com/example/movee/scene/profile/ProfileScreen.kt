package com.example.movee.scene.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movee.navigation.Route
import com.example.movee.scene.login.LoginViewModel
import com.example.movee.ui.components.TextItem

@Composable
fun ProfileScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.navigate(Route.LoginScreen.route)
            viewModel.logOut()
        }
        ) {
            Text(text = "Logout")
        }
        viewModel.currentUser?.let {
            TextItem(text = it)
        }
    }
}