package com.busymodernpeople.feature.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.ui.component.GButton
import com.busymodernpeople.core.design.ui.component.GTextField
import com.busymodernpeople.core.design.ui.component.TextFieldSize
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.feature.mypage.component.ProfileBasicData

@Preview
@Composable
fun MyPageUpdateScreen(
//    appState: GalapagosAppState = rememberGalapagosAppState(),
//    showBottomSheet: (SheetContent) -> Unit = { },
//    hideBottomSheet: () -> Unit = { }
) {
    var editMode by remember { mutableStateOf(true) }
    var nickname by remember { mutableStateOf("") }
    var star = if (editMode) "*" else ""

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (editMode) {
                TopBar(
                    leadingIcon = null,
                    leadingIconOnClick = { /* TODO */ },
                    content = "내 프로필 수정",
                    trailingIcon = com.busymodernpeople.core.design.R.drawable.ic_animal_category,
                    trailingIconOnClick = { /* TODO */ }
                )
            } else {
                TopBar(
                    leadingIconOnClick = { /* TODO */ }, content = "내 프로필",
                    trailingIcon = com.busymodernpeople.core.design.R.drawable.ic_write,
                    trailingIconOnClick = { /* TODO */ }
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            ProfileBasicData("도랭이애비", arrowFlag = false, isEdit = editMode)
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "기본정보",
                style = GalapagosTheme.typography.title3.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "닉네임${star}",
                style = GalapagosTheme.typography.body1.copy(fontWeight = FontWeight.Medium),
                color = GalapagosTheme.colors.FontGray2,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            GTextField(
                value = "도랭이애비", onValueChange = { nickname = it }, modifier = Modifier.padding(horizontal = 24.dp),
                textFieldSize = TextFieldSize.Height56, enabled = editMode
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "이메일${star}",
                style = GalapagosTheme.typography.body1.copy(fontWeight = FontWeight.Medium),
                color = GalapagosTheme.colors.FontGray2,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            var email by remember { mutableStateOf("") }
            GTextField(
                value = "AAA@naver.com", onValueChange = { email = it }, modifier = Modifier.padding(horizontal = 24.dp),
                textFieldSize = TextFieldSize.Height56, enabled = editMode
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(1f).padding(horizontal = 24.dp)
        ) {
            if (editMode) GButton(content = "완료", onClick = { /*TODO*/ })
        }
    }
}