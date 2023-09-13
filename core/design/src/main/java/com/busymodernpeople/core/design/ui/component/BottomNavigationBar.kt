package com.busymodernpeople.core.design.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        modifier = modifier,
        elevation = 10.dp,
        color = backgroundColor,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    space = 33.dp,
                    alignment = Alignment.CenterHorizontally
                ),
            ) {
                content()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomNavigationItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    @StringRes label: Int,
    selected: Boolean,
    selectedContentColor: Color,
    unselectedContentColor: Color,
    onClick: () -> Unit
) {
    val contentColor = if (selected) selectedContentColor else unselectedContentColor

    Surface(
        modifier = modifier
            .background(color = Color.Transparent)
            .padding(start = 6.dp, end = 6.dp)
            .clickable(onClick = onClick),
        onClick = onClick,
        interactionSource = NoRippleInteractionSource()
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = stringResource(id = label),
                    tint = contentColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(id = label),
                    style = GalapagosTheme.typography.body4,
                    fontWeight = FontWeight.SemiBold,
                    color = contentColor
                )
            }
        }
    }
}