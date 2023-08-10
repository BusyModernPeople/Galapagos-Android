package com.busymodernpeople.galapagos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions

@Composable
fun rememberGalapagosAppState(
    navController: NavHostController = rememberNavController()
) : GalapagosAppState {
    return remember(navController) { GalapagosAppState(navController) }
}

@Stable
class GalapagosAppState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestination = TopLevelDestination.values().toList()

    val shouldShowBottomBar: Boolean
        @Composable get() = topLevelDestination
            .map { it.route }
            .contains(currentDestination?.route)

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }

        navController.navigate(topLevelDestination.route, navOptions)
    }
}

