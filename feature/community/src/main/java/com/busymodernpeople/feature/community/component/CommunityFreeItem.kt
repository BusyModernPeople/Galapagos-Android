package com.busymodernpeople.feature.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme


@Preview
@Composable
fun CommunityFreeItem(
    title: String = "눈 오는 날 산책 다녀왔어요.",
    content: String = "이런저런 이야기",
    likeCount: Int = 36,
    commentCount: Int = 14,
    createdTime: String = "58분 전"
) {
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
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
                            painter = painterResource(id = R.drawable.ic_like),
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
                            painter = painterResource(id = R.drawable.ic_comment),
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