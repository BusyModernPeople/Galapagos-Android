package com.busymodernpeople.feature.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState

fun NavGraphBuilder.loginGraph(
    appState: GalapagosAppState
) {
    navigation(
        route = AuthDestinations.Login.ROUTE,
        startDestination = AuthDestinations.Login.LOGIN
    ) {
        composable(route = AuthDestinations.Login.LOGIN) { entry ->
            val backStackEntry = rememberNavControllerBackStackEntry(
                entry = entry,
                navController = appState.navController,
                graph = AuthDestinations.Login.ROUTE
            )
            val viewModel: LoginViewModel = hiltViewModel(backStackEntry)

            LoginScreen(
                appState = appState,
                viewModel = viewModel
            )
        }

        composable(route = AuthDestinations.Login.EMAIL_LOGIN) { entry ->
            val backStackEntry = rememberNavControllerBackStackEntry(
                entry = entry,
                navController = appState.navController,
                graph = AuthDestinations.Login.ROUTE
            )

            EmailLoginScreen(
                appState = appState,
                viewModel = hiltViewModel(backStackEntry)
            )
        }
    }
}

@Composable
fun rememberNavControllerBackStackEntry(
    entry: NavBackStackEntry,
    navController: NavController,
    graph: String,
) = remember(entry) {
    navController.getBackStackEntry(graph)
}