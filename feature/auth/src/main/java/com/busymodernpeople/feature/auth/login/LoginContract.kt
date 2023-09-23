package com.busymodernpeople.feature.auth.login

import android.content.Context
import androidx.navigation.NavOptions
import com.busymodernpeople.core.common.base.UiEffect
import com.busymodernpeople.core.common.base.UiEvent
import com.busymodernpeople.core.common.base.UiState

class LoginContract {
    data class State(
        val isLoading: Boolean = false
    ) : UiState

    sealed class Event : UiEvent {
        data class KaKaoLoginButtonClicked(val context: Context) : Event()
        data class NaverLoginButtonClicked(val context: Context) : Event()
        object GoogleLoginButtonClicked : Event()
    }

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
        data class ShowSnackBar(val message: String) : Effect()
        object LaunchGoogleLogin : Effect()
    }
}