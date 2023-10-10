package com.busymodernpeople.feature.diary

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.busymodernpeople.core.common.base.DiaryDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.SheetContent

fun NavGraphBuilder.diaryGraph(
    appState: GalapagosAppState,
    showBottomSheet: (SheetContent) -> Unit,
    hideBottomSheet: () -> Unit
) {
    navigation(
        route = DiaryDestinations.ROUTE,
        startDestination = DiaryDestinations.DIARY
    ) {
        composable(route = DiaryDestinations.DIARY) {
            DiaryScreen(
                appState = appState
            )
        }
    }
}