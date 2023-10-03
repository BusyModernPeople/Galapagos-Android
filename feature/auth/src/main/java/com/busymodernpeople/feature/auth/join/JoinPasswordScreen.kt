package com.busymodernpeople.feature.auth.join

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.busymodernpeople.feature.auth.join.component.JoinProgressBar
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun JoinPasswordScreen(
    appState: GalapagosAppState = rememberGalapagosAppState(),
    viewModel: JoinViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    LaunchedEffect(true) {
        focusRequester.requestFocus()

        effectFlow.collectLatest { effect ->
            when (effect) {
                is JoinContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }

                is JoinContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }
            }
        }
    }

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
            Spacer(modifier = Modifier.height(10.dp))
            JoinProgressBar(
                modifier = Modifier.fillMaxWidth(),
                initialProgress = 0.25f,
                progress = 0.5f
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(id = R.string.join_input_password),
                style = GalapagosTheme.typography.title1.copy(fontWeight = FontWeight.Bold),
                color = GalapagosTheme.colors.FontBlack
            )
            Spacer(modifier = Modifier.height(40.dp))
            GTextField(
                textFieldSize = TextFieldSize.Height68,
                value = uiState.password ?: "",
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeholderText = stringResource(id = R.string.join_password_textfield_placeholder),
                focusRequester = focusRequester,
                onValueChange = {
                    viewModel.updateState(uiState.copy(password = it))
                    viewModel.checkPassword()
                }
            )
            Spacer(modifier = Modifier.height(6.dp))
            FlowRow(
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ConditionItem(
                    isSatisfied = uiState.passwordConditions[0],
                    content = R.string.join_password_condition_english
                )
                ConditionItem(
                    isSatisfied = uiState.passwordConditions[1],
                    content = R.string.join_password_condition_number
                )
                ConditionItem(
                    isSatisfied = uiState.passwordConditions[2],
                    content = R.string.join_password_condition_symbol
                )
                ConditionItem(
                    isSatisfied = uiState.passwordConditions[3],
                    content = R.string.join_password_condition_length
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            GTextField(
                textFieldSize = TextFieldSize.Height68,
                value = uiState.confirmPassword,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeholderText = stringResource(id = R.string.join_confirm_password_textfield_placeholder),
                onValueChange = {
                    viewModel.updateState(uiState.copy(confirmPassword = it))
                }
            )
            Spacer(modifier = Modifier.height(6.dp))
            ConditionItem(
                isSatisfied =
                    uiState.passwordConditions.count { it } == 4
                            && uiState.password == uiState.confirmPassword,
                content = R.string.join_password_condition_match
            )
            Spacer(modifier = Modifier.weight(1f))
            GButton(
                modifier = Modifier.padding(bottom = 50.dp),
                buttonSize = ButtonSize.Height56,
                enabled = uiState.passwordConditions.count { it } == 4
                                && uiState.password == uiState.confirmPassword,
                content = stringResource(id = R.string.join_next),
                onClick = { appState.navigate(AuthDestinations.Join.NICKNAME) }
            )
        }
    }
}