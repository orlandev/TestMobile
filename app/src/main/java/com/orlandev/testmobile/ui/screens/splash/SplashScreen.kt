package com.orlandev.testmobile.ui.screens.splash

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
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier) {
            Text(text = "SplashScreen")
            Button(onClick = {

                navController.navigate(NavigationRoute.LoginRoute.route)

            }) {
                Text(text = "Login")
            }
            Button(onClick = {

                navController.navigate(NavigationRoute.HomeScreenRoute.route)

            }) {
                Text(text = "Home")
            }
        }
    }
}