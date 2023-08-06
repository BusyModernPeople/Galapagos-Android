package com.busymodernpeople.feature.join

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.busymodernpeople.core.design.ui.component.EnterAnimation

const val JOIN_GRAPH = "joinGraph"

const val JOIN_AGREE = "joinAgree"
const val JOIN_EMAIL = "joinEmail"
const val JOIN_PASSWORD = "joinPassword"
const val JOIN_NICKNAME = "joinNickname"
const val JOIN_COMPLETE = "joinComplete"

fun NavController.navigateToJoinGraph(navOptions: NavOptions? = null) {
    this.navigate(JOIN_GRAPH, navOptions)
}

fun NavGraphBuilder.joinGraph(navController: NavHostController) {
    navigation(
        route = JOIN_GRAPH,
        startDestination = JOIN_AGREE
    ) {
        composable(route = JOIN_AGREE) {
            EnterAnimation {
                JoinAgreeScreen(
                    onBack = { navController.navigateUp() },
                    onConfirm = { navController.navigate(JOIN_EMAIL) }
                )
            }
        }
        composable(route = JOIN_EMAIL) {
            EnterAnimation {
                JoinEmailScreen(
                    onBack = { navController.navigateUp() },
                    onConfirm = { navController.navigate(JOIN_PASSWORD) }
                )
            }
        }
        composable(route = JOIN_PASSWORD) {
            EnterAnimation {
                JoinPasswordScreen(
                    onBack = { navController.navigateUp() },
                    onConfirm = { navController.navigate(JOIN_NICKNAME) }
                )
            }
        }
        composable(route = JOIN_NICKNAME) {
            EnterAnimation {
                JoinNicknameScreen(
                    onBack = { navController.navigateUp() },
                    onConfirm = { navController.navigate(JOIN_COMPLETE)}
                )
            }
        }
        composable(route = JOIN_COMPLETE) {
            EnterAnimation {
                JoinCompleteScreen(
                    onBack = { navController.navigateUp() },
                    onRegisterPet = { },
                    onGuestMode = { }
                )
            }
        }
    }
}