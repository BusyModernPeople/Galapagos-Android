package com.busymodernpeople.galapagos

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.busymodernpeople.core.design.ui.component.EnterAnimation
import com.busymodernpeople.feature.join.JoinAgreeScreen
import com.busymodernpeople.feature.login.LoginScreen

object GalapagosDestinations {
    const val LOGIN = "login"
    const val JOIN_AGREE = "joinAgree"
}

@Composable
fun GalapagosNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = GalapagosDestinations.LOGIN
    ) {
        composable(route = GalapagosDestinations.LOGIN) {
            LoginScreen(
                onKakaoLogin = { navController.navigate(GalapagosDestinations.JOIN_AGREE) },
                onNaverLogin = { navController.navigate(GalapagosDestinations.JOIN_AGREE) },
                onGoogleLogin = { navController.navigate(GalapagosDestinations.JOIN_AGREE) }
            )
        }
        composable(route = GalapagosDestinations.JOIN_AGREE) {
            EnterAnimation {
                JoinAgreeScreen(
                    onBack = { navController.navigateUp() }
                )
            }
        }
    }
}