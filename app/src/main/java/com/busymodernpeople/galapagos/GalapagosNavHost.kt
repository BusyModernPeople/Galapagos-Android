package com.busymodernpeople.galapagos

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.busymodernpeople.core.design.ui.component.BottomNavigationBar
import com.busymodernpeople.core.design.ui.component.BottomNavigationItem
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.feature.join.joinGraph
import com.busymodernpeople.feature.join.navigateToJoinGraph
import com.busymodernpeople.feature.login.LOGIN_GRAPH
import com.busymodernpeople.feature.login.loginGraph

@Composable
fun GalapagosNavHost() {
    val appState = rememberGalapagosAppState()
    val navController = appState.navController

    Scaffold(
        backgroundColor = GalapagosTheme.colors.FontWhite,
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                GalapagosBottomNavigationBar(
                    destinations = appState.topLevelDestination,
                    currentDestination = appState.currentDestination,
                    onNavigateToDestination = appState::navigateToTopLevelDestination
                )
            }
        }
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = LOGIN_GRAPH
        ) {
            loginGraph(
                navigateToJoinGraph = { navController.navigateToJoinGraph() }
            )
            joinGraph(
                navController = navController
            )
            composable("homeGraph") {

            }
        }
    }
}

@Composable
private fun GalapagosBottomNavigationBar(
    modifier: Modifier = Modifier,
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    selectedContentColor: Color = GalapagosTheme.colors.PrimaryGreen,
    unselectedContentColor: Color = GalapagosTheme.colors.FontGray4
) {
    BottomNavigationBar(
        modifier = modifier
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination?.route == destination.route

            BottomNavigationItem(
                icon = destination.icon,
                label = destination.label,
                selected = selected,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
                onClick = { onNavigateToDestination(destination) }
            )
        }
    }
}