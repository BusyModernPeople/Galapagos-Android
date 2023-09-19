package com.busymodernpeople.feature.auth.join

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.core.design.ui.component.EnterAnimation

fun NavGraphBuilder.joinGraph(
    navController: NavHostController
) {
    navigation(
        route = AuthDestinations.Join.ROUTE,
        startDestination = AuthDestinations.Join.AGREE
    ) {
        composable(route = AuthDestinations.Join.AGREE) {
            EnterAnimation {
                JoinAgreeScreen(
                    navController = navController
                )
            }
        }
        composable(route = AuthDestinations.Join.EMAIL) {
            EnterAnimation {
                JoinEmailScreen(
                    navController = navController
                )
            }
        }
        composable(route = AuthDestinations.Join.PASSWORD) {
            EnterAnimation {
                JoinPasswordScreen(
                    navController = navController
                )
            }
        }
        composable(route = AuthDestinations.Join.NICKNAME) {
            EnterAnimation {
                JoinNicknameScreen(
                    navController = navController
                )
            }
        }
        composable(route = AuthDestinations.Join.COMPLETE) {
            EnterAnimation {
                JoinCompleteScreen(
                    navController = navController
                )
            }
        }
    }
}