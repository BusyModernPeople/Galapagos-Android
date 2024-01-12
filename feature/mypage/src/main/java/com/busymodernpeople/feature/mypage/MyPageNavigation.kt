package com.busymodernpeople.feature.mypage

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import androidx.navigation.compose.composable
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.MyPageDestinations
import com.busymodernpeople.core.common.base.SheetContent
import com.busymodernpeople.feature.mypage.component.MyPetProfile

fun NavGraphBuilder.mypageGraph(
    appState: GalapagosAppState,
    showBottomSheet: (SheetContent) -> Unit,
    hideBottomSheet: () -> Unit
) {
    navigation(
        route = MyPageDestinations.ROUTE,
        startDestination = MyPageDestinations.MYPAGE
    ) {
        composable(route = MyPageDestinations.MYPAGE) {
            MyPageScreen(
                appState = appState,
                showBottomSheet = showBottomSheet,
                hideBottomSheet = hideBottomSheet
            )
        }
        composable(route = MyPageDestinations.MYPAGE_EDIT) {
            MyPageEditScreen(
                appState = appState,
                showBottomSheet = showBottomSheet,
                hideBottomSheet = hideBottomSheet
            )
        }
        composable(route = MyPageDestinations.EDIT_SETTING) {
            MyPageSettingScreen(
                appState = appState,
                showBottomSheet = showBottomSheet,
                hideBottomSheet = hideBottomSheet
            )
        }
        composable(route = MyPageDestinations.PET_COLLECTION) {
            MyPagePetScreen(
                appState = appState,
                showBottomSheet = showBottomSheet,
                hideBottomSheet = hideBottomSheet
            )
        }
        composable(route = MyPageDestinations.WRITE_POST) {
            MyPageMyPostScreen(
                appState = appState,
                showBottomSheet = showBottomSheet,
                hideBottomSheet = hideBottomSheet
            )
        }
        composable(route = MyPageDestinations.LIKE_POST) {
            MyPageLikePostScreen(
                appState = appState,
                showBottomSheet = showBottomSheet,
                hideBottomSheet = hideBottomSheet
            )
        }
        composable(route = MyPageDestinations.EVENT_INFORM) {
            MyPageEventScreen(
                appState = appState,
                showBottomSheet = showBottomSheet,
                hideBottomSheet = hideBottomSheet
            )
        }
        composable(route = MyPageDestinations.ASK_MAIL) {
            MyPageAskScreen(
                appState = appState,
                showBottomSheet = showBottomSheet,
                hideBottomSheet = hideBottomSheet
            )
        }
    }
}