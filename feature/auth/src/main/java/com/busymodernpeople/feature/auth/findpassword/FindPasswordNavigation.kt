package com.busymodernpeople.feature.auth.findpassword

import FindPasswordEmailScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.busymodernpeople.core.common.base.AuthDestinations

fun NavGraphBuilder.findPasswordGraph(
    navController: NavHostController
) {
    navigation(
        route = AuthDestinations.FindPassword.ROUTE,
        startDestination = AuthDestinations.FindPassword.EMAIL
    ) {
        composable(route = AuthDestinations.FindPassword.EMAIL) {
            FindPasswordEmailScreen(
                navController = navController
            )
        }
        composable(route = AuthDestinations.FindPassword.RESET_PASSWORD) {
            FindPasswordResetScreen(
                navController = navController
            )
        }
        composable(route = AuthDestinations.FindPassword.COMPLETE) {
            FindPasswordCompleteScreen(
                navController = navController
            )
        }
    }
}