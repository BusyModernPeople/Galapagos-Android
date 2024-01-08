package com.busymodernpeople.feature.diary

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

@Preview
@Composable
fun AddDiaryScreen() {
    var content by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .systemBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        AddDiaryTopBar()

        Spacer(Modifier.height(10.dp))

        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = "4월 13일",
            style = GalapagosTheme.typography.title2.copy(
                color = GalapagosTheme.colors.FontBlack
            )
        )

        Spacer(Modifier.height(22.dp))

        ImageSelector()

        Spacer(Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
        ) {
            BasicTextField(
                modifier = Modifier.fillMaxWidth().heightIn(min = 200.dp),
                value = content,
                onValueChange = {
                    content = it
                }
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = GalapagosTheme.colors.PrimaryGreen)) {
                        append("${content.length}")
                    }
                    append("/200")
                },
                style = GalapagosTheme.typography.body4.copy(
                    color = Color(0xFF96979B)
                ),
                modifier = Modifier.align(Alignment.End)
            )
        }

        Spacer(modifier = Modifier
            .fillMaxHeight()
            .weight(1f))

        BottomMenu()
    }
}

@Composable
private fun AddDiaryTopBar() {
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_app_bar_prev),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .clickable { }
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    shape = RoundedCornerShape(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = GalapagosTheme.colors.FontWhite,
                        contentColor = GalapagosTheme.colors.BgGray1
                    ),
                    border = BorderStroke(1.dp, GalapagosTheme.colors.BgGray1),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                    onClick = { }
                ) {
                    Text(
                        text = "임시저장",
                        style = GalapagosTheme.typography.body2.copy(
                            color = GalapagosTheme.colors.FontGray1
                        )
                    )
                }
                Button(
                    shape = RoundedCornerShape(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = GalapagosTheme.colors.FontGray1,
                        contentColor = GalapagosTheme.colors.FontWhite
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                    onClick = { }
                ) {
                    Text(
                        text = "등록하기",
                        style = GalapagosTheme.typography.body2.copy(
                            color = GalapagosTheme.colors.FontWhite
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ImageSelector() {
    Box(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.14f)
                .background(
                    shape = RoundedCornerShape(12.dp),
                    color = GalapagosTheme.colors.BgGray3
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    shape = RoundedCornerShape(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = GalapagosTheme.colors.FontWhite,
                        contentColor = GalapagosTheme.colors.FontBlack
                    ),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp),
                    onClick = { }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            painter = painterResource(id = R.drawable.ic_add_image),
                            tint = Color.Unspecified,
                            contentDescription = "IC_ADD_IMAGE"
                        )

                        Text(
                            text = "사진 선택하기",
                            style = GalapagosTheme.typography.title5.copy(
                                color = GalapagosTheme.colors.FontGray1
                            )
                        )
                    }
                }

                Text(
                    text = "동영상 포함\n최대 5장까지 업로드 가능합니다.",
                    style = GalapagosTheme.typography.body3.copy(
                        color = GalapagosTheme.colors.FontGray3
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun BottomMenu() {
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(Color(0xFFFAFAD)))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Icon(
            modifier = Modifier.size(28.dp),
            painter = painterResource(id = R.drawable.ic_add_image),
            tint = Color.Unspecified,
            contentDescription = "IC_ADD_IMAGE"
        )

        Box(
            modifier = Modifier.size(28.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_category),
                tint = Color.Unspecified,
                contentDescription = "IC_ADD_CATEGORY"
            )
        }
    }
}