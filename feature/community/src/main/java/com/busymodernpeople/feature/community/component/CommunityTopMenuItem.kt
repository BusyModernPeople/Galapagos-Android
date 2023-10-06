package com.busymodernpeople.feature.community.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme


@Preview
@Composable
fun CommunityTopMenuItem(
    @DrawableRes icon: Int = R.drawable.ic_community_free,
    content: String = ""
) {
    Column(
        modifier = Modifier.background(color = Color.White).width(80.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                modifier = Modifier.height(32.dp).width(32.dp),
                painter = painterResource(id = icon),
                contentDescription = null
            )
        }

        Text(
            text = content,
            style = GalapagosTheme.typography.body2.copy(fontWeight = FontWeight.SemiBold),
            color = GalapagosTheme.colors.FontBlack
        )
    }
}