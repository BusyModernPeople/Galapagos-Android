package com.busymodernpeople.feature.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.SheetContent
import com.busymodernpeople.core.common.base.rememberGalapagosAppState
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.core.design.ui.theme.LocalColors
import com.busymodernpeople.core.design.ui.theme.LocalTypography
import com.busymodernpeople.core.design.ui.theme.Pretendard
import com.busymodernpeople.feature.community.component.CommunityCommentItem

@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun CommunityFreeBoardDetailScreen(
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
        TopBar()
        CommunityFreeDetailContent()

        // TODO : 댓글이 있을 경우
        Spacer(modifier = Modifier.height(78.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(16.dp)
                .background(color = GalapagosTheme.colors.BgGray3)
        ) { }
        CommunityCommentItem()
        CommunityCommentItem(isComment = false)
        BottomCommentField()
    }
    HeartFB()
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
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .clickable { /* TODO */ }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { /* TODO */ }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_dot_menu_vertical),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { /* TODO */ }
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
                painter = painterResource(R.drawable.ic_profile_empty),
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
        Spacer(modifier = Modifier.height(10.dp))
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

@Preview
@Composable
private fun BottomCommentField() {
    var comment by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .width(28.dp)
                    .height(28.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = painterResource(R.drawable.ic_profile_empty),
                contentDescription = null
            )

            Surface(
                modifier = Modifier.clip(RoundedCornerShape(36.dp)),
                color = GalapagosTheme.colors.PrimaryGreen
            ) {
                Box(
                    modifier = Modifier
                        .padding(6.dp)
                        .height(24.dp)
                        .width(24.dp)
                ) {
                    IconButton(
                        onClick = { /* TODO */ }
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 10.dp)
                .height(36.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommentTextField(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 38.dp, end = 44.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },
                value = comment,
                onValueChange = { comment = it },
                placeholderText = "댓글을 입력해주세요"
            )
        }
    }
}

@Composable
fun CommentTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String = "",
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: KeyboardActions? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val localColors = LocalColors.current
    val localTypography = LocalTypography.current

    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused = it.isFocused
            },
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        singleLine = true,
        textStyle = localTypography.title4.copy(color = localColors.FontGray1),
        keyboardOptions = keyboardOptions ?: KeyboardOptions(),
        keyboardActions = keyboardActions ?: KeyboardActions(),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.height(27.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.fillMaxSize(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            modifier = Modifier
                                .offset(y = 1.dp)
                                .fillMaxSize(1f),
                            text = placeholderText,
                            style = GalapagosTheme.typography.body1.copy(
                                color = localColors.BgGray1
                            )
                        )
                    }
                    innerTextField()
                }
            }
        },
        visualTransformation = visualTransformation
    )
}

@Preview
@Composable
private fun HeartFB() {
    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(end = 24.dp, bottom = 80.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Surface(
            modifier = Modifier.size(56.dp).shadow(elevation = 4.dp, shape = RoundedCornerShape(56.dp)),
            shape = RoundedCornerShape(56.dp),
            color = GalapagosTheme.colors.PrimaryGreen
        ) {
            IconButton(
                onClick = { /* TODO */ }
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_heart),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}