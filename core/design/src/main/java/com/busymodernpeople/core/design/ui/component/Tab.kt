package com.busymodernpeople.core.design.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.ui.component.capture.Capturable
import com.busymodernpeople.core.design.ui.component.capture.rememberCaptureController
import com.busymodernpeople.core.design.ui.theme.BgGray5
import com.busymodernpeople.core.design.ui.theme.FontBlack
import com.busymodernpeople.core.design.ui.theme.FontGray2
import com.busymodernpeople.core.design.ui.theme.FontWhite
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.core.design.ui.theme.PrimaryGreen
import com.busymodernpeople.core.design.ui.theme.Typography
import kotlinx.coroutines.delay

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
            FontGray2
        },
        animationSpec = tween(easing = LinearEasing)
    )

    val fontWeight = if (isSelected) {
        FontWeight.Bold
    } else {
        FontWeight.SemiBold
    }

    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
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
    val indicatorWidth: Dp by animateDpAsState(
        targetValue = sizeList[selectedItemIndex] ?: 0.dp,
        animationSpec = tween(easing = LinearEasing)
    )

    Box(contentAlignment = Alignment.BottomStart) {
        ScrollableTabIndicator(
            indicatorWidth = indicatorWidth,
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

    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
        Surface(
            modifier = modifier,
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
}

@Composable
fun GlassmorphicTab(
    modifier: Modifier = Modifier,
    items: List<String>,
    backgroundBitmap: ImageBitmap,
    selectedItemIndex: Int,
    indicatorColor: Color = PrimaryGreen,
    onClick: (index: Int) -> Unit
) {
    val density = LocalDensity.current
    val sizeList = remember { mutableStateMapOf<Int, Dp>() }

    var width by remember { mutableStateOf(1.dp) }
    var height by remember { mutableStateOf(1.dp) }

    val indicatorOffset: Dp by animateDpAsState(
        targetValue = sizeList
            .values
            .take(selectedItemIndex)
            .sumOf { it },
        animationSpec = tween(easing = LinearEasing)
    )
    val indicatorWidth: Dp by animateDpAsState(
        targetValue = sizeList[selectedItemIndex] ?: 0.dp,
        animationSpec = tween(easing = LinearEasing)
    )

    var offsetX = 0f
    var offsetY = 0f

    Box {
        Surface(
            modifier = modifier
                .width(width)
                .height(height)
                .border(
                    width = 1.dp,
                    color = BgGray5,
                    shape = CircleShape
                )
                .blur(10.dp)
                .clip(CircleShape)
                .onGloballyPositioned {
                    offsetX = it.positionInRoot().x
                    offsetY = it.positionInRoot().y
                }
                .drawWithContent {
                    drawContent()
                    drawImage(backgroundBitmap, Offset(-offsetX, -offsetY))
                },
            shape = CircleShape,
            elevation = 12.dp
        ) { }

        Box(
            modifier = modifier
                .height(IntrinsicSize.Min)
                .onSizeChanged {
                    with(density) {
                        width = it.width.toDp()
                        height = it.height.toDp()
                    }
                }
                .padding(8.dp)
        ) {
            GlassmorphicTabIndicator(
                indicatorWidth = indicatorWidth,
                indicatorOffset = indicatorOffset,
                indicatorColor = indicatorColor
            )

            Row {
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ContentTabItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit,
    text: String
) {
    val textColor = if (isSelected) FontWhite else PrimaryGreen
    val backgroundColor = if (isSelected) PrimaryGreen else Color(0xFFE8E8EF)

    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
        Surface(
            modifier = modifier,
            shape = CircleShape,
            color = backgroundColor,
            onClick = { onClick() },
            interactionSource = NoRippleInteractionSource()
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                text = text,
                style = Typography.body2,
                color = textColor
            )
        }
    }
}

@Composable
fun ContentTab(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedItemIndex: Int,
    onClick: (index: Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEachIndexed { index, text ->
            val isSelected = index == selectedItemIndex

            ContentTabItem(
                isSelected = isSelected,
                onClick = { onClick(index) },
                text = text
            )
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
fun PreviewGlassmorphicTab() {
    var selectedItemIndex by remember { mutableStateOf(0) }

    val captureController = rememberCaptureController()
    var background: ImageBitmap by remember { mutableStateOf(ImageBitmap(1, 1)) }

    val scrollState = rememberScrollState()
    val isScrolling = scrollState.isScrollInProgress

    LaunchedEffect(isScrolling) {
        captureController.capture()
        while (isScrolling) {
            delay(100)
            captureController.capture()
        }
    }

    GalapagosTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Capturable(
                controller = captureController,
                onCaptured = { bitmap, _ ->
                    bitmap?.let {
                        background = it
                    }
                }
            ) {
                val colors = listOf(Color.Black, Color.Blue, Color.White, Color.Cyan, Color.DarkGray)

                Column(Modifier.verticalScroll(scrollState)) {
                    repeat(30) { index ->
                        Box(
                            Modifier.fillMaxWidth().height(50.dp)
                                .background(colors[index % colors.size])
                        )
                    }
                }
            }

            GlassmorphicTab(
                backgroundBitmap = background,
                items = listOf("리스트", "캘린더"),
                selectedItemIndex = selectedItemIndex,
                onClick = { selectedItemIndex = it }
            )
        }
    }
}

@Preview
@Composable
fun PreviewContentTab() {
    var selectedItemIndex by remember { mutableStateOf(0) }

    GalapagosTheme {
        ContentTab(
            items = listOf("자유게시판", "물품거래", "동물분양"),
            selectedItemIndex = selectedItemIndex,
            onClick = { selectedItemIndex = it }
        )
    }
}