package com.busymodernpeople.feature.mypage

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

@Preview
@Composable
fun LogoutDialog() {
    Surface(
        modifier = Modifier,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp).padding(top = 45.dp, bottom = 35.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "로그아웃 하시겠어요?",
                style = GalapagosTheme.typography.body1,
                color = GalapagosTheme.colors.FontBlack
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier
            ) {
                Surface(
                    modifier = Modifier.width(110.dp).border(shape = RoundedCornerShape(100.dp), width = 1.dp, color = GalapagosTheme.colors.BgGray1),
                    shape = RoundedCornerShape(100.dp)
                ) {
                    Box(
                        modifier = Modifier.padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "취소",
                            style = GalapagosTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                            color = GalapagosTheme.colors.FontGray1
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Surface(
                    modifier = Modifier.width(110.dp),
                    shape = RoundedCornerShape(100.dp),
                    color = GalapagosTheme.colors.IconBlack
                ) {
                    Box(
                        modifier = Modifier.padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "로그아웃",
                            style = GalapagosTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}