package com.busymodernpeople.feature.auth.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val LOGIN_GRAPH = "loginGraph"

const val LOGIN = "login"

fun NavController.navigateToLoginGraph(navOptions: NavOptions? = null) {
    this.navigate(LOGIN_GRAPH, navOptions)
}

fun NavGraphBuilder.loginGraph(
    navigateToJoinGraph: () -> Unit = { },
    navigateToHome: () -> Unit = { }
) {
    navigation(
        route = LOGIN_GRAPH,
        startDestination = LOGIN
    ) {
        composable(route = LOGIN) {
            LoginScreen(
                onKakaoLogin = { navigateToJoinGraph() },
                onNaverLogin = { navigateToJoinGraph() },
                onGoogleLogin = { navigateToHome() }
            )
        }
    }
}