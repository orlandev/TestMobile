package com.orlandev.testmobile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.orlandev.testmobile.ui.screens.ProductViewModel
import com.orlandev.testmobile.ui.screens.home.HomeScreen
import com.orlandev.testmobile.ui.screens.login.LoginScreen
import com.orlandev.testmobile.ui.screens.product_detail.DetailScreen
import com.orlandev.testmobile.ui.screens.splash.SplashScreen


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController, homeScreenViewModel: ProductViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        drawerContent = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "DRAWER AREA")
            }
        }
    ) {
        AnimatedNavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            navController = navController,
            startDestination = NavigationRoute.HomeScreenRoute.route
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
                    navController = navController,
                    productViewModel = homeScreenViewModel
                )
            }
            composable(
                route = NavigationRoute.DetailScreenRoute.route,
            )
            {
                DetailScreen(
                    navController = navController,
                    productViewModel = homeScreenViewModel
                )
            }
        }
    }
}


