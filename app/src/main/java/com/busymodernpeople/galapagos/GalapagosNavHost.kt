package com.busymodernpeople.galapagos

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.busymodernpeople.core.common.base.SheetContent
import com.busymodernpeople.core.design.ui.component.BottomNavigationBar
import com.busymodernpeople.core.design.ui.component.BottomNavigationItem
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.feature.auth.join.joinGraph
import com.busymodernpeople.feature.auth.join.navigateToJoinGraph
import com.busymodernpeople.feature.auth.login.LOGIN_GRAPH
import com.busymodernpeople.feature.auth.login.loginGraph
import com.busymodernpeople.feature.home.HomeScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GalapagosNavHost() {
    val appState = rememberGalapagosAppState()
    val navController = appState.navController

    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    var bottomSheetContent: SheetContent? by remember { mutableStateOf(null) }
    val scope = rememberCoroutineScope()

    val showBottomSheet: (SheetContent) -> Unit = { content: SheetContent ->
        bottomSheetContent = content
        scope.launch { bottomSheetState.show() }
    }
    val hideBottomSheet: () -> Unit = {
        scope.launch {
            bottomSheetState.hide()
            bottomSheetContent = null
        }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            bottomSheetContent?.invoke(this)
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(20.dp)
    ) {
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
                    navigateToJoinGraph = { navController.navigateToJoinGraph() },
                    navigateToHome = { navController.navigate("homeGraph")}
                )
                joinGraph(
                    navController = navController
                )
                composable("homeGraph") {
                    HomeScreen(
                        showBottomSheet = showBottomSheet,
                        hideBottomSheet = hideBottomSheet
                    )
                }
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