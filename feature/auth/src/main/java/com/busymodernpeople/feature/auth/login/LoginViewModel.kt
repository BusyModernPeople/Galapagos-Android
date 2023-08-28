package com.busymodernpeople.feature.auth.login

import androidx.lifecycle.viewModelScope
import com.busymodernpeople.core.common.base.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch


@HiltViewModel
class LoginViewModel : BaseViewModel<LoginContract.State, LoginContract.Event, LoginContract.Effect>(
    initialState = LoginContract.State()
) {
    override fun reduceState(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.KaKaoLoginButtonClicked -> {
                loginByKakao()
            }
            is LoginContract.Event.NaverLoginButtonClicked -> {
                updateState(currentState.copy(error = "미구현"))
            }
            is LoginContract.Event.GoogleLoginButtonClicked -> {
                updateState(currentState.copy(error = "미구현"))
            }
        }
    }

    fun loginByGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("server_client_id")
            .requestEmail()
            .build()


    }

    fun loginByKakao() {
        viewModelScope.launch {
             postEffect(LoginContract.Effect.NavigateToHome)
        }
    }
}