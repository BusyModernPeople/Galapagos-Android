package com.busymodernpeople.feature.mypage.component

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
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

@Preview
@Composable
fun ProfileBasicData(
    nickname: String = "",
    arrowFlag: Boolean = true,
    isEdit: Boolean = true,
    modifier: Modifier = Modifier
) {
    if (isEdit) {
        Box(
            modifier = Modifier.width(116.dp).padding(top = 84.dp, start = 84.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(32.dp),
                color = Color(0xFFFFFFFF),
                elevation = 2.dp
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = null,
                    tint = Color(0xFF111111),
                    modifier = Modifier.padding(7.dp)
                )
            }
        }
    }
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .height(110.dp)
                .width(110.dp),
            color = GalapagosTheme.colors.BgGray3,
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_man),
                contentDescription = null,
                tint = GalapagosTheme.colors.BgGray1,
                modifier = Modifier
                    .fillMaxSize(1f)
                    .padding(27.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = nickname,
                style = GalapagosTheme.typography.title3.copy(fontWeight = FontWeight.Bold)
            )

            if (arrowFlag) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right_black),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}