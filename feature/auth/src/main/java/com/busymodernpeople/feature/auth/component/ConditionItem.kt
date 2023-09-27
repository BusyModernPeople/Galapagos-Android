package com.busymodernpeople.feature.auth.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

@Composable
fun ConditionItem(
    modifier: Modifier = Modifier,
    isSatisfied: Boolean,
    @StringRes content: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_check),
            contentDescription = null,
            tint = if (isSatisfied) {
                GalapagosTheme.colors.PrimaryGreen
            } else {
                GalapagosTheme.colors.BgGray1
            }
        )
        Text(
            text = stringResource(id = content),
            style = GalapagosTheme.typography.body2,
            color = if (isSatisfied) {
                GalapagosTheme.colors.PrimaryGreen
            } else {
                GalapagosTheme.colors.BgGray1
            }
        )
    }
}