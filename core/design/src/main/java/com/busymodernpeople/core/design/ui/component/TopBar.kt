package com.busymodernpeople.core.design.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

@Composable
fun TopBar(
    content: String = "",
    @DrawableRes leadingIcon: Int? = R.drawable.ic_app_bar_prev,
    @DrawableRes trailingIcon: Int? = null,
    leadingIconOnClick: () -> Unit,
    trailingIconOnClick: () -> Unit = {},
) {
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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (leadingIcon != null) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_app_bar_prev),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp).clip(CircleShape)
                        .clickable { leadingIconOnClick() }
                )
            }

            if (trailingIcon != null) {
                Icon(
                    painter = painterResource(id = trailingIcon),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.clip(CircleShape).clickable { trailingIconOnClick() }
                )
            }
        }

        Text(
            text = content,
            style = GalapagosTheme.typography.title4.copy(
                fontWeight = FontWeight.SemiBold,
                color = GalapagosTheme.colors.FontBlack
            )
        )
    }
}