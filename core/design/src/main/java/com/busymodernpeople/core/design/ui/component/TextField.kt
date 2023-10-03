package com.busymodernpeople.core.design.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.core.design.ui.theme.LocalColors
import com.busymodernpeople.core.design.ui.theme.LocalTypography
import kotlinx.coroutines.delay

sealed class TextFieldSize {
    object Height68 : TextFieldSize()
    object Height56 : TextFieldSize()
}

/**
 * @param leadingContent 텍스트 입력 값 앞에 컨텐츠가 있으면 사용
 * @param trailingContent 텍스트 입력 값 뒤에 컨텐츠가 있으면 사용 (타이머, 버튼 등)
 * @param isError 텍스트 입력 값 에러 여부
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GTextField(
    modifier: Modifier = Modifier,
    textFieldSize: TextFieldSize = TextFieldSize.Height68,
    value: String,
    onValueChange: (String) -> Unit,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    placeholderText: String = "",
    isError: Boolean = false,
    enabled: Boolean = true,
    focusRequester: FocusRequester = FocusRequester(),
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: KeyboardActions? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    showLength: Boolean = false,
    maxChar: Int = 20
) {
    val localColors = LocalColors.current
    val localTypography = LocalTypography.current
    
    val composableFocusRequester = remember { focusRequester }
    var initState by remember { mutableStateOf(true) }
    var isFocused by remember { mutableStateOf(false) }

    val borderColor = when {
        initState -> localColors.BgGray1
        !enabled -> localColors.BgGray1
        isError -> localColors.FontRed
        isFocused -> localColors.PrimaryGreen
        else -> localColors.PrimaryGreen
    }

    BasicTextField(
        modifier = modifier
            .focusRequester(composableFocusRequester)
            .onFocusChanged {
                if (it.isFocused && initState) {
                    initState = false
                }
                isFocused = it.isFocused
            }
            .border(
                width = 1.dp,
                color = borderColor,
                shape = when (textFieldSize) {
                    is TextFieldSize.Height68 -> RoundedCornerShape(8.dp)
                    is TextFieldSize.Height56 -> RoundedCornerShape(6.dp)
                }
            )
            .background(
                color = localColors.FontWhite,
                shape = when (textFieldSize) {
                    is TextFieldSize.Height68 -> RoundedCornerShape(8.dp)
                    is TextFieldSize.Height56 -> RoundedCornerShape(6.dp)
                }
            ),
        value = value,
        onValueChange = {
            if (it.length <= maxChar) onValueChange(it)
        },
        singleLine = true,
        enabled = enabled,
        textStyle = localTypography.title4.copy(
            color = if (enabled) localColors.FontGray1 else localColors.BgGray1
        ),
        keyboardOptions = keyboardOptions ?: KeyboardOptions(),
        keyboardActions = keyboardActions ?: KeyboardActions(),
        decorationBox = { innerTextField ->
            Row(
                modifier = when (textFieldSize) {
                    is TextFieldSize.Height68 -> Modifier.padding(20.dp)
                    is TextFieldSize.Height56 -> Modifier.padding(horizontal = 20.dp, vertical = 11.dp)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                leadingContent?.let { leadingContent() }
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            modifier = Modifier.offset(y = 1.dp),
                            text = placeholderText,
                            style = GalapagosTheme.typography.body1.copy(
                                color = localColors.BgGray1
                            )
                        )
                    }
                    innerTextField()
                }
                if (trailingContent != null) {
                    trailingContent()
                } else {
                    if (isFocused) {
                        CompositionLocalProvider(
                            LocalMinimumInteractiveComponentEnforcement provides false
                        ) {
                            if (showLength) {
                                Text(
                                    text = "${value.length}/${maxChar}",
                                    style = LocalTextStyle.current.copy(
                                        color = localColors.BgGray1
                                    )
                                )
                                Spacer(Modifier.width(10.dp))
                            }

                            IconButton(
                                onClick = { onValueChange("") }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_textfield_x),
                                    tint = Color.Unspecified,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        },
        visualTransformation = visualTransformation
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TextFieldButton(
    modifier: Modifier = Modifier,
    content: String,
    shape: Shape = RoundedCornerShape(8.dp),
    onClick: () -> Unit,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = GalapagosTheme.colors.PrimaryGreen,
        contentColor = GalapagosTheme.colors.BgGray4
    ),
    border: BorderStroke = BorderStroke(width = 0.dp, color = Color.Unspecified),
    elevation: ButtonElevation = ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        disabledElevation = 0.dp
    )
) {
    CompositionLocalProvider(
        LocalMinimumInteractiveComponentEnforcement provides false
    ) {
        Button(
            modifier = modifier,
            onClick = onClick,
            enabled = enabled,
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            colors = colors,
            shape = shape,
            border = border,
            elevation = elevation
        ) {
            Text(
                text = content,
                color = colors.contentColor(enabled = enabled).value,
                style = GalapagosTheme.typography.body2.copy(fontWeight = FontWeight.SemiBold)
            )
        }
    }
}

fun Int.toMinSec() : String {
    val minute = this / 60
    val second = this % 60

    return String.format("%d:%02d", minute, second)
}

@Preview(showBackground = true)
@Composable
fun PreviewGTextFieldHeight68() {
    GalapagosTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                var value by remember { mutableStateOf("") }

                // 기본 텍스트필드
                GTextField(
                    value = value,
                    onValueChange = {
                        value = it
                    }
                )

                // 에러 여부 텍스트필드
                GTextField(
                    value = value,
                    onValueChange = {
                        value = it
                    },
                    isError = value.length < 6
                )

                // 에러 여부 && 글자 수 표시 텍스트필드
                GTextField(
                    value = value,
                    onValueChange = {
                        value = it
                    },
                    showLength = true
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGTextFieldHeight56() {
    GalapagosTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                var value by remember { mutableStateOf("") }

                // 기본 텍스트필드
                GTextField(
                    textFieldSize = TextFieldSize.Height56,
                    value = value,
                    onValueChange = {
                        value = it
                    }
                )

                // 에러 여부 텍스트필드
                GTextField(
                    textFieldSize = TextFieldSize.Height56,
                    value = value,
                    onValueChange = {
                        value = it
                    },
                    isError = value.length < 6
                )

                // 에러 여부 && 글자수 표시 텍스트필드
                GTextField(
                    textFieldSize = TextFieldSize.Height56,
                    value = value,
                    onValueChange = {
                        value = it
                    },
                    showLength = true
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGTextFieldWithTrailingContent() {
    GalapagosTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            var isTimerRunning by remember {
                mutableStateOf(true)
            }
            var remainingTime by remember {
                mutableStateOf(180)
            }

            LaunchedEffect(isTimerRunning) {
                while (isTimerRunning && remainingTime > 0) {
                    delay(1000)
                    remainingTime--
                }
                if (isTimerRunning) isTimerRunning = false
            }

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                var value by remember { mutableStateOf("") }

                // 버튼 텍스트 필드
                GTextField(
                    value = value,
                    textFieldSize = TextFieldSize.Height56,
                    onValueChange = {
                        value = it
                    },
                    trailingContent = {
                        TextFieldButton(
                            content = "확인",
                            onClick = { /*TODO*/ },
                            enabled = value.length == 6
                        )
                    }
                )

                // 타이머 && 버튼 텍스트 필드
                GTextField(
                    value = value,
                    placeholderText = "인증코드 6자리 입력",
                    textFieldSize = TextFieldSize.Height56,
                    onValueChange = {
                        value = it
                    },
                    isError = value.length < 6,
                    trailingContent = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = remainingTime.toMinSec(),
                                style = GalapagosTheme.typography.body1.copy(
                                    color = GalapagosTheme.colors.BgGray1
                                )
                            )

                            TextFieldButton(
                                content = "확인",
                                onClick = { /*TODO*/ },
                                enabled = value.length == 6
                            )
                        }
                    }
                )
            }
        }
    }
}