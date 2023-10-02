package com.busymodernpeople.feature.auth.findpassword

import FindPasswordEmailScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState

fun NavGraphBuilder.findPasswordGraph(
    appState: GalapagosAppState
) {
    navigation(
        route = AuthDestinations.FindPassword.ROUTE,
        startDestination = AuthDestinations.FindPassword.EMAIL
    ) {
        composable(route = AuthDestinations.FindPassword.EMAIL) {
            FindPasswordEmailScreen(
                appState = appState
            )
        }
        composable(route = AuthDestinations.FindPassword.RESET_PASSWORD) {
            FindPasswordResetScreen(
                appState = appState
            )
        }
        composable(route = AuthDestinations.FindPassword.COMPLETE) {
            FindPasswordCompleteScreen(
                appState = appState
            )
        }
    }
}