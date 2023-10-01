package com.busymodernpeople.feature.community.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

@Preview
@Composable
fun CommunityCommentItem(
    @DrawableRes profileImg: Int = R.drawable.ic_profile_empty,
    nickname: String = "",
    content: String = "실례지만, 어떤 아이를 키우고 계시는지 여쭈어봐도 될까요?",
    likeCount: Int = 36,
    commentCount: Int = 14,
    createdTime: String = "58분 전"
) {
    Box(
        modifier = Modifier.padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                        modifier = Modifier
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
                    Image(
                        painterResource(id = R.drawable.ic_dot_menu_horizontal),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = content,
                    style = GalapagosTheme.typography.body2,
                    color = GalapagosTheme.colors.FontGray1
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(

                ) {
                    Text(
                        text = "답글쓰기",
                        style = GalapagosTheme.typography.body4,
                        color = GalapagosTheme.colors.BgGray3
                    )
                    Text(
                        text = "∙",
                        style = GalapagosTheme.typography.body4,
                        color = GalapagosTheme.colors.BgGray3
                    )
                    Text(
                        text = "답글",
                        style = GalapagosTheme.typography.body4,
                        color = GalapagosTheme.colors.BgGray3
                    )
                    Text(
                        text = commentCount.toString(),
                        style = GalapagosTheme.typography.body4,
                        color = GalapagosTheme.colors.BgGray3
                    )
                }
            }
        }
    }
}