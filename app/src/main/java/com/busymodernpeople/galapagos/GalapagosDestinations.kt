package com.busymodernpeople.galapagos

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.busymodernpeople.feature.login.LoginScreen

object GalapagosDestinations {
    const val LOGIN = "login"
}

@Composable
fun GalapagosNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = GalapagosDestinations.LOGIN
    ) {
        composable(route = GalapagosDestinations.LOGIN) {
            LoginScreen()
        }
    }
}