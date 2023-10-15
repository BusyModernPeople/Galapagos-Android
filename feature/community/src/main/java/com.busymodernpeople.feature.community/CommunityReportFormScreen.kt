package com.busymodernpeople.feature.community

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.rememberGalapagosAppState
import com.busymodernpeople.core.design.ui.component.ButtonSize
import com.busymodernpeople.core.design.ui.component.GButton
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.core.design.ui.theme.Pretendard

@Preview
@Composable
fun CommunityReportFormScreen(
    appState: GalapagosAppState = rememberGalapagosAppState(),
    title: String = "게시판 신고"
) {
    var reportText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
    ) {
        TopBar(
            content = title,
            leadingIconOnClick = { appState.navigateUp() }
        )
        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "기타",
                style = TextStyle(
                    fontFamily = Pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = GalapagosTheme.colors.FontGray1,
                    lineHeight = 27.sp
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            ReportTextField(
                value = reportText, onValueChange = { reportText = it },
                placeholderText = "신고 내용을 작성해주세요"
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
        GButton(
            modifier = Modifier.padding(horizontal = 24.dp),
            content = "제출", onClick = { /*TODO*/ },
            buttonSize = ButtonSize.Height56
        )
    }
}

@Composable
fun ReportTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String = "",
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: KeyboardActions? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused = it.isFocused
            }
            .border(
                width = 1.dp,
                color = GalapagosTheme.colors.BgGray1,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = GalapagosTheme.colors.FontWhite,
                shape = RoundedCornerShape(12.dp)
            ),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = GalapagosTheme.typography.body1.copy(color = GalapagosTheme.colors.FontBlack),
        keyboardOptions = keyboardOptions ?: KeyboardOptions(),
        keyboardActions = keyboardActions ?: KeyboardActions(),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(0.44f)) {
                    if (value.isEmpty()) {
                        Text(
                            modifier = Modifier.offset(y = 1.dp),
                            text = placeholderText,
                            style = GalapagosTheme.typography.body1.copy(
                                color = GalapagosTheme.colors.BgGray1
                            )
                        )
                    }
                    innerTextField()
                }
            }
        },
        visualTransformation = visualTransformation
    )
}