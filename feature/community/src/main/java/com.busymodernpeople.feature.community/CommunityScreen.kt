package com.busymodernpeople.feature.community

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
import com.busymodernpeople.core.common.base.CommunityDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.SheetContent
import com.busymodernpeople.core.common.base.rememberGalapagosAppState
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.component.ContentTab
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.feature.community.component.CommunityHomeItem
import com.busymodernpeople.feature.community.component.CommunityTopMenuItem

@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun CommunityScreen(
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
        CommunityTopBar()
        Spacer(modifier = Modifier.height(20.dp))
        CommunityTopMenu(appState)
        Spacer(modifier = Modifier.height(40.dp))
        CommunityContent()
    }
    writeFB()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CommunityTopBar() {
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
            text = "커뮤니티",
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
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_alarm),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
private fun CommunityTopMenu(
    appState: GalapagosAppState
) {
    Surface(
        modifier = Modifier.padding(horizontal = 24.dp).shadow(20.dp),
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
            CommunityTopMenuItem(
                icon = R.drawable.ic_community_free,
                content = "자유게시판",
                onClick = { appState.navigate(CommunityDestinations.FREE_BOARD) }
            )
            CommunityTopMenuItem(R.drawable.ic_community_qna, "QnA")
            CommunityTopMenuItem(R.drawable.ic_community_inform, "공지사항")
        }
    }
}

@Preview
@Composable
private fun CommunityContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "커뮤니티 최신글",
            style = GalapagosTheme.typography.title2.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(8.dp))

        ContentTab(
            items = listOf(
                "자유게시판",
                "QnA",
                "공지사항"
            ),
            selectedItemIndex = 0,
            onClick = { /*TODO*/ }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            repeat(5) {
                CommunityHomeItem()
            }
            ShowMoreButton()
        }

        Spacer(modifier = Modifier.height(36.dp))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
private fun ShowMoreButton(
    onClick: () -> Unit = { }
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 5.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            Text(
                text = "더보기",
                style = GalapagosTheme.typography.body2.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = GalapagosTheme.colors.FontGray2
                )
            )

            Icon(
                painterResource(id = R.drawable.ic_show_more),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun writeFB() {
    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(end = 24.dp, bottom = 16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Surface(
            modifier = Modifier
                .size(56.dp)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(56.dp)),
            shape = RoundedCornerShape(56.dp),
            color = GalapagosTheme.colors.PrimaryGreen
        ) {
            IconButton(
                modifier = Modifier,
                onClick = { /* TODO */ }
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_write),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}