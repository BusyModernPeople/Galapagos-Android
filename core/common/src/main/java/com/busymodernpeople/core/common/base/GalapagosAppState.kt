package com.busymodernpeople.core.common.base

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberGalapagosAppState(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    scope: CoroutineScope = rememberCoroutineScope()
) : GalapagosAppState {
    return remember(navController) {
        GalapagosAppState(navController, scaffoldState, scope)
    }
}

@Stable
class GalapagosAppState(
    val navController: NavHostController,
    val scaffoldState: ScaffoldState,
    val scope: CoroutineScope
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestination = TopLevelDestination.values().toList()

    val shouldShowBottomBar: Boolean
        @Composable get() = topLevelDestination
            .map { it.route }
            .contains(currentDestination?.route)

    fun showSnackBar(message: String) = scope.launch {
        scaffoldState.snackbarHostState.showSnackbar(message)
    }

    fun navigate(destination: String, builder: NavOptionsBuilder.() -> Unit = {}) {
        navController.navigate(destination, builder)
    }

    fun navigate(
        destination: String,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        navController.navigate(destination, navOptions, navigatorExtras)
    }


    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }

        navController.navigate(topLevelDestination.route, navOptions)
    }
}

