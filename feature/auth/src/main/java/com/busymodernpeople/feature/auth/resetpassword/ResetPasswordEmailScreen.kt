import androidx.compose.foundation.background
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

@Preview
@Composable
fun FindPasswordEmailScreen(
    appState: GalapagosAppState = rememberGalapagosAppState()
) {
    var email by remember { mutableStateOf("") }
    var isSentAuthenticationCode by remember { mutableStateOf(false) }
    var isAuthenticated by remember { mutableStateOf(false) }

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
            leadingIconOnClick = { appState.navigateUp() }
        )
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(id = R.string.find_password_email_title),
                style = GalapagosTheme.typography.title1.copy(fontWeight = FontWeight.Bold),
                color = GalapagosTheme.colors.FontBlack
            )
            Spacer(modifier = Modifier.height(40.dp))
            
            Text(
                text = stringResource(id = R.string.find_password_input_registered_email),
                style = GalapagosTheme.typography.body2.copy(fontWeight = FontWeight.Normal),
                color = GalapagosTheme.colors.FontGray1
            )
            Spacer(modifier = Modifier.height(10.dp))

            GTextField(
                modifier = Modifier.focusRequester(focusRequester),
                textFieldSize = TextFieldSize.Height68,
                enabled = !isSentAuthenticationCode,
                value = email,
                maxChar = 40,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholderText = stringResource(id = R.string.find_password_email_textfield_placeholder),
                onValueChange = {
                    email = it
                }
            )
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
                    text = stringResource(id = R.string.find_password_not_received_authentication_code),
                    style = GalapagosTheme.typography.body4.copy(fontWeight = FontWeight.Normal),
                    color = GalapagosTheme.colors.FontGray2
                )
                Text(
                    text = stringResource(id = R.string.find_password_resend_authentication_code),
                    style = GalapagosTheme.typography.body4.copy(fontWeight = FontWeight.Normal),
                    color = GalapagosTheme.colors.FontGray2,
                    textDecoration = TextDecoration.Underline
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            GButton(
                modifier = Modifier.padding(bottom = 50.dp),
                buttonSize = ButtonSize.Height56,
                enabled = email.isNotEmpty(),
                content = stringResource(id = R.string.find_password_send_authentication_code),
                onClick = { appState.navigate(AuthDestinations.ResetPassword.COMPLETE) }
            )
        }
    }
}

