package com.busymodernpeople.feature.login

import com.busymodernpeople.core.common.base.UiEffect
import com.busymodernpeople.core.common.base.UiEvent
import com.busymodernpeople.core.common.base.UiState

class LoginContract {
    data class State(
        val isLoading: Boolean = false,
        val lastLoginMethod: String? = null,
        val error: String? = null
    ) : UiState

    sealed class Event : UiEvent {
        object KaKaoLoginButtonClicked : Event()
        object NaverLoginButtonClicked : Event()
        object GoogleLoginButtonClicked : Event()
    }

    sealed class Effect : UiEffect {
        object NavigateToHome : Effect()
        data class ShowSnackbar(val message: String) : Effect()
    }
}