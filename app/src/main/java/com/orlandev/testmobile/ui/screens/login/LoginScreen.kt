package com.orlandev.testmobile.ui.screens.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.orlandev.testmobile.navigation.NavigationRoute

@Composable
fun LoginScreen(
    navController: NavController,
    loginScreenViewModel: LoginScreenViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier) {
            Text(text = "LOGIN")
            Button(onClick = {
                navController.navigate(NavigationRoute.HomeScreenRoute.route)

            }) {
                Text(text = "LOGIN SUCCESS")
            }
        }
    }
}