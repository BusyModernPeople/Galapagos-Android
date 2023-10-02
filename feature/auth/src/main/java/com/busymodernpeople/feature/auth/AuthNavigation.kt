package com.busymodernpeople.feature.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.feature.auth.findpassword.findPasswordGraph
import com.busymodernpeople.feature.auth.join.joinGraph
import com.busymodernpeople.feature.auth.login.loginGraph

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

        findPasswordGraph(
            appState = appState
        )
    }
}