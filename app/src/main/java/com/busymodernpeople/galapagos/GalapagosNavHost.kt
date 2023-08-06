package com.busymodernpeople.galapagos

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.busymodernpeople.feature.join.joinGraph
import com.busymodernpeople.feature.join.navigateToJoinGraph
import com.busymodernpeople.feature.login.LOGIN_GRAPH
import com.busymodernpeople.feature.login.loginGraph

@Composable
fun GalapagosNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LOGIN_GRAPH
    ) {
        loginGraph(
            navController = navController,
            navigateToJoinGraph = {
                navController.navigateToJoinGraph()
            }
        )
        joinGraph(
            navController = navController
        )
    }
}