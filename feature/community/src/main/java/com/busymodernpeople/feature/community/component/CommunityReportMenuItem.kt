package com.busymodernpeople.feature.community.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.core.design.R

@Composable
fun CommunityReportMenuItem(
    modifier: Modifier,
    content: String = ""
) {
    Row(
        modifier = Modifier.fillMaxWidth(1f),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = content,
            color = GalapagosTheme.colors.FontGray1,
            style = GalapagosTheme.typography.body1
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right_2),
            contentDescription = null,
            tint = GalapagosTheme.colors.FontBlack
        )
    }
}