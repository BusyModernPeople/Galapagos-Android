package com.busymodernpeople.feature.auth.join

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState
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
fun JoinAgreeScreen(
    appState: GalapagosAppState = rememberGalapagosAppState(),
    viewModel: JoinViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Spacer(modifier = Modifier.height(10.dp))
            JoinProgressBar(
                modifier = Modifier.fillMaxWidth(),
                progress = 0f
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(id = R.string.join_terms_agree),
                style = GalapagosTheme.typography.title1.copy(fontWeight = FontWeight.Bold),
                color = GalapagosTheme.colors.FontBlack
            )
            Text(
                text = stringResource(id = R.string.join_terms_agree_intro),
                style = GalapagosTheme.typography.body1,
                color = GalapagosTheme.colors.FontGray2
            )
            Spacer(modifier = Modifier.height(40.dp))

            val allChecked = uiState.termsAgreed.count { it } == 3
            
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = 12.dp
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CheckBoxItem(
                        checked = allChecked,
                        content = {
                            Text(
                                text = stringResource(id = R.string.join_agree_all),
                                style = GalapagosTheme.typography.title4.copy(fontWeight = FontWeight.Bold)
                            )
                        },
                        onClick = {
                            if (allChecked) {
                                viewModel.updateState(
                                    uiState.copy(
                                        termsAgreed = listOf(false, false, false)
                                    )
                                )
                            } else {
                                viewModel.updateState(
                                    uiState.copy(
                                        termsAgreed = listOf(true, true, true)
                                    )
                                )
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                CheckBoxItem(
                    checked = uiState.termsAgreed[0],
                    content = {
                        Text(
                            text = buildAnnotatedString {
                                append(stringResource(id = R.string.join_terms_agree))
                                withStyle(style = SpanStyle(GalapagosTheme.colors.PrimaryGreen)) {
                                    append(stringResource(id = R.string.join_required))
                                }
                            },
                            style = GalapagosTheme.typography.body1.copy(fontWeight = FontWeight.Normal),
                            color = GalapagosTheme.colors.FontBlack,
                            textDecoration = TextDecoration.Underline
                        )
                    },
                    onClick = {
                        viewModel.updateState(
                            uiState.copy(
                                termsAgreed = uiState.termsAgreed
                                    .toMutableList().apply { set(0, !uiState.termsAgreed[0]) }
                            )
                        )
                    }
                )
                CheckBoxItem(
                    checked = uiState.termsAgreed[1],
                    content = {
                        Text(
                            text = buildAnnotatedString {
                                append(stringResource(id = R.string.join_privacy_policy))
                                withStyle(style = SpanStyle(GalapagosTheme.colors.PrimaryGreen)) {
                                    append(stringResource(id = R.string.join_required))
                                }
                            },
                            style = GalapagosTheme.typography.body1.copy(fontWeight = FontWeight.Normal),
                            color = GalapagosTheme.colors.FontBlack,
                            textDecoration = TextDecoration.Underline
                        )
                    },
                    onClick = {
                        viewModel.updateState(
                            uiState.copy(
                                termsAgreed = uiState.termsAgreed
                                    .toMutableList().apply { set(1, !uiState.termsAgreed[1]) }
                            )
                        )
                    }
                )
                CheckBoxItem(
                    checked = uiState.termsAgreed[2],
                    content = {
                        Text(
                            text = buildAnnotatedString {
                                append(stringResource(id = R.string.join_ad_info_agree))
                                withStyle(style = SpanStyle(GalapagosTheme.colors.FontGray3)) {
                                    append(stringResource(id = R.string.join_optional))
                                }
                            },
                            style = GalapagosTheme.typography.body1.copy(fontWeight = FontWeight.Normal),
                            color = GalapagosTheme.colors.FontBlack,
                        )
                    },
                    onClick = {
                        viewModel.updateState(
                            uiState.copy(
                                termsAgreed = uiState.termsAgreed
                                    .toMutableList().apply { set(2, !uiState.termsAgreed[2]) }
                            )
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            GButton(
                modifier = Modifier.padding(bottom = 50.dp),
                buttonSize = ButtonSize.Height56,
                enabled = uiState.termsAgreed[0] && uiState.termsAgreed[1],
                content = stringResource(id = R.string.join_next),
                onClick = {
                    appState.navigate(
                        if (viewModel.socialType == "EMAIL") {
                            AuthDestinations.Join.EMAIL
                        } else {
                            AuthDestinations.Join.NICKNAME
                        }
                    )
                }
            )
        }
    }
}

@Composable
private fun CheckBoxItem(
    content: @Composable () -> Unit,
    checked: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CheckBox(
            checked = checked,
            onClick = { onClick() }
        )
        content()
    }
}

@Composable
private fun CheckBox(
    checked: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Spacer(
            modifier = Modifier
                .size(24.dp)
                .background(
                    if (checked) {
                        GalapagosTheme.colors.PrimaryGreen
                    } else {
                        Color(0xFFEFEFEF)
                    }
                )
        )
        Icon(
            painter = painterResource(id = com.busymodernpeople.core.design.R.drawable.ic_check),
            contentDescription = null,
            tint = if (checked) {
                GalapagosTheme.colors.FontWhite
            } else {
                GalapagosTheme.colors.FontGray4
            }
        )
    }
}