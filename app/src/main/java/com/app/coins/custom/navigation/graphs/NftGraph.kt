package com.app.coins.custom.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.app.coins.ui.nft.NftCollectionScreen
import com.app.coins.ui.nft.collectiondetail.CollectionDetailScreen
import com.app.coins.utils.ScreenRoutes

fun NavGraphBuilder.nftGraph(
    navController: NavController,
    shouldBottomBarVisible: (Boolean) -> Unit
) {
    navigation(
        startDestination = ScreenRoutes.NFT_SCREEN_ROUTE,
        route = ScreenRoutes.NFT_HOST_ROUTE
    ) {
        composable(
            route = ScreenRoutes.NFT_SCREEN_ROUTE
        ) {
            NftCollectionScreen(nftClicked = { route ->
                navController.navigate(route)
            })
        }
        composable(
            route = ScreenRoutes.COLLECTION_DETAIL_SCREEN_ROUTE,
            arguments = listOf(
                navArgument("collectionAddress") {
                    type = NavType.StringType
                }
            )
        ) {
            CollectionDetailScreen(onBackClick = {
                navController.popBackStack()
            })
        }

    }
}