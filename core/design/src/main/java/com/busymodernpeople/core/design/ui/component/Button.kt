package com.busymodernpeople.core.design.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.core.design.ui.theme.LocalTypography
import com.busymodernpeople.core.design.ui.theme.Pretendard

sealed class ButtonSize {
    object Height40 : ButtonSize()
    object Height52 : ButtonSize()
    object Height56 : ButtonSize()
}

/**
 * @param leadingIcon 버튼 앞에 표시되는 아이콘
 * @param trailingIcon 버튼 뒤에 표시되는 아이콘
 */
@Composable
fun GButton(
    modifier: Modifier = Modifier,
    buttonSize: ButtonSize = ButtonSize.Height40,
    shape: Shape = RoundedCornerShape(8.dp),
    content: String,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    iconSpacer: Int = 10,
    onClick: () -> Unit,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = GalapagosTheme.colors.PrimaryGreen,
        contentColor = Color.White
    ),
    border: BorderStroke = BorderStroke(width = 0.dp, color = Color.Unspecified),
    elevation: ButtonElevation = ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        disabledElevation = 0.dp
    )
) {
    val localTypography = LocalTypography.current

    // 버튼 크기에 따른 Padding 설정
    val (height, horizontalPadding) = when (buttonSize) {
        ButtonSize.Height56 -> Pair(56.dp, 20.dp)
        ButtonSize.Height52 -> Pair(52.dp, 20.dp)
        ButtonSize.Height40 -> Pair(40.dp, 12.dp)
    }

    val textStyle =
        if (buttonSize == ButtonSize.Height56) {
            localTypography.body1
        } else {
            localTypography.body2
        }

    Button(
        modifier = modifier.fillMaxWidth().height(height),
        onClick = onClick,
        enabled = enabled,
        contentPadding = PaddingValues(horizontal = horizontalPadding),
        colors = colors,
        shape = shape,
        border = border,
        elevation = elevation
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(iconSpacer.dp)
            ) {
                leadingIcon?.let {
                    Icon(
                        painter = painterResource(id = leadingIcon),
                        contentDescription = null,
                        tint = colors.contentColor(enabled = enabled).value
                    )
                }
                Text(
                    text = content,
                    color = colors.contentColor(enabled = enabled).value,
                    style = textStyle.copy(fontWeight = FontWeight.SemiBold)
                )
                trailingIcon?.let {
                    Icon(
                        painter = painterResource(id = trailingIcon),
                        contentDescription = null,
                        tint = colors.contentColor(enabled = enabled).value
                    )
                }
            }
        }
    }
}

/**
 * @param icon 버튼 앞에 표시되는 아이콘
 */
@Composable
fun GFloatingButton(
    modifier: Modifier = Modifier,
    content: String? = null,
    @DrawableRes icon: Int? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    horizontalPadding: Dp = 25.dp,
    verticalPadding: Dp = 15.dp,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = GalapagosTheme.colors.PrimaryGreen,
        contentColor = Color.White,
    ),
    border: BorderStroke = BorderStroke(width = 0.dp, color = Color.Unspecified),
    elevation: ButtonElevation = ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        disabledElevation = 0.dp
    )
) {
    val localTypography = LocalTypography.current

    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = verticalPadding),
        colors = colors,
        shape = RoundedCornerShape(999.dp),
        border = border,
        elevation = elevation
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon?.let {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = colors.contentColor(enabled = enabled).value
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                }

                content?.let {
                    Text(
                        text = content,
                        color = colors.contentColor(enabled = enabled).value,
                        style = localTypography.body1.copy(fontWeight = FontWeight.SemiBold),
                        fontFamily = Pretendard
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGButton() {
    GalapagosTheme {
        Box(
            modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                GButton(
                    content = "확인",
                    onClick = { /*TODO*/ }
                )

                GButton(
                    content = "확인",
                    enabled = false,
                    onClick = { /*TODO*/ }
                )

                GButton(
                    content = "확인",
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = GalapagosTheme.colors.FontWhite,
                        contentColor = GalapagosTheme.colors.FontGray3
                    ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = GalapagosTheme.colors.BgGray1
                    ),
                    onClick = { /*TODO*/ }
                )
            }
        }
    }
}