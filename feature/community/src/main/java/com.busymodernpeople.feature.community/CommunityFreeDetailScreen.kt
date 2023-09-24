package com.busymodernpeople.feature.community

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.busymodernpeople.core.common.base.SheetContent
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.core.design.ui.theme.Pretendard

@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun CommunityFreeDetailScreen(
    navController: NavController = rememberNavController(),
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
        TopBar(content = "자유게시판", trailingIcon = R.drawable.ic_animal_category, leadingIconOnClick = { /*TODO*/ })
        CommunityFreeDetailContent()
    }
}

@Composable
private fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_app_bar_prev),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp).clip(CircleShape)
                    .clickable { /* TODO */ }
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.clip(CircleShape).clickable { /* TODO */ }
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_dot_menu),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.clip(CircleShape).clickable { /* TODO */ }
                )
            }
        }

        Text(
            text = "자유게시판",
            style = GalapagosTheme.typography.title4.copy(
                fontWeight = FontWeight.SemiBold,
                color = GalapagosTheme.colors.FontBlack
            )
        )
    }
}

@Preview
@Composable
private fun CommunityFreeDetailContent() {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "제목제목",
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = GalapagosTheme.colors.FontGray1,
                lineHeight = 27.sp
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .width(28.dp)
                    .height(28.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = painterResource(R.drawable.ic_home),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "세포의유미",
                style = GalapagosTheme.typography.body2.copy(fontWeight = FontWeight.SemiBold),
                color = GalapagosTheme.colors.FontGray1
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "9시간 전",
                style = GalapagosTheme.typography.body4.copy(fontWeight = FontWeight.Medium),
                color = GalapagosTheme.colors.FontGray4
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "웅대한 있으며, 열락의 피가 쓸쓸하랴? 트고, 넣는 천고에 생생하며, 운다. 이 아름답고 목숨이 곳이 사막이다. 우는 위하여 커다란 품고 구하지 많이 힘차게 노래하며 풀이 것이다. 이상은 이",
            style = GalapagosTheme.typography.body1.copy(lineHeight = 28.sp),
            color = GalapagosTheme.colors.FontGray1
        )
        Spacer(modifier = Modifier.width(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_eye),
                    contentDescription = null
                )
                Text(
                    text = "22",
                    style = GalapagosTheme.typography.body4.copy(
                        fontWeight = FontWeight.Normal,
                        color = GalapagosTheme.colors.FontGray3
                    )
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_like),
                    contentDescription = null
                )
                Text(
                    text = "22",
                    style = GalapagosTheme.typography.body4.copy(
                        fontWeight = FontWeight.Normal,
                        color = GalapagosTheme.colors.FontGray3
                    )
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_comment),
                    contentDescription = null
                )
                Text(
                    text = "34",
                    style = GalapagosTheme.typography.body4.copy(
                        fontWeight = FontWeight.Normal,
                        color = GalapagosTheme.colors.FontGray3
                    )
                )
            }
        }
    }
}