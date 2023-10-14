package com.busymodernpeople.feature.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.feature.mypage.component.MyPetListMenu

@Preview
@Composable
fun MyPagePetScreen() {
    Column(
        modifier = Modifier
    ) {
        TopBar(
            leadingIconOnClick = { /*TODO*/ },
            content = "내 동물들"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 24.dp)
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(1f),
                shape = RoundedCornerShape(8.dp),
                elevation = 20.dp
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "총 ",
                        style = GalapagosTheme.typography.body1,
                        color = Color(0xFF555555)
                    )
                    Text(
                        text = "0",
                        style = GalapagosTheme.typography.title4.copy(fontWeight = FontWeight.Bold),
                        color = GalapagosTheme.colors.PrimaryGreen
                    )
                    Text(
                        text = " 마리의 동물을 키우고 있어요!",
                        style = GalapagosTheme.typography.body1,
                        color = Color(0xFF555555)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        MyPetListMenu(modifier = Modifier.padding(horizontal = 24.dp))
    }
    PetAddFB()
}

@Preview
@Composable
private fun PetAddFB() {
    Box(
        modifier = Modifier.fillMaxSize(1f).padding(bottom = 50.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            modifier = Modifier.height(54.dp)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(56.dp)),
            shape = RoundedCornerShape(999.dp),
            color = GalapagosTheme.colors.PrimaryGreen
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { /* TODO */ }
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_plus),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "동물 추가하기",
                    style = GalapagosTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                    color = Color.White,
                    modifier = Modifier.offset(y = 1.dp)
                )
            }
        }
    }
}