package com.busymodernpeople.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.HomeDestinations
import com.busymodernpeople.core.common.base.SheetContent

fun NavGraphBuilder.homeGraph(
    appState: GalapagosAppState,
    showBottomSheet: (SheetContent) -> Unit,
    hideBottomSheet: () -> Unit
) {
    navigation(
        route = HomeDestinations.ROUTE,
        startDestination = HomeDestinations.HOME
    ) {
        composable(route = HomeDestinations.HOME) {
            HomeScreen(
                appState = appState,
                showBottomSheet = showBottomSheet,
                hideBottomSheet = hideBottomSheet
            )
        }
    }
}