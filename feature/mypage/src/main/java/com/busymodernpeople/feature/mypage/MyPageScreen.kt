package com.busymodernpeople.feature.mypage

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.feature.mypage.component.MenuListItem
import com.busymodernpeople.feature.mypage.component.ProfileBasicData

@Preview
@Composable
fun MyPageScreen(
//    appState: GalapagosAppState = rememberGalapagosAppState(),
//    showBottomSheet: (SheetContent) -> Unit = { },
//    hideBottomSheet: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        MyPageTopBar()
        Spacer(modifier = Modifier.height(240.dp))
        ProfileMenu()
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(1f)
                .padding(horizontal = 24.dp)
                .background(color = GalapagosTheme.colors.BgGray3)
        ) {}
        Spacer(modifier = Modifier.height(24.dp))
        MenuList()
    }
    Box(
        modifier = Modifier.height(296.dp).fillMaxWidth(1f).padding(top = 56.dp),
        contentAlignment = Alignment.Center
    ) {
        ProfileBasicData()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MyPageTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 7.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "마이페이지",
            style = GalapagosTheme.typography.title1.copy(fontWeight = FontWeight.Bold)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = 10.dp,
                alignment = Alignment.End
            )
        ) {
            CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_setting),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileMenu(
    // appState: GalapagosAppState
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .shadow(20.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 27.dp,
                    vertical = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyPageTopMenuItem(
                icon = R.drawable.ic_pet_color,
                content = "내 동물들"
                // onClick = { appState.navigate(CommunityDestinations.FREE_BOARD) }
            )
            MyPageTopMenuItem(R.drawable.ic_pencil_color, "작성한 글")
            MyPageTopMenuItem(R.drawable.ic_heart_color, "좋아요한 글")
        }
    }
}

@Preview
@Composable
fun MyPageTopMenuItem(
    @DrawableRes icon: Int = R.drawable.ic_pet_color,
    content: String = "내 동물들",
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .width(80.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(
            onClick = { onClick() }
        ) {
            Icon(
                modifier = Modifier
                    .height(32.dp)
                    .width(32.dp),
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = content,
            style = GalapagosTheme.typography.body2.copy(fontWeight = FontWeight.SemiBold),
            color = GalapagosTheme.colors.FontBlack
        )
    }
}

@Preview
@Composable
fun MenuList() {
    Column(
        modifier = Modifier
    ) {
        MenuListItem("이벤트 및 공지사항")
        Spacer(modifier = Modifier.height(8.dp))
        MenuListItem("문의하기")
        Spacer(modifier = Modifier.height(8.dp))
        MenuListItem("고객센터")
        Spacer(modifier = Modifier.height(8.dp))
    }
}