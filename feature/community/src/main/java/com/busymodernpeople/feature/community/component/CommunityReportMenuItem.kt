package com.busymodernpeople.feature.community.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

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
            painter = painterResource(id = R.drawable.ic_arrow_right_black),
            contentDescription = null,
            tint = GalapagosTheme.colors.FontBlack
        )
    }
}