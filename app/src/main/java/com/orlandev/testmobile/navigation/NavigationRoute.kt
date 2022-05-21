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

    object DetailScreenRoute : NavigationRoute(route = "DetailScreenRoute")

    object ProfileScreenRoute : NavigationRoute(
        route = "ProfileScreenRoute",
        resourceDrawableId = R.drawable.ic_round_person_pin_24,
        resourceStringId = R.string.profile
    )

    object UserProductsScreenRoute : NavigationRoute(
        route = "UserProductsScreenRoute",
        resourceDrawableId = R.drawable.ic_baseline_shopping_cart_24,
        resourceStringId = R.string.user_products,
    )

    object SettingsScreenRoute : NavigationRoute(
        route = "SettingsScreenRoute",
        resourceDrawableId = R.drawable.ic_baseline_settings_24,
        resourceStringId = R.string.settings,
    )


}
