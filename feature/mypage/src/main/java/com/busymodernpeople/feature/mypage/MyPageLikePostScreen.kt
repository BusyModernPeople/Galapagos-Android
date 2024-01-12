package com.busymodernpeople.feature.mypage


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.SheetContent
import com.busymodernpeople.core.common.base.rememberGalapagosAppState
import com.busymodernpeople.core.design.ui.component.TopBar

@Preview
@Composable
fun MyPageLikePostScreen(
    appState: GalapagosAppState = rememberGalapagosAppState(),
    showBottomSheet: (SheetContent) -> Unit = { },
    hideBottomSheet: () -> Unit = { }
) {
    Column(
        modifier = Modifier
    ) {
        TopBar(leadingIconOnClick = { appState.navigateUp() }, content = "좋아요한 글")
        Spacer(modifier = Modifier.height(24.dp))
    }
}