package com.app.coins.custom.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.app.coins.custom.navigation.graphs.cryptoGraph
import com.app.coins.custom.navigation.graphs.newsGraph
import com.app.coins.custom.navigation.graphs.nftGraph
import com.app.coins.utils.ScreenRoutes

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.CRYPTO_HOST_ROUTE,
        modifier = modifier,

        ) {
        cryptoGraph(navController, shouldBottomBarVisible = {})

        nftGraph(navController, shouldBottomBarVisible = {})

        newsGraph(navController, shouldBottomBarVisible = {})


    }
}