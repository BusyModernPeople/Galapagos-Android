package com.busymodernpeople.feature.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.core.common.base.HomeDestinations
import com.busymodernpeople.core.design.ui.component.ButtonSize
import com.busymodernpeople.core.design.ui.component.GButton
import com.busymodernpeople.core.design.ui.component.GTextField
import com.busymodernpeople.core.design.ui.component.TextFieldSize
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.feature.auth.R

@Preview
@Composable
fun EmailLoginScreen(
    navController: NavController = rememberNavController(),
    viewModel: LoginViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    LaunchedEffect(true) {
        focusRequester.requestFocus()
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
                navController.navigateUp()
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
                modifier = Modifier.focusRequester(focusRequester),
                textFieldSize = TextFieldSize.Height68,
                value = email,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholderText = stringResource(id = R.string.join_email_textfield_placeholder),
                onValueChange = {
                    email = it
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            GTextField(
                textFieldSize = TextFieldSize.Height68,
                value = password,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeholderText = stringResource(id = R.string.join_password_textfield_placeholder),
                onValueChange = {
                    password = it
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.align(Alignment.End),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.End
                )
            ) {
                Text(
                    modifier = Modifier.clickable {
                        navController.navigate(AuthDestinations.FindPassword.ROUTE)
                    },
                    text = stringResource(id = R.string.email_login_find_password),
                    style = GalapagosTheme.typography.body4.copy(
                        fontWeight = FontWeight.Normal,
                        color = GalapagosTheme.colors.FontGray2
                    ),
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = stringResource(id = R.string.email_login_signup),
                    style = GalapagosTheme.typography.body4.copy(
                        fontWeight = FontWeight.Normal,
                        color = GalapagosTheme.colors.FontGray2
                    ),
                    textDecoration = TextDecoration.Underline
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            GButton(
                modifier = Modifier.padding(bottom = 50.dp),
                enabled = email.isNotEmpty() && password.isNotEmpty(),
                buttonSize = ButtonSize.Height56,
                content = stringResource(id = R.string.login),
                onClick = { navController.navigate(HomeDestinations.ROUTE) }
            )
        }
    }
}