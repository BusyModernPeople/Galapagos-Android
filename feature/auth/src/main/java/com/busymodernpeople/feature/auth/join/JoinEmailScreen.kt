package com.busymodernpeople.feature.auth.join

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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
import com.busymodernpeople.core.design.ui.component.TextFieldButton
import com.busymodernpeople.core.design.ui.component.TextFieldSize
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.component.toMinSec
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.feature.auth.R
import com.busymodernpeople.feature.auth.component.ConditionItem
import com.busymodernpeople.feature.auth.join.component.JoinProgressBar
import kotlinx.coroutines.flow.collectLatest

@Preview
@Composable
fun JoinEmailScreen(
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
                progress = 0.25f
            )
            Spacer(modifier = Modifier.height(40.dp))
            if (!uiState.isSentAuthCode) {
                Text(
                    text = stringResource(id = R.string.join_input_email),
                    style = GalapagosTheme.typography.title1.copy(fontWeight = FontWeight.Bold),
                    color = GalapagosTheme.colors.FontBlack
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            GTextField(
                modifier = Modifier.focusRequester(focusRequester),
                textFieldSize = TextFieldSize.Height68,
                enabled = !uiState.isSentAuthCode,
                value = uiState.email ?: "",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholderText = stringResource(id = R.string.join_email_textfield_placeholder),
                maxChar = 40,
                onValueChange = {
                    viewModel.updateState(
                        uiState.copy(
                            email = it
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            GButton(
                buttonSize = ButtonSize.Height56,
                enabled = uiState.email.isNotEmpty() && !uiState.isSentAuthCode,
                content = stringResource(id = R.string.join_authenticate_email),
                onClick = {
                    viewModel.processEvent(
                        JoinContract.Event.SendAuthCode
                    )
                }
            )

            if (uiState.isSentAuthCode) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = stringResource(id = R.string.join_input_authenticate_number),
                    style = GalapagosTheme.typography.body2,
                    color = GalapagosTheme.colors.FontBlack
                )
                Spacer(modifier = Modifier.height(10.dp))


                GTextField(
                    textFieldSize = TextFieldSize.Height68,
                    value = uiState.authCode,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    placeholderText = stringResource(id = R.string.join_authentication_number_textfield_placeholder),
                    maxChar = 6,
                    onValueChange = {
                        viewModel.updateState(uiState.copy(authCode = it))
                    },
                    trailingContent = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = uiState.remainingTime.toMinSec(),
                                style = GalapagosTheme.typography.body1.copy(
                                    color = GalapagosTheme.colors.BgGray1
                                )
                            )

                            TextFieldButton(
                                content = stringResource(id = R.string.join_confirm),
                                onClick = {
                                    viewModel.processEvent(
                                        JoinContract.Event.AuthenticateCode
                                    )
                                },
                                enabled = uiState.authCode.length == 6
                            )
                        }
                    }
                )
                if (!uiState.isAuthenticated) {
                    Spacer(modifier = Modifier.height(7.5.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_join_info),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Text(
                            text = stringResource(id = R.string.join_not_received_authentication_number),
                            style = GalapagosTheme.typography.body4.copy(fontWeight = FontWeight.Normal),
                            color = GalapagosTheme.colors.FontGray2
                        )
                        Text(
                            modifier = Modifier.clickable {
                                viewModel.processEvent(JoinContract.Event.SendAuthCode)
                            },
                            text = stringResource(id = R.string.join_resend_authentication_number),
                            style = GalapagosTheme.typography.body4.copy(fontWeight = FontWeight.Normal),
                            color = GalapagosTheme.colors.FontGray2,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.height(6.dp))
                    ConditionItem(
                        isSatisfied = true,
                        content = R.string.join_authenticated_message
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            GButton(
                modifier = Modifier.padding(bottom = 50.dp),
                buttonSize = ButtonSize.Height56,
                enabled = uiState.isSentAuthCode && uiState.isAuthenticated,
                content = stringResource(id = R.string.join_next),
                onClick = { appState.navigate(AuthDestinations.Join.PASSWORD) }
            )
        }
    }
}