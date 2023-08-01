package com.busymodernpeople.core.design.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object GalapagosTheme {
    val colors: GalapagosColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: GalapagosTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}