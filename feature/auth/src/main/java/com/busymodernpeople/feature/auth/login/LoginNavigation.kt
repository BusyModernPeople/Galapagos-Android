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

fun NavGraphBuilder.loginGraph(
    navController: NavController
) {
    navigation(
        route = AuthDestinations.Login.ROUTE,
        startDestination = AuthDestinations.Login.LOGIN
    ) {
        composable(route = AuthDestinations.Login.LOGIN) { entry ->
            val backStackEntry = rememberNavControllerBackStackEntry(
                entry = entry,
                navController = navController,
                graph = AuthDestinations.Login.ROUTE
            )
            val viewModel: LoginViewModel = hiltViewModel(backStackEntry)

            LoginScreen(
                navController = navController,
                effectFlow = viewModel.effect,
                viewModel = viewModel
            )
        }

        composable(route = AuthDestinations.Login.EMAIL_LOGIN) { entry ->
            val backStackEntry = rememberNavControllerBackStackEntry(
                entry = entry,
                navController = navController,
                graph = AuthDestinations.Login.ROUTE
            )

            EmailLoginScreen(
                navController = navController,
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