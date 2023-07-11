package com.busymodernpeople.galapagos.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun TabItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit,
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

    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = Color.Transparent,
        onClick = { onClick() },
        interactionSource = NoRippleInteractionSource()
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 7.dp),
            style = Typography.body2,
            fontWeight = FontWeight.Bold,
            text = text,
            color = textColor
        )
    }
}

@Composable
fun GlassmorphicTab(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedItemIndex: Int,
    indicatorColor: Color = PrimaryGreen,
    onClick: (index: Int) -> Unit
) {
    val density = LocalDensity.current
    val sizeList = remember { mutableStateMapOf<Int, Dp>() }

    val indicatorOffset: Dp by animateDpAsState(
        targetValue = sizeList
            .values
            .take(selectedItemIndex)
            .sumOf { it },
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
                indicatorWidth = sizeList[selectedItemIndex] ?: 0.dp,
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
                        modifier = Modifier.onSizeChanged {
                            sizeList[index] = with(density) { it.width.toDp() }
                        },
                        isSelected = isSelected,
                        onClick = { onClick(index) },
                        text = text
                    )
                }
            }
        }
    }
}

inline fun <T> Iterable<T>.sumOf(selector: (T) -> Dp): Dp {
    var sum: Dp = 0.dp
    for (element in this) {
        sum += selector(element)
    }
    return sum
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