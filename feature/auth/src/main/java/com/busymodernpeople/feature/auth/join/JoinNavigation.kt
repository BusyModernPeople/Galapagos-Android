package com.busymodernpeople.feature.auth.join

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.design.ui.component.EnterAnimation
import com.busymodernpeople.feature.auth.login.rememberNavControllerBackStackEntry

fun NavGraphBuilder.joinGraph(
    appState: GalapagosAppState
) {
    navigation(
        route = "${AuthDestinations.Join.ROUTE}?socialType={socialType}&email={email}",
        startDestination = "${AuthDestinations.Join.AGREE}?socialType={socialType}&email={email}"
    ) {
        composable(
            route = "${AuthDestinations.Join.AGREE}?socialType={socialType}&email={email}",
            arguments = listOf(
                navArgument("socialType") {
                    type = NavType.StringType
                    defaultValue = "EMAIL"
                },
                navArgument("email") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = ""
                }
            )
        ) { entry ->
            val backStackEntry = rememberNavControllerBackStackEntry(
                entry = entry,
                navController = appState.navController,
                graph = "${AuthDestinations.Join.ROUTE}?socialType={socialType}&email={email}"
            )
            EnterAnimation {
                JoinAgreeScreen(
                    appState = appState,
                    viewModel = hiltViewModel(backStackEntry)
                )
            }
        }
        composable(route = AuthDestinations.Join.EMAIL) { entry ->
            val backStackEntry = rememberNavControllerBackStackEntry(
                entry = entry,
                navController = appState.navController,
                graph = "${AuthDestinations.Join.ROUTE}?socialType={socialType}&email={email}"
            )
            EnterAnimation {
                JoinEmailScreen(
                    appState = appState,
                    viewModel = hiltViewModel(backStackEntry)
                )
            }
        }
        composable(route = AuthDestinations.Join.PASSWORD) { entry ->
            val backStackEntry = rememberNavControllerBackStackEntry(
                entry = entry,
                navController = appState.navController,
                graph = "${AuthDestinations.Join.ROUTE}?socialType={socialType}&email={email}"
            )
            EnterAnimation {
                JoinPasswordScreen(
                    appState = appState,
                    viewModel = hiltViewModel(backStackEntry)
                )
            }
        }
        composable(route = AuthDestinations.Join.NICKNAME) { entry ->
            val backStackEntry = rememberNavControllerBackStackEntry(
                entry = entry,
                navController = appState.navController,
                graph = "${AuthDestinations.Join.ROUTE}?socialType={socialType}&email={email}"
            )
            EnterAnimation {
                JoinNicknameScreen(
                    appState = appState,
                    viewModel = hiltViewModel(backStackEntry)
                )
            }
        }
        composable(route = AuthDestinations.Join.COMPLETE) { entry ->
            val backStackEntry = rememberNavControllerBackStackEntry(
                entry = entry,
                navController = appState.navController,
                graph = "${AuthDestinations.Join.ROUTE}?socialType={socialType}&email={email}"
            )
            EnterAnimation {
                JoinCompleteScreen(
                    appState = appState,
                    viewModel = hiltViewModel(backStackEntry)
                )
            }
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