package com.busymodernpeople.feature.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.feature.auth.join.joinGraph
import com.busymodernpeople.feature.auth.login.loginGraph
import com.busymodernpeople.feature.auth.resetpassword.ResetPasswordGraph

fun NavGraphBuilder.authGraph(
    appState: GalapagosAppState
) {
    navigation(
        route = AuthDestinations.ROUTE,
        startDestination = AuthDestinations.Login.ROUTE
    ) {
        loginGraph(
            appState = appState
        )

        joinGraph(
            appState = appState
        )

        ResetPasswordGraph(
            appState = appState
        )
    }
}