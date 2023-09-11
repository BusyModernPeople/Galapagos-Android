package com.busymodernpeople.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.common.base.SheetContent
import com.busymodernpeople.core.design.ui.component.ButtonSize
import com.busymodernpeople.core.design.ui.component.ContentTab
import com.busymodernpeople.core.design.ui.component.GButton
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun HomeScreen(
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
        HomeTopBar()
        MainAnimalContent()
        CommunityContent()
    }
}

@Preview
@Composable
fun HomeTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "logo",
                style = GalapagosTheme.typography.title2.copy(fontWeight = FontWeight.Bold)
            )

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = com.busymodernpeople.core.design.R.drawable.ic_alarm),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun MainAnimalContent() {
    Spacer(modifier = Modifier.height(16.dp))

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 10.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.no_main_animal_message),
                    style = GalapagosTheme.typography.body2.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = GalapagosTheme.colors.FontGray1
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                GButton(
                    buttonSize = ButtonSize.Height52,
                    shape = RoundedCornerShape(100.dp),
                    leadingIcon = com.busymodernpeople.core.design.R.drawable.ic_plus,
                    content = stringResource(id = R.string.add_animal),
                    onClick = { /*TODO*/ }
                )
            }
        }
    }
}

@Preview
@Composable
fun CommunityContent() {
    Spacer(modifier = Modifier.height(40.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "커뮤니티 최신글",
            style = GalapagosTheme.typography.title2.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(18.dp))

        ContentTab(
            items = listOf(
                "자유게시판",
                "QnA",
                "공지사항"
            ),
            selectedItemIndex = 0,
            onClick = { /*TODO*/ }
        )

        Spacer(modifier = Modifier.height(18.dp))

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            repeat(5) {
                CommunityItem()
            }
        }

        Spacer(modifier = Modifier.height(36.dp))
    }

}

@Preview
@Composable
fun CommunityItem(
    title: String = "눈 오는 날 산책 다녀왔어요.",
    content: String = "이런저런 이야기",
    likeCount: Int = 36,
    commentCount: Int = 14,
    createdTime: String = "58분 전"
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = title,
                    style = GalapagosTheme.typography.body1.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = GalapagosTheme.colors.FontGray1
                    )
                )
                Text(
                    text = content,
                    style = GalapagosTheme.typography.body2.copy(
                        fontWeight = FontWeight.Normal,
                        color = GalapagosTheme.colors.FontGray2
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = com.busymodernpeople.core.design.R.drawable.ic_like),
                            contentDescription = null
                        )
                        Text(
                            text = "$likeCount",
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
                            painter = painterResource(id = com.busymodernpeople.core.design.R.drawable.ic_comment),
                            contentDescription = null
                        )
                        Text(
                            text = "$commentCount",
                            style = GalapagosTheme.typography.body4.copy(
                                fontWeight = FontWeight.Normal,
                                color = GalapagosTheme.colors.FontGray3
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = createdTime,
                        style = GalapagosTheme.typography.body4.copy(
                            fontWeight = FontWeight.Normal,
                            color = GalapagosTheme.colors.FontGray4
                        )
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(90.dp)
                    .background(
                        color = GalapagosTheme.colors.BgGray3,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }
    }
}