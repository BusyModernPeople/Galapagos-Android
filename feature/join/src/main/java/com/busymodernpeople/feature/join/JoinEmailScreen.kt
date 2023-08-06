package com.busymodernpeople.feature.join

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.ui.component.ButtonSize
import com.busymodernpeople.core.design.ui.component.GButton
import com.busymodernpeople.core.design.ui.component.GTextField
import com.busymodernpeople.core.design.ui.component.TextFieldButton
import com.busymodernpeople.core.design.ui.component.TextFieldSize
import com.busymodernpeople.core.design.ui.component.toMinSec
import com.busymodernpeople.core.design.ui.join.JoinConditionItem
import com.busymodernpeople.core.design.ui.join.JoinProgressBar
import com.busymodernpeople.core.design.ui.join.JoinTopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import kotlinx.coroutines.delay

@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun JoinEmailScreen(
    onBack: () -> Unit = { },
    onConfirm: () -> Unit = { }
) {
    var email by remember { mutableStateOf("") }
    var isSentAuthenticatioNumber by remember { mutableStateOf(false) }
    var isAuthenticated by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        JoinTopBar {
            onBack()
        }
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Spacer(modifier = Modifier.height(10.dp))
            JoinProgressBar(
                modifier = Modifier.fillMaxWidth(),
                progress = 0.25f
            )
            Spacer(modifier = Modifier.height(40.dp))
            if (!isSentAuthenticatioNumber) {
                Text(
                    text = stringResource(id = R.string.join_input_email),
                    style = GalapagosTheme.typography.title1.copy(fontWeight = FontWeight.Bold),
                    color = GalapagosTheme.colors.FontBlack
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            GTextField(
                textFieldSize = TextFieldSize.Height68,
                enabled = !isSentAuthenticatioNumber,
                value = email,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholderText = stringResource(id = R.string.join_email_textfield_placeholder),
                onValueChange = {
                    email = it
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            GButton(
                buttonSize = ButtonSize.Height56,
                enabled = email.isNotEmpty(),
                content = stringResource(id = R.string.join_authenticate_email),
                onClick = { isSentAuthenticatioNumber = true }
            )

            if (isSentAuthenticatioNumber) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = stringResource(id = R.string.join_input_authenticate_number),
                    style = GalapagosTheme.typography.body2,
                    color = GalapagosTheme.colors.FontBlack
                )
                Spacer(modifier = Modifier.height(10.dp))

                var authenticationNumber by remember { mutableStateOf("") }

                GTextField(
                    textFieldSize = TextFieldSize.Height68,
                    value = authenticationNumber,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    placeholderText = stringResource(id = R.string.join_authentication_number_textfield_placeholder),
                    maxChar = 6,
                    onValueChange = {
                        authenticationNumber = it.filter { symbol ->
                            symbol.isDigit()
                        }
                    },
                    trailingContent = {
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
                                content = stringResource(id = R.string.join_confirm),
                                onClick = { isAuthenticated = true },
                                enabled = authenticationNumber.length == 6
                            )
                        }
                    }
                )
                if (!isAuthenticated) {
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
                            text = stringResource(id = R.string.join_resend_authentication_number),
                            style = GalapagosTheme.typography.body4.copy(fontWeight = FontWeight.Normal),
                            color = GalapagosTheme.colors.FontGray2,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.height(6.dp))
                    JoinConditionItem(
                        isSatisfied = true,
                        content = R.string.join_authenticated_message
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            GButton(
                buttonSize = ButtonSize.Height56,
                enabled = isSentAuthenticatioNumber && isAuthenticated,
                content = stringResource(id = R.string.join_next),
                onClick = { onConfirm() }
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}