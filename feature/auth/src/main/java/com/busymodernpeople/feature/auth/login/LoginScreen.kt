package com.busymodernpeople.feature.auth.login

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.rememberGalapagosAppState
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.feature.auth.R
import com.google.android.gms.common.api.ApiException
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.flow.collectLatest

@Preview
@Composable
fun LoginScreen(
    appState: GalapagosAppState = rememberGalapagosAppState(),
    viewModel: LoginViewModel = hiltViewModel()
) {
    val effectFlow = viewModel.effect

    val context = LocalContext.current

    val googleLoginResultLauncher =
        rememberLauncherForActivityResult(contract = GoogleLoginContract()) { task ->
            try {
                val gsa = task?.getResult(ApiException::class.java)
                if (gsa != null) {
                    viewModel.googleLogin(
                        code = gsa.serverAuthCode ?: "",
                        idToken = gsa.idToken ?: "",
                        email = gsa.email ?: ""
                    )
                }
            } catch (e: ApiException) {
                Log.d("googleLoginError", e.toString())
            }
        }

    LaunchedEffect(true) {
        UserApiClient.instance.unlink { error ->
            if (error != null) Log.d("카카오", "연결 해제")
        }
        UserApiClient.instance.logout { error ->
            if (error != null) Log.d("카카오", "연결 해제")
        }

        effectFlow.collectLatest { effect ->
            when (effect) {
                is LoginContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }

                is LoginContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }

                is LoginContract.Effect.LaunchGoogleLogin -> {
                    googleLoginResultLauncher.launch(GOOGLE_LOGIN_REQUEST)
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(175.dp))
        GalapagosTitle()
        Spacer(modifier = Modifier.height(33.dp))
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.login_intro1))
                withStyle(style = SpanStyle(GalapagosTheme.colors.PrimaryGreen)) {
                    append(stringResource(id = R.string.login_title))
                }
                append(stringResource(id = R.string.login_intro2))
            },
            style = GalapagosTheme.typography.body1,
            color = GalapagosTheme.colors.FontBlack,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(134.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            SocialLoginButton(icon = R.drawable.ic_kakao_login) {
                viewModel.processEvent(LoginContract.Event.KaKaoLoginButtonClicked(context))
            }
            SocialLoginButton(icon = R.drawable.ic_naver_login) {
                viewModel.processEvent(LoginContract.Event.NaverLoginButtonClicked(context))
            }
            SocialLoginButton(icon = R.drawable.ic_google_login) {
                viewModel.processEvent(LoginContract.Event.GoogleLoginButtonClicked)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                modifier = Modifier.clickable {
                    appState.navigate("${AuthDestinations.Join.ROUTE}?socialType=EMAIL&email=")
                },
                text = stringResource(id = R.string.login_email_signup),
                color = GalapagosTheme.colors.FontBlack,
                textDecoration = TextDecoration.Underline
            )
            Divider(
                modifier = Modifier
                    .width(0.6.dp)
                    .height(15.dp),
                color = GalapagosTheme.colors.BgGray1
            )
            Text(
                modifier = Modifier.clickable {
                    appState.navigate(AuthDestinations.Login.EMAIL_LOGIN)
                },
                text = stringResource(id = R.string.login_email_login),
                color = GalapagosTheme.colors.FontBlack,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Composable
private fun GalapagosTitle() {
    Image(
        painter = painterResource(R.drawable.ic_app_logo),
        contentDescription = null
    )
    Spacer(modifier = Modifier.height(24.dp))
    Image(
        painter = painterResource(R.drawable.ic_app_name),
        contentDescription = null
    )
}

@Composable
private fun SocialLoginButton(
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = icon),
        contentDescription = null,
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .clickable {
                onClick()
            },
        contentScale = ContentScale.Crop
    )
}