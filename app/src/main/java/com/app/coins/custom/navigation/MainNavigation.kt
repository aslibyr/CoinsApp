package com.app.coins.custom.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.coins.ui.crypto.HomeScreen
import com.app.coins.ui.more.MoreScreen
import com.app.coins.ui.nft.NftScreen
import com.app.coins.utils.ScreenRoutes

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.HOME_SCREEN_ROUTE,
        modifier = Modifier,

        ) {
        composable(
            route = ScreenRoutes.HOME_SCREEN_ROUTE
        ) {
            HomeScreen()

        }
        composable(
            route = ScreenRoutes.NFT_SCREEN_ROUTE
        ) {
            NftScreen()

        }
        composable(
            route = ScreenRoutes.MORE_SCREEN_ROUTE
        ) {
            MoreScreen()

        }
    }
}