package com.busymodernpeople.feature.community

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.busymodernpeople.core.common.base.CommunityDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.SheetContent

fun NavGraphBuilder.communityGraph(
    appState: GalapagosAppState,
    showBottomSheet: (SheetContent) -> Unit,
    hideBottomSheet: () -> Unit
) {
    navigation(
        route = CommunityDestinations.ROUTE,
        startDestination = CommunityDestinations.COMMUNITY
    ) {
        composable(route = CommunityDestinations.COMMUNITY) {
            CommunityScreen(
                appState = appState,
                showBottomSheet = showBottomSheet,
                hideBottomSheet = hideBottomSheet
            )
        }

        composable(route = CommunityDestinations.FREE_BOARD) {
            CommunityFreeBoardScreen(
                appState = appState
            )
        }

        composable(route = CommunityDestinations.POST_DETAIL) {
            CommunityPostDetailScreen(
                appState = appState,
                showBottomSheet = showBottomSheet,
                hideBottomSheet = hideBottomSheet
            )
        }

        composable(route = CommunityDestinations.REPORT_MENU) {
            CommunityReportMenuScreen(
                appState = appState
            )
        }

        composable(
            route = "${CommunityDestinations.REPORT_FORM}?title={title}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { entry ->
            CommunityReportFormScreen(
                appState = appState,
                title = entry.arguments?.getString("title") ?: ""
            )
        }
    }
}