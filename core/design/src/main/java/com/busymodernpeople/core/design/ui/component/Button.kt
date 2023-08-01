package com.busymodernpeople.core.design.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.busymodernpeople.core.design.ui.theme.Pretendard
import com.busymodernpeople.core.design.ui.theme.PrimaryGreen

sealed class Button {
    object Height40 : Button()
    object Height52 : Button()
    object Height56 : Button()
}

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    button: Button = Button.Height40,
    shape: Shape = RoundedCornerShape(8.dp),
    content: String,
    icon_id: Int? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = PrimaryGreen,
        contentColor = Color.White
    ),
    border: BorderStroke = BorderStroke(width = 0.dp, color = Color.Unspecified),
    elevation: ButtonElevation = ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        disabledElevation = 0.dp
    )
) {
    // 버튼 크기에 따른 Padding 설정
    val (height, horizontalPadding) = when (button) {
        Button.Height56 -> Pair(56.dp, 20.dp)
        Button.Height52 -> Pair(52.dp, 20.dp)
        Button.Height40 -> Pair(40.dp, 12.dp)
    }

    val textSize = if (button == Button.Height56) 16.sp else 14.sp
    val iconSpacer = if (button == Button.Height40) 10.dp else 18.dp

    Button(
        modifier = modifier.height(height),
        onClick = onClick,
        enabled = enabled,
        contentPadding = PaddingValues(horizontal = horizontalPadding),
        colors = colors,
        shape = shape,
        border = border,
        elevation = elevation
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = content,
                    color = colors.contentColor(enabled = enabled).value,
                    fontSize = textSize,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Pretendard
                )
                if (icon_id != null) {
                    Spacer(modifier = Modifier.width(iconSpacer))
                    Icon(
                        painter = painterResource(id = icon_id),
                        contentDescription = null,
                        tint = colors.contentColor(enabled = enabled).value
                    )
                }
            }
        }
    }
}

@Composable
fun FloatingButton(
    modifier: Modifier = Modifier,
    content: String? = null,
    icon_id: Int? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    horizontalPadding: Dp = 25.dp,
    verticalPadding: Dp = 15.dp,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = PrimaryGreen,
        contentColor = Color.White,
    ),
    border: BorderStroke = BorderStroke(width = 0.dp, color = Color.Unspecified),
    elevation: ButtonElevation = ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        disabledElevation = 0.dp
    )
) {
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
                if (icon_id != null) {
                    Icon(
                        painter = painterResource(id = icon_id),
                        contentDescription = null,
                        tint = colors.contentColor(enabled = enabled).value
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                }

                if (content != null) {
                    Text(
                        text = content,
                        color = colors.contentColor(enabled = enabled).value,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Pretendard
                    )
                }
            }
        }
    }
}