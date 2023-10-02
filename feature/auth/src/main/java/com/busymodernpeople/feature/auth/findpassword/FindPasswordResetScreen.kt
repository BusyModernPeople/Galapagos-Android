package com.busymodernpeople.feature.auth.findpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.rememberGalapagosAppState
import com.busymodernpeople.core.design.ui.component.ButtonSize
import com.busymodernpeople.core.design.ui.component.GButton
import com.busymodernpeople.core.design.ui.component.GTextField
import com.busymodernpeople.core.design.ui.component.TextFieldSize
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.feature.auth.R
import com.busymodernpeople.feature.auth.component.ConditionItem

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun FindPasswordResetScreen(
    appState: GalapagosAppState = rememberGalapagosAppState()
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    LaunchedEffect(true) {
        focusRequester.requestFocus()
    }

    // TODO: 후에 패스워드 확인 과정 ViewModel 단으로 넘김
    val regExp = listOf(
        "^(?=.*[a-zA-Z]).+",
        "^(?=.*[0-9]).+",
        """^(?=.*[-+_!@#\$%^&*., ?]).+""",
        "^.{8,20}$"
    )
    val conditions = mutableListOf(false, false, false, false)
    for (i: Int in conditions.indices) {
        conditions[i] = password.matches(regExp[i].toRegex())
    }
    val allSatisfied = conditions.count { it } == 4

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        TopBar(
            leadingIconOnClick = { appState.navigateUp() }
        )
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(id = R.string.find_password_reset_title),
                style = GalapagosTheme.typography.title1.copy(fontWeight = FontWeight.Bold),
                color = GalapagosTheme.colors.FontBlack
            )
            Spacer(modifier = Modifier.height(40.dp))
            GTextField(
                modifier = Modifier.focusRequester(focusRequester),
                textFieldSize = TextFieldSize.Height68,
                value = password,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeholderText = stringResource(id = R.string.find_password_password_textfield_placeholder),
                onValueChange = {
                    password = it
                }
            )
            Spacer(modifier = Modifier.height(6.dp))
            FlowRow(
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ConditionItem(
                    isSatisfied = conditions[0],
                    content = R.string.join_password_condition_english
                )
                ConditionItem(
                    isSatisfied = conditions[1],
                    content = R.string.join_password_condition_number
                )
                ConditionItem(
                    isSatisfied = conditions[2],
                    content = R.string.join_password_condition_symbol
                )
                ConditionItem(
                    isSatisfied = conditions[3],
                    content = R.string.join_password_condition_length
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            GTextField(
                textFieldSize = TextFieldSize.Height68,
                value = confirmPassword,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeholderText = stringResource(id = R.string.find_password_confirm_password_textfield_placeholder),
                onValueChange = {
                    confirmPassword = it
                }
            )
            Spacer(modifier = Modifier.height(6.dp))
            ConditionItem(
                isSatisfied = allSatisfied && password == confirmPassword,
                content = R.string.join_password_condition_match
            )
            Spacer(modifier = Modifier.weight(1f))
            GButton(
                modifier = Modifier.padding(bottom = 50.dp),
                buttonSize = ButtonSize.Height56,
                enabled = allSatisfied && password == confirmPassword,
                content = stringResource(id = R.string.join_next),
                onClick = { appState.navigate(AuthDestinations.FindPassword.COMPLETE) }
            )
        }
    }
}