package com.app.coins.custom.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.app.coins.ui.crypto.HomeScreen
import com.app.coins.ui.crypto.detail.DetailScreen
import com.app.coins.utils.ScreenRoutes

fun NavGraphBuilder.cryptoGraph(
    navController: NavController,
    shouldBottomBarVisible: (Boolean) -> Unit
) {
    navigation(
        startDestination = ScreenRoutes.CRYPTO_SCREEN_ROUTE,
        route = ScreenRoutes.CRYPTO_HOST_ROUTE
    ) {
        composable(
            route = ScreenRoutes.CRYPTO_SCREEN_ROUTE
        ) {
            HomeScreen(cryptoClicked = { route ->
                navController.navigate(route)
            })

        }
        composable(
            route = ScreenRoutes.CRYPTO_DETAIL_SCREEN_ROUTE,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen(onBackClick = {
                navController.popBackStack()
            })
        }
    }
}