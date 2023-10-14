package com.busymodernpeople.feature.community

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
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
            CommunityFreeBoardDetailScreen(
                appState = appState,
                showBottomSheet = showBottomSheet,
                hideBottomSheet = hideBottomSheet
            )
        }

        composable(route = CommunityDestinations.FREE_BOARD_DETAIL) {
            CommunityFreeBoardScreen(
                appState = appState
            )
        }

        composable(route = CommunityDestinations.REPORT_MENU) {
            CommunityReportMenuScreen(
                appState = appState
            )
        }

        composable(route = CommunityDestinations.REPORT_FORM) {
            CommunityReportFormScreen(
                appState = appState
            )
        }
    }
}