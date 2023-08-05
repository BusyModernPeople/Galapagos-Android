package com.busymodernpeople.core.design.ui.join

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.R

@Composable
fun JoinTopBar(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 13.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_app_bar_prev),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.clip(CircleShape).clickable { onClick() }
        )
    }
}