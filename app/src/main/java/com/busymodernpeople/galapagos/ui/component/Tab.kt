package com.busymodernpeople.galapagos.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.busymodernpeople.galapagos.ui.theme.GalapagosTheme
import com.busymodernpeople.galapagos.ui.theme.PrimaryGreen
import com.busymodernpeople.galapagos.ui.theme.Typography

@Composable
private fun TabIndicator(
    indicatorWidth: Dp,
    indicatorOffset: Dp,
    indicatorColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(indicatorWidth)
            .offset(indicatorOffset)
            .clip(CircleShape)
            .background(indicatorColor)
    )
}

@Composable
private fun TabItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    tabWidth: Dp,
    text: String
) {
    val textColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            Color.White
        } else {
            Color.Black
        },
        animationSpec = tween(easing = LinearEasing)
    )

    Text(
        modifier = Modifier
            .clip(CircleShape)
            .clickable { onClick() }
            .width(tabWidth)
            .padding(horizontal = 15.dp, vertical = 7.dp),
        style = Typography.body2,
        fontWeight = FontWeight.Bold,
        text = text,
        color = textColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun GlassmorphicTab(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedItemIndex: Int,
    indicatorColor: Color = PrimaryGreen,
    tabWidth: Dp = 67.dp,
    onClick: (index: Int) -> Unit
) {
    val indicatorOffset: Dp by animateDpAsState(
        targetValue = tabWidth * selectedItemIndex,
        animationSpec = tween(easing = LinearEasing)
    )

    Surface(
        modifier = modifier
            .height(intrinsicSize = IntrinsicSize.Min)
            .border(
                width = 1.dp,
                color = Color(0x25FFFFFF),
                shape = CircleShape
            ),
        shape = CircleShape,
        color = Color.White.copy(alpha = 0.5f),
        elevation = 12.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(
                    Brush.radialGradient(
                        listOf(
                            Color.White.copy(0.4f),
                            Color.White.copy(0.1f),
                        ),
                        radius = 100f,
                        center = Offset.Infinite
                    )
                )
        )

        Box(modifier = modifier.padding(all = 8.dp)) {
            TabIndicator(
                indicatorWidth = tabWidth,
                indicatorOffset = indicatorOffset,
                indicatorColor = indicatorColor
            )

            Row(
                modifier = Modifier.clip(CircleShape),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, text ->
                    val isSelected = index == selectedItemIndex

                    TabItem(
                        isSelected = isSelected,
                        onClick = { onClick(index) },
                        tabWidth = tabWidth,
                        text = text
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTab() {
    GalapagosTheme {
        GlassmorphicTab(
            items = listOf("리스트", "캘린더"),
            selectedItemIndex = 0,
            onClick = { }
        )
    }
}