package com.busymodernpeople.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.SheetContent
import com.busymodernpeople.core.common.base.rememberGalapagosAppState
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.feature.mypage.component.MenuListItem

@Preview
@Composable
fun MyPageSettingScreen(
    appState: GalapagosAppState = rememberGalapagosAppState(),
    showBottomSheet: (SheetContent) -> Unit = { },
    hideBottomSheet: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        TopBar(leadingIconOnClick = { /* TODO */ }, content = "설정")
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "계정",
            style = GalapagosTheme.typography.body3,
            color = GalapagosTheme.colors.FontGray2,
            modifier = Modifier.padding(start = 36.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        MenuListItem("로그아웃")
        Spacer(modifier = Modifier.height(8.dp))
        MenuListItem("회원탈퇴")
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(1f)
                .padding(horizontal = 24.dp)
                .background(color = GalapagosTheme.colors.BgGray3)
        ) {}
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "앱",
            style = GalapagosTheme.typography.body3,
            color = GalapagosTheme.colors.FontGray2,
            modifier = Modifier.padding(start = 36.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        MenuListItem("이용약관")
        Spacer(modifier = Modifier.height(8.dp))
        MenuListItem("개인정보처리방침")
        Spacer(modifier = Modifier.height(8.dp))
        MenuListItem("버전정보")
    }
}