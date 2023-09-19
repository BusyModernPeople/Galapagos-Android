package com.busymodernpeople.feature.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.feature.auth.join.joinGraph
import com.busymodernpeople.feature.auth.login.loginGraph

fun NavGraphBuilder.authGraph(
    navController: NavHostController
) {
    navigation(
        route = AuthDestinations.ROUTE,
        startDestination = AuthDestinations.Login.ROUTE
    ) {
        loginGraph(
            navController = navController
        )

        joinGraph(
            navController = navController
        )
    }
}