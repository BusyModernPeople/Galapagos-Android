package com.busymodernpeople.feature.community

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.busymodernpeople.core.common.base.CommunityDestinations
import com.busymodernpeople.core.common.base.SheetContent

fun NavGraphBuilder.communityGraph(
    navController: NavHostController,
    showBottomSheet: (SheetContent) -> Unit,
    hideBottomSheet: () -> Unit
) {
    navigation(
        route = CommunityDestinations.ROUTE,
        startDestination = CommunityDestinations.COMMUNITY
    ) {
        composable(route = CommunityDestinations.COMMUNITY) {
            CommunityScreen(
                navController = navController,
                showBottomSheet = showBottomSheet,
                hideBottomSheet = hideBottomSheet
            )
        }
    }
}