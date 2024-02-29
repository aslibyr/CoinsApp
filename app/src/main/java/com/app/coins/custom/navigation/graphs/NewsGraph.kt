package com.app.coins.custom.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.app.coins.ui.news.NewsScreen
import com.app.coins.ui.news.newsdetail.NewsDetailScreen
import com.app.coins.utils.ScreenRoutes

fun NavGraphBuilder.newsGraph(
    navController: NavController,
    shouldBottomBarVisible: (Boolean) -> Unit
) {
    navigation(
        startDestination = ScreenRoutes.NEWS_SCREEN_ROUTE,
        route = ScreenRoutes.NEWS_HOST_ROUTE
    ) {
        composable(
            route = ScreenRoutes.NEWS_SCREEN_ROUTE
        ) {
            NewsScreen(newsClicked = { route ->
                navController.navigate(route)
            })

        }
        composable(
            route = ScreenRoutes.NEWS_DETAIL_SCREEN_ROUTE,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            NewsDetailScreen(onBackClick = {
                navController.popBackStack()
            })
        }
    }
}