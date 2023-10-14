package com.busymodernpeople.feature.auth.join

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.navOptions
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.HomeDestinations
import com.busymodernpeople.core.common.base.rememberGalapagosAppState
import com.busymodernpeople.core.design.ui.component.ButtonSize
import com.busymodernpeople.core.design.ui.component.GButton
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.feature.auth.R
import com.busymodernpeople.feature.auth.join.component.JoinProgressBar
import kotlinx.coroutines.flow.collectLatest

@Preview
@Composable
fun JoinCompleteScreen(
    appState: GalapagosAppState = rememberGalapagosAppState(),
    viewModel: JoinViewModel = hiltViewModel()
) {
    val effectFlow = viewModel.effect

    LaunchedEffect(true) {
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
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            JoinProgressBar(
                modifier = Modifier.fillMaxWidth(),
                initialProgress = 0.75f,
                progress = 1f
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.join_complete_welcome_message))
                    withStyle(style = SpanStyle(GalapagosTheme.colors.PrimaryGreen)) {
                        append("도랭이애비")
                    }
                    append(" 님")
                },
                style = GalapagosTheme.typography.title1.copy(fontWeight = FontWeight.Bold),
                color = GalapagosTheme.colors.FontBlack,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(75.dp))
            Box(
                modifier = Modifier
                    .size(185.dp)
                    .background(
                        color = GalapagosTheme.colors.BgGray3,
                        shape = RoundedCornerShape(12.dp)
                    )
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(id = R.string.join_request_pet_registration),
                style = GalapagosTheme.typography.title4.copy(fontWeight = FontWeight.SemiBold),
                color = GalapagosTheme.colors.FontBlack
            )
            Spacer(modifier = Modifier.weight(1f))

            GButton(
                buttonSize = ButtonSize.Height56,
                iconSpacer = 4,
                content = stringResource(id = R.string.join_register_pet),
                onClick = { appState.navigate(HomeDestinations.ROUTE) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            GButton(
                modifier = Modifier.padding(bottom = 50.dp),
                buttonSize = ButtonSize.Height56,
                iconSpacer = 4,
                content = stringResource(id = R.string.join_guest_mode),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = GalapagosTheme.colors.FontWhite,
                    contentColor = GalapagosTheme.colors.FontGray3
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = GalapagosTheme.colors.BgGray1
                ),
                onClick = {
                    appState.navigate(
                        destination = HomeDestinations.ROUTE,
                        navOptions = navOptions {
                            popUpTo(AuthDestinations.Login.LOGIN)
                        }
                    )
                }
            )
        }
    }
}