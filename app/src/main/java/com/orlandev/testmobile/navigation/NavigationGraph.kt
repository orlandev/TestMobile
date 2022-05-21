package com.orlandev.testmobile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.orlandev.testmobile.ui.screens.home.HomeScreen
import com.orlandev.testmobile.ui.screens.login.LoginScreen
import com.orlandev.testmobile.ui.screens.product_detail.DetailScreen
import com.orlandev.testmobile.ui.screens.splash.SplashScreen


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        AnimatedNavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            navController = navController,
            startDestination = NavigationRoute.SplashRoute.route
        )
        {

            composable(
                route = NavigationRoute.SplashRoute.route,
            )
            {
                SplashScreen(navController = navController)
            }

            composable(
                route = NavigationRoute.LoginRoute.route,
            )
            {
                LoginScreen(navController)
            }

            composable(
                route = NavigationRoute.HomeScreenRoute.route,
            )
            {
                HomeScreen(
                    navController = navController
                )
            }
            composable(
                route = NavigationRoute.DetailScreenRoute.route,
            )
            {
                DetailScreen(
                    navController = navController
                )
            }
        }
    }
}


