package com.busymodernpeople.feature.auth.login

import androidx.lifecycle.viewModelScope
import com.busymodernpeople.core.common.base.BaseViewModel
import com.busymodernpeople.data.network.adapter.ApiResult
import com.busymodernpeople.data.repository.AuthRepository
import com.busymodernpeople.feature.auth.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<LoginContract.State, LoginContract.Event, LoginContract.Effect>(
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

            }
        }
    }

    fun loginByGoogle(
        accessToken: String,
        deviceToken: String
    ) {
        viewModelScope.launch {
            authRepository.loginByGoogle(
                accessToken = accessToken,
                deviceToken = deviceToken
            ).onStart {
                currentState.copy(isLoading = true)
            }.collect { result ->
                currentState.copy(isLoading = true)
                when (result) {
                    is ApiResult.Success -> {

                    }
                    is ApiResult.NetworkError -> {

                    }
                    is ApiResult.ApiError -> {

                    }
                }
            }
        }
    }

    fun fetchGoogleAccessToken(
        clientId: String = BuildConfig.GOOGLE_OAUTH_CLIENT_ID,
        clientSecret: String = BuildConfig.GOOGLE_OAUTH_CLIENT_SECRET,
        code: String,
        idToken: String
    ) {
        viewModelScope.launch {
            authRepository.fetchGoogleAccessToken(
                clientId = clientId,
                clientSecret = clientSecret,
                code = code,
                idToken = idToken
            )
        }
    }


    fun loginByKakao() {
        viewModelScope.launch {
             postEffect(LoginContract.Effect.NavigateToHome)
        }
    }
}