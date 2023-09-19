package com.busymodernpeople.feature.auth.join

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.core.design.ui.component.ButtonSize
import com.busymodernpeople.core.design.ui.component.GButton
import com.busymodernpeople.core.design.ui.component.GTextField
import com.busymodernpeople.core.design.ui.component.TextFieldSize
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.feature.auth.R
import com.busymodernpeople.feature.auth.component.ConditionItem
import com.busymodernpeople.feature.auth.join.component.JoinProgressBar

@Preview
@Composable
fun JoinNicknameScreen(
    navController: NavController = rememberNavController()
) {
    var nickname by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        TopBar(
            leadingIconOnClick = { navController.navigateUp() }
        )
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Spacer(modifier = Modifier.height(10.dp))
            JoinProgressBar(
                modifier = Modifier.fillMaxWidth(),
                initialProgress = 0.5f,
                progress = 0.75f
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(id = R.string.join_input_nickname),
                style = GalapagosTheme.typography.title1.copy(fontWeight = FontWeight.Bold),
                color = GalapagosTheme.colors.FontBlack
            )
            Spacer(modifier = Modifier.height(40.dp))
            GTextField(
                textFieldSize = TextFieldSize.Height68,
                value = nickname,
                placeholderText = stringResource(id = R.string.join_nickname_textfield_placeholder),
                showLength = true,
                maxChar = 6,
                onValueChange = {
                    nickname = it
                }
            )
            if (nickname.length in 2..6) {
                Spacer(modifier = Modifier.height(6.dp))
                ConditionItem(
                    isSatisfied = true,
                    content = R.string.join_nickname_condition_satisfied_message
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            GButton(
                modifier = Modifier.padding(bottom = 50.dp),
                buttonSize = ButtonSize.Height56,
                enabled = nickname.length in 2..6,
                content = stringResource(id = R.string.join_next),
                onClick = { navController.navigate(AuthDestinations.Join.COMPLETE) }
            )
        }
    }
}