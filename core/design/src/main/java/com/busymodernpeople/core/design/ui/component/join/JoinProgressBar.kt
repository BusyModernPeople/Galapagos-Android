package com.busymodernpeople.core.design.ui.component.join

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

@Composable
fun JoinProgressBar(
    modifier: Modifier = Modifier,
    /*@FloatRange(from = 0.0, to = 1.0)*/
    progress: Float,
    /*@FloatRange(from = 0.0, to = 1.0)*/
    initialProgress: Float = 0.029f,
    bgColor: Color = GalapagosTheme.colors.BgGray3,
    barColor: Color = GalapagosTheme.colors.PrimaryGreen,
    height: Dp = 8.dp
) {
    val animatedProgress = remember { Animatable(initialProgress) }
    LaunchedEffect(true) {
        animatedProgress.animateTo(
            targetValue = progress,
            animationSpec = tween(
                durationMillis = 500,
                easing = LinearEasing
            )
        )
    }

    Canvas(modifier = modifier) {
        drawRoundedLinearProgressBar(
            startFraction = 0f,
            endFraction = 1f,
            height = height,
            color = bgColor
        )

        drawRoundedLinearProgressBar(
            startFraction = 0f,
            endFraction = initialProgress,
            height = height,
            color = barColor
        )

        drawRoundedLinearProgressBar(
            startFraction = 0f,
            endFraction = animatedProgress.value,
            height = height,
            color = barColor
        )
    }
}

private fun DrawScope.drawRoundedLinearProgressBar(
    startFraction: Float,
    endFraction: Float,
    height: Dp,
    color: Color,
) {
    val width = size.width
    val yOffset = 0f

    val isLtr = layoutDirection == LayoutDirection.Ltr
    val barStart = (if (isLtr) startFraction else 1f - endFraction) * width
    val barEnd = (if (isLtr) endFraction else 1f - startFraction) * width

    drawRoundRect(
        color = color,
        topLeft = Offset(barStart, yOffset),
        size = Size(barEnd, height.toPx()),
        cornerRadius = CornerRadius(4.dp.toPx())
    )
}