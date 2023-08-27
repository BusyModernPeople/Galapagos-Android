package com.busymodernpeople.feature.auth.login

import androidx.lifecycle.viewModelScope
import com.busymodernpeople.core.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel : BaseViewModel<LoginContract.State, LoginContract.Event, LoginContract.Effect>(
    initialState = LoginContract.State()
) {
    override fun reduceState(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.KaKaoLoginButtonClicked -> {
                kakaoLogin()
            }
            is LoginContract.Event.NaverLoginButtonClicked -> {
                updateState(currentState.copy(error = "미구현"))
            }
            is LoginContract.Event.GoogleLoginButtonClicked -> {
                updateState(currentState.copy(error = "미구현"))
            }
        }
    }

    fun kakaoLogin() {
        viewModelScope.launch {
             postEffect(LoginContract.Effect.NavigateToHome)
        }
    }
}