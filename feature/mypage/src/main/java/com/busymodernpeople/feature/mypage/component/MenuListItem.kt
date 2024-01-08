package com.busymodernpeople.feature.mypage.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

@Preview
@Composable
fun MenuListItem(
    content: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
        ) {
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = content,
                style = GalapagosTheme.typography.title4.copy(fontWeight = FontWeight.Bold)
            )
        }
        Row(
            modifier = Modifier
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right_black),
                tint = GalapagosTheme.colors.FontBlack,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}