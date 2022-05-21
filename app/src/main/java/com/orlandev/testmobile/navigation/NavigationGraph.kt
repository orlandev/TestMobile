package com.orlandev.testmobile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.orlandev.testmobile.R
import com.orlandev.testmobile.ui.screens.ProductViewModel
import com.orlandev.testmobile.ui.screens.dev_screen.DevScreen
import com.orlandev.testmobile.ui.screens.home.HomeScreen
import com.orlandev.testmobile.ui.screens.login.LoginScreen
import com.orlandev.testmobile.ui.screens.product_detail.DetailScreen
import com.orlandev.testmobile.ui.screens.splash.SplashScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController, homeScreenViewModel: ProductViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        drawerContent = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.stock),
                        contentDescription = stringResource(
                            id = R.string.app_name
                        ), contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.app_name),
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontSize = 19.sp)
                    )
                }

                DrawerItem(
                    painter = painterResource(id = NavigationRoute.HomeScreenRoute.resourceDrawableId!!),
                    stringResource(id = NavigationRoute.HomeScreenRoute.resourceStringId!!)
                ) {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate(NavigationRoute.HomeScreenRoute.route)
                }

                Divider()

                DrawerItem(
                    painter = painterResource(id = NavigationRoute.ProfileScreenRoute.resourceDrawableId!!),
                    stringResource(id = NavigationRoute.ProfileScreenRoute.resourceStringId!!)
                ) {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate(NavigationRoute.ProfileScreenRoute.route)
                }

                DrawerItem(
                    painter = painterResource(id = NavigationRoute.UserProductsScreenRoute.resourceDrawableId!!),
                    stringResource(id = NavigationRoute.UserProductsScreenRoute.resourceStringId!!)
                ) {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate(NavigationRoute.UserProductsScreenRoute.route)
                }

                DrawerItem(
                    painter = painterResource(id = NavigationRoute.SettingsScreenRoute.resourceDrawableId!!),
                    stringResource(id = NavigationRoute.SettingsScreenRoute.resourceStringId!!)
                ) {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate(NavigationRoute.SettingsScreenRoute.route)
                }

                Divider()

                DrawerItem(
                    painter = painterResource(id = R.drawable.ic_logout),
                    stringResource(id = R.string.close_session)
                ) {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    //TODO( CLOSE USER SESSION )
                }
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

            composable(
                route = NavigationRoute.ProfileScreenRoute.route,
            )
            {
                DevScreen(NavigationRoute.ProfileScreenRoute.route)
            }

            composable(
                route = NavigationRoute.UserProductsScreenRoute.route,
            )
            {
                DevScreen(NavigationRoute.UserProductsScreenRoute.route)
            }

            composable(
                route = NavigationRoute.SettingsScreenRoute
                    .route,
            )
            {
                DevScreen(NavigationRoute.SettingsScreenRoute
                    .route)
            }


        }
    }
}

@Composable
fun DrawerItem(painter: Painter, title: String, onItemClicked: () -> Unit) {
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(4.dp)
            .clickable {
                onItemClicked()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Icon(
                painter = painter,
                contentDescription = title
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = title)
        }
    }
}


