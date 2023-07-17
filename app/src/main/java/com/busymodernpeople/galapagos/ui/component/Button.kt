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
fun defaultButton(
    modifier: Modifier,
    height: Int,
    content: String,
    icon_id: Int? = null,
    isRounded: Boolean = false,
    onClick: () -> Unit,
    enabled: Boolean = true,
    colors: ButtonColors,
    border: BorderStroke = BorderStroke(width = 0.dp, color = Color.Unspecified),
    elevation: ButtonElevation = ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        disabledElevation = 0.dp
    )
) {
    // Round인지 아닌지에 따라 Radius 조절
    var radius = if (isRounded) 1000.dp else 8.dp
    
    // 버튼 크기에 따른 Padding 설정
    var horizontalPadding = 0.dp
    var verticalPadding = 0.dp

    when (height) {
        56 -> {
            verticalPadding = 16.dp
            horizontalPadding = 20.dp
        }
        52 -> {
            verticalPadding = 15.dp
            horizontalPadding = 20.dp
        }
        40 -> {
            verticalPadding = 8.dp
            horizontalPadding = 12.dp
        }
    }

    var textSize = if (height == 56) 16.sp else 14.sp
    var iconSpacer = if (height == 40) 10.dp else 18.dp
    var iconSize = 0.dp
    
    // TODO: Width 설정 필요

    Button(
        modifier = Modifier.wrapContentHeight().fillMaxWidth(),
        onClick = onClick, enabled = true,
        contentPadding = PaddingValues(horizontal = horizontalPadding),
        colors = colors,
        shape = RoundedCornerShape(radius),
        border = border,
        elevation = elevation
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier.height(verticalPadding))
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
            Spacer(modifier.height(verticalPadding))
        }
    }
}

@Composable
fun floatingButton(
    modifier: Modifier,
    content: String? = null,
    icon_id: Int? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    horizontalPadding: Dp = 0.dp,
    verticalPadding: Dp = 0.dp,
    colors: ButtonColors,
    border: BorderStroke = BorderStroke(width = 0.dp, color = Color.Unspecified),
    elevation: ButtonElevation = ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        disabledElevation = 0.dp
    )
) {
    Button(
        modifier = Modifier.wrapContentSize(),
        onClick = onClick, enabled = true,
        contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = verticalPadding),
        colors = colors,
        shape = RoundedCornerShape(10000.dp),
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
                }

                if (icon_id != null && content != null) {
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