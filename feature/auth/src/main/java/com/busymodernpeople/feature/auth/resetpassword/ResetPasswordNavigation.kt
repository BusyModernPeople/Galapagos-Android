package com.busymodernpeople.feature.auth.resetpassword

import FindPasswordEmailScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState

fun NavGraphBuilder.ResetPasswordGraph(
    appState: GalapagosAppState
) {
    navigation(
        route = AuthDestinations.ResetPassword.ROUTE,
        startDestination = AuthDestinations.ResetPassword.EMAIL
    ) {
        composable(route = AuthDestinations.ResetPassword.EMAIL) {
            FindPasswordEmailScreen(
                appState = appState
            )
        }
        composable(route = AuthDestinations.ResetPassword.COMPLETE) {
            ResetPasswordCompleteScreen(
                appState = appState
            )
        }
    }
}