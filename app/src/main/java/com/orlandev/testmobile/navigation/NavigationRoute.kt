package com.orlandev.testmobile.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.orlandev.testmobile.R


sealed class NavigationRoute(
    val route: String,
    @DrawableRes val resourceDrawableId: Int? = null,
    @StringRes val resourceStringId: Int? = null,
    argument: String? = null
) {

    object SplashRoute : NavigationRoute(route = "SplashRoute")
    object LoginRoute : NavigationRoute(route = "LoginRoute")

    object HomeScreenRoute :
        NavigationRoute(
            route = "HomeScreen",
            resourceDrawableId = R.drawable.ic_round_home_24,
            resourceStringId = R.string.home
        )

}