package com.busymodernpeople.galapagos.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import com.busymodernpeople.galapagos.ui.theme.*

@Composable
private fun ScrollableTabIndicator(
    indicatorWidth: Dp,
    indicatorOffset: Dp,
    indicatorColor: Color
) {
    Spacer(
        modifier = Modifier
            .width(indicatorWidth)
            .height(3.dp)
            .offset(indicatorOffset)
            .background(
                color = indicatorColor,
                shape = RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp)
            )
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ScrollableTabItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit,
    text: String
) {
    val textColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            FontBlack
        } else {
            FontGray4
        },
        animationSpec = tween(easing = LinearEasing)
    )

    val fontWeight = if (isSelected) {
        FontWeight.Bold
    } else {
        FontWeight.SemiBold
    }

    Surface(
        modifier = modifier,
        color = Color.Transparent,
        onClick = { onClick() },
        interactionSource = NoRippleInteractionSource()
    ) {
        Text(
            modifier = Modifier.padding(vertical = 13.dp),
            text = text,
            style = Typography.body2,
            fontWeight = fontWeight,
            color = textColor
        )
    }
}

@Composable
fun ScrollableTabRow(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedItemIndex: Int,
    indicatorColor: Color = Color.Black,
    onClick: (index: Int) -> Unit
) {
    val density = LocalDensity.current
    val sizeList = remember { mutableStateMapOf<Int, Dp>() }

    val indicatorOffset: Dp by animateDpAsState(
        targetValue = sizeList
            .values
            .take(selectedItemIndex)
            .sumOf { it }
                + 24.dp + 16.dp * selectedItemIndex,
        animationSpec = tween(easing = LinearEasing)
    )

    Box(contentAlignment = Alignment.BottomStart) {
        ScrollableTabIndicator(
            indicatorWidth = sizeList[selectedItemIndex] ?: 0.dp,
            indicatorOffset = indicatorOffset,
            indicatorColor = indicatorColor
        )

        Row(
            modifier = modifier
                .background(color = Color.Transparent)
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items.forEachIndexed { index, text ->
                val isSelected = index == selectedItemIndex

                ScrollableTabItem(
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

@Composable
private fun GlassmorphicTabIndicator(
    indicatorWidth: Dp,
    indicatorOffset: Dp,
    indicatorColor: Color
) {
    Spacer(
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
private fun GlassmorphicTabItem(
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
            GlassmorphicTabIndicator(
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

                    GlassmorphicTabItem(
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

@Preview(showBackground = true)
@Composable
fun PreviewScrollableTab() {
    var selectedItemIndex by remember { mutableStateOf(0) }

    GalapagosTheme {
        ScrollableTabRow(
            items = listOf("전체", "도마뱀", "거북이"),
            selectedItemIndex = selectedItemIndex,
            onClick = { selectedItemIndex = it }
        )
    }
}

@Preview
@Composable
fun PreviewTab() {
    var selectedItemIndex by remember { mutableStateOf(0) }

    GalapagosTheme {
        GlassmorphicTab(
            items = listOf("리스트", "캘린더"),
            selectedItemIndex = selectedItemIndex,
            onClick = { selectedItemIndex = it }
        )
    }
}