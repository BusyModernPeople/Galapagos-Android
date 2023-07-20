package com.busymodernpeople.galapagos.ui.component

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.busymodernpeople.galapagos.ui.theme.*
import kotlin.text.Typography

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    height: Int,
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
    val horizontalPadding = when (height) {
        56 -> 20.dp
        52 -> 20.dp
        40 -> 12.dp
        else -> 0.dp
    }

    val textSize = if (height == 56) 16.sp else 14.sp
    val iconSpacer = if (height == 40) 10.dp else 18.dp

    Button(
        modifier = modifier.height(height.dp),
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