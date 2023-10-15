package com.busymodernpeople.feature.community.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

/**
 * @param isComment 댓글(true)인지 답글(false)인지 구분
 */
@Preview
@Composable
fun CommunityCommentItem(
    @DrawableRes profileImg: Int = R.drawable.ic_profile_empty,
    nickname: String = "",
    content: String = "실례지만, 어떤 아이를 키우고 계시는지 여쭈어봐도 될까요?",
    likeCount: Int = 36,
    commentCount: Int = 14,
    createdTime: String = "58분 전",
    isComment: Boolean = true
) {
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (!isComment) {
                Spacer(modifier = Modifier.width(33.dp))
            }
            Image(
                modifier = Modifier
                    .width(28.dp)
                    .height(28.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = painterResource(profileImg),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = nickname,
                            style = GalapagosTheme.typography.body2.copy(fontWeight = FontWeight.SemiBold),
                            color = GalapagosTheme.colors.FontGray1
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = createdTime,
                            style = GalapagosTheme.typography.body4.copy(fontWeight = FontWeight.Medium),
                            color = GalapagosTheme.colors.FontGray4
                        )
                    }

                    Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dot_menu_vertical),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable { isExpanded = !isExpanded }
                        )

                        DropdownMenu(
                            expanded = isExpanded,
                            onDismissRequest = { isExpanded = false }
                        ) {
                            DropdownMenuItem(
                                onClick = {  }
                            ) {
                                Text(
                                    text = "신고하기",
                                    style = GalapagosTheme.typography.body1.copy(
                                        color = GalapagosTheme.colors.FontGray1,
                                        fontWeight = FontWeight.Normal
                                    )
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = content,
                    style = GalapagosTheme.typography.body2,
                    color = GalapagosTheme.colors.FontGray1
                )
                Spacer(modifier = Modifier.height(10.dp))

                if (isComment) {
                    Row(
                        modifier = Modifier
                    ) {
                        Text(
                            text = "답글쓰기",
                            style = GalapagosTheme.typography.body4,
                            color = GalapagosTheme.colors.FontGray3
                        )
                        Text(
                            text = "∙",
                            style = GalapagosTheme.typography.body4,
                            color = GalapagosTheme.colors.FontGray3
                        )
                        Text(
                            text = "답글",
                            style = GalapagosTheme.typography.body4,
                            color = GalapagosTheme.colors.FontGray3
                        )
                        Text(
                            text = commentCount.toString(),
                            style = GalapagosTheme.typography.body4,
                            color = GalapagosTheme.colors.FontGray3
                        )
                    }
                }
            }
        }
    }
}