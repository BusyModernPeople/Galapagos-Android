package com.busymodernpeople.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.SheetContent
import com.busymodernpeople.core.common.base.rememberGalapagosAppState
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.component.TextFieldSize
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.core.design.ui.theme.LocalColors
import com.busymodernpeople.core.design.ui.theme.LocalTypography
import com.busymodernpeople.feature.mypage.component.MenuListItem

@Preview
@Composable
fun MyPageEventScreen(
    appState: GalapagosAppState = rememberGalapagosAppState(),
    showBottomSheet: (SheetContent) -> Unit = { },
    hideBottomSheet: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        var searchText by remember { mutableStateOf("") }

        TopBar(leadingIconOnClick = { appState.navigateUp() }, content = "이벤트 및 공지사항")
        Spacer(modifier = Modifier.height(20.dp))
        MyPageSearchBar(modifier = Modifier.padding(horizontal = 24.dp), value = searchText, onValueChange = { searchText = it })
        Spacer(modifier = Modifier.height(40.dp))
        InformMenu()
        Spacer(modifier = Modifier.height(26.dp))
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(1f)
                .padding(horizontal = 24.dp)
                .background(color = GalapagosTheme.colors.BgGray3)
        ) {}
        Spacer(modifier = Modifier.height(26.dp))
        EventMenu()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyPageSearchBar(
    modifier: Modifier = Modifier,
    textFieldSize: TextFieldSize = TextFieldSize.Height68,
    value: String,
    onValueChange: (String) -> Unit,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    placeholderText: String = "검색하기",
    enabled: Boolean = true,
    focusRequester: FocusRequester = FocusRequester(),
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: KeyboardActions? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val localColors = LocalColors.current
    val localTypography = LocalTypography.current

    val composableFocusRequester = remember { focusRequester }
    var initState by remember { mutableStateOf(true) }
    var isFocused by remember { mutableStateOf(false) }

    val borderColor = when {
        initState -> localColors.BgGray1
        !enabled -> localColors.BgGray1
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
                shape = RoundedCornerShape(100.dp)
            )
            .background(
                color = localColors.FontWhite,
                shape = RoundedCornerShape(100.dp)
            ),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        singleLine = true,
        enabled = enabled,
        textStyle = localTypography.body1.copy(
            color = if (enabled) localColors.FontGray1 else localColors.BgGray1
        ),
        keyboardOptions = keyboardOptions ?: KeyboardOptions(),
        keyboardActions = keyboardActions ?: KeyboardActions(),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16 .dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            modifier = Modifier.offset(y = 1.dp),
                            text = placeholderText,
                            style = GalapagosTheme.typography.body1.copy(
                                color = Color(0xFFBDBEC7)
                            )
                        )
                    }
                    innerTextField()
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
                if (isFocused) {
                    CompositionLocalProvider(
                        LocalMinimumInteractiveComponentEnforcement provides false
                    ) {
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
        },
        visualTransformation = visualTransformation
    )
}

@Preview
@Composable
fun MyPageInformList(
    content: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = content,
            style = GalapagosTheme.typography.body1.copy(color = GalapagosTheme.colors.FontGray1),
            modifier = Modifier.padding(end = 30.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right_black),
            tint = GalapagosTheme.colors.FontBlack,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun InformMenu() {
    Column(
        modifier = Modifier
    ) {
        Text(
            text = "일반공지",
            style = GalapagosTheme.typography.title3.copy(fontWeight = FontWeight.Bold, color = Color(0xFF111111)),
            modifier = Modifier.padding(start = 24.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
        MyPageInformList("[공지] 질문금지, 상품판매 금지")
        Spacer(modifier = Modifier.height(26.dp))
        MyPageInformList("[공지] 공지 글 작성")
    }
}

@Preview
@Composable
fun EventMenu() {
    Column(
        modifier = Modifier
    ) {
        Text(
            text = "이벤트",
            style = GalapagosTheme.typography.title3.copy(fontWeight = FontWeight.Bold, color = Color(0xFF111111)),
            modifier = Modifier.padding(start = 24.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
        MyPageInformList("[공지] 질문금지, 상품판매 금지")
        Spacer(modifier = Modifier.height(26.dp))
        MyPageInformList("[공지] 공지 글 작성")
    }
}