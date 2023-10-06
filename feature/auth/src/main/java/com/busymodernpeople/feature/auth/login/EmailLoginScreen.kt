package com.busymodernpeople.feature.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
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
import kotlinx.coroutines.flow.collectLatest

@Preview
@Composable
fun EmailLoginScreen(
    appState: GalapagosAppState = rememberGalapagosAppState(),
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    LaunchedEffect(true) {
        focusRequester.requestFocus()

        effectFlow.collectLatest { effect ->
            when (effect) {
                is LoginContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }

                is LoginContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }

                else -> { }
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
            leadingIconOnClick = {
                appState.navigateUp()
            }
        )
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(id = R.string.email_login_title),
                style = GalapagosTheme.typography.title1.copy(fontWeight = FontWeight.Bold),
                color = GalapagosTheme.colors.FontBlack
            )
            Spacer(modifier = Modifier.height(40.dp))
            GTextField(
                modifier = Modifier.onFocusChanged {
                    if (it.isFocused && uiState.emailErrorState) {
                        viewModel.updateState(
                            uiState.copy(emailErrorState = false)
                        )
                    }
                },
                textFieldSize = TextFieldSize.Height68,
                value = uiState.email,
                isError = uiState.emailErrorState,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholderText = stringResource(id = R.string.join_email_textfield_placeholder),
                focusRequester = focusRequester,
                maxChar = 40,
                onValueChange = {
                    viewModel.updateState(
                        uiState.copy(email = it)
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            GTextField(
                modifier = Modifier.onFocusChanged {
                    if (it.isFocused && uiState.passwordErrorState) {
                        viewModel.updateState(
                            uiState.copy(passwordErrorState = false)
                        )
                    }
                },
                textFieldSize = TextFieldSize.Height68,
                value = uiState.password,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeholderText = stringResource(id = R.string.join_password_textfield_placeholder),
                onValueChange = {
                    viewModel.updateState(
                        uiState.copy(password = it)
                    )
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 4.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                Text(
                    modifier = Modifier.clickable {
                        appState.navigate(AuthDestinations.ResetPassword.ROUTE)
                    },
                    text = stringResource(id = R.string.email_login_forget_password),
                    style = GalapagosTheme.typography.body4.copy(
                        fontWeight = FontWeight.Normal,
                        color = GalapagosTheme.colors.FontGray2
                    )
                )
                Text(
                    modifier = Modifier.clickable {
                        appState.navigate(AuthDestinations.ResetPassword.ROUTE)
                    },
                    text = stringResource(id = R.string.email_login_reset_password),
                    style = GalapagosTheme.typography.body4.copy(
                        color = GalapagosTheme.colors.FontGray1
                    ),
                    textDecoration = TextDecoration.Underline
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            GButton(
                modifier = Modifier.padding(bottom = 50.dp),
                enabled = uiState.email.isNotEmpty() && uiState.password.isNotEmpty(),
                buttonSize = ButtonSize.Height56,
                content = stringResource(id = R.string.login),
                onClick = {
                    viewModel.emailLogin()
                }
            )
        }
    }
}