package com.app.coins.custom.bottom_bar

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.coins.utils.ScreenRoutes
import com.app.coins.utils.theme.FontType
import com.app.coins.utils.theme.darkTextColor
import com.app.coins.utils.theme.light
import com.app.coins.utils.theme.primaryBackgroundColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


@Composable
fun BottomBar(
    navController: NavController,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navigation: (String) -> Unit = { route ->
        if (route == ScreenRoutes.BACK_PRESSED) {
            navController.popBackStack()
        } else {
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        }
    }

    val items = listOf(
        BottomNavigationItem(
            title = "Crypto",
            icon = Icons.Outlined.MonetizationOn,
            route = ScreenRoutes.CRYPTO_HOST_ROUTE
        ),
        BottomNavigationItem(
            title = "NFT's",
            icon = Icons.Filled.Image,
            route = ScreenRoutes.NFT_HOST_ROUTE
        ),
        BottomNavigationItem(
            title = "More",
            icon = Icons.Filled.MoreHoriz,
            route = ScreenRoutes.MORE_SCREEN_ROUTE
        )

    )

    Column(modifier = Modifier.fillMaxWidth()) {
        NavigationBar(
            modifier = Modifier.graphicsLayer {
                clip = true
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                shadowElevation = 5f
            },
            containerColor = primaryBackgroundColor
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                    onClick = {
                        navigation(item.route)
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 10.sp,
                            fontFamily = FontType.quicksandBold
                        )
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = item.icon,
                            contentDescription = item.title,
                        )
                    },
                    interactionSource = NoRippleInteractionSource,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = light,
                        selectedTextColor = light,
                        unselectedIconColor = darkTextColor,
                        unselectedTextColor = darkTextColor,
                        indicatorColor = primaryBackgroundColor
                    )
                )
            }
        }
    }
}

private object NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}