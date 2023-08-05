package com.busymodernpeople.core.design.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun EnterAnimation(content: @Composable AnimatedVisibilityScope.() -> Unit) {
    val visibleState = remember { MutableTransitionState(false) }
    visibleState.targetState = true

    AnimatedVisibility(
        visibleState = visibleState,
        enter = slideInHorizontally(animationSpec = tween(durationMillis = 200)) { fullWidth ->
            fullWidth / 3
        } + fadeIn(
            animationSpec = tween(durationMillis = 500)
        ),
        exit = slideOutHorizontally(animationSpec = spring(stiffness = Spring.StiffnessHigh)) {
            500
        } + fadeOut(),
        content = content
    )
}