package com.busymodernpeople.core.design.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class GalapagosColors(
    fontBlack: Color = Color(0xFF161616),
    fontGray1: Color = Color(0xFF333333),
    fontGray2: Color = Color(0xFF79747E),
    fontGray3: Color = Color(0xFF96979B),
    fontGray4: Color = Color(0xFFC4C4C4),
    fontGray5: Color = Color(0xFFD3D3D3),
    fontWhite: Color = Color(0xFFFFFFFF),
    fontRed: Color = Color(0xFFE71F2A),
    bgGray1: Color = Color(0xFFDBDBDB),
    bgGray2: Color = Color(0xFFEBEBEB),
    bgGray3: Color = Color(0xFFF2F2F2),
    bgGray4: Color = Color(0xFFF9F9F9),
    bgGray5: Color = Color(0xFFFCFCFE),
    iconBlack: Color = Color(0xFF222738),
    primaryGreen: Color = Color(0xFF57BA83),
    primaryYellow: Color = Color(0xFFFFD771)
) {
    var FontBlack by mutableStateOf(fontBlack)
        private set
    var FontGray1 by mutableStateOf(fontGray1)
        private set
    var FontGray2 by mutableStateOf(fontGray2)
        private set
    var FontGray3 by mutableStateOf(fontGray3)
        private set
    var FontGray4 by mutableStateOf(fontGray4)
        private set
    var FontGray5 by mutableStateOf(fontGray5)
        private set
    var FontWhite by mutableStateOf(fontWhite)
        private set
    var FontRed by mutableStateOf(fontRed)
        private set

    var BgGray1 by mutableStateOf(bgGray1)
        private set
    var BgGray2 by mutableStateOf(bgGray2)
        private set
    var BgGray3 by mutableStateOf(bgGray3)
        private set
    var BgGray4 by mutableStateOf(bgGray4)
        private set
    var BgGray5 by mutableStateOf(bgGray5)
        private set

    var IconBlack by mutableStateOf(iconBlack)
        private set

    var PrimaryGreen by mutableStateOf(primaryGreen)
        private set
    var PrimaryYellow by mutableStateOf(primaryYellow)
        private set
    
    fun copy(
        fontBlack: Color = this.FontBlack,
        fontGray1: Color = this.FontGray1,
        fontGray2: Color = this.FontGray2,
        fontGray3: Color = this.FontGray3,
        fontGray4: Color = this.FontGray4,
        fontGray5: Color = this.FontGray5,
        fontWhite: Color = this.FontWhite,
        fontRed: Color = this.FontRed,
        bgGray1: Color = this.BgGray1,
        bgGray2: Color = this.BgGray2,
        bgGray3: Color = this.BgGray3,
        bgGray4: Color = this.BgGray4,
        bgGray5: Color = this.BgGray5,
        iconBlack: Color = this.IconBlack,
        primaryGreen: Color = this.PrimaryGreen,
        primaryYellow: Color = this.PrimaryYellow
    ) = GalapagosColors(
        fontBlack = fontBlack,
        fontGray1 = fontGray1,
        fontGray2 = fontGray2,
        fontGray3 = fontGray3,
        fontGray4 = fontGray4,
        fontGray5 = fontGray5,
        fontWhite = fontWhite,
        fontRed = fontRed,
        bgGray1 = bgGray1,
        bgGray2 = bgGray2,
        bgGray3 = bgGray3,
        bgGray4 = bgGray4,
        bgGray5 = bgGray5,
        iconBlack = iconBlack,
        primaryGreen = primaryGreen,
        primaryYellow = primaryYellow
    )

    fun updateColorFrom(other: GalapagosColors) {
        FontBlack = other.FontBlack
        FontGray1 = other.FontGray1
        FontGray2 = other.FontGray2
        FontGray3 = other.FontGray3
        FontGray4 = other.FontGray4
        FontGray5 = other.FontGray5
        FontWhite = other.FontWhite
        FontRed = other.FontRed
        BgGray1 = other.BgGray1
        BgGray2 = other.BgGray2
        BgGray3 = other.BgGray3
        BgGray4 = other.BgGray4
        BgGray5 = other.BgGray5
        IconBlack = other.IconBlack
        PrimaryGreen = other.PrimaryGreen
        PrimaryYellow = other.PrimaryYellow
    }
}

val LocalColors = staticCompositionLocalOf { galapagosColors() }