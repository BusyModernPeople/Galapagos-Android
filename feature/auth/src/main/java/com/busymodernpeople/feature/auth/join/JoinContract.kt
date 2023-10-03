package com.busymodernpeople.feature.auth.join

import androidx.navigation.NavOptions
import com.busymodernpeople.core.common.base.UiEffect
import com.busymodernpeople.core.common.base.UiEvent
import com.busymodernpeople.core.common.base.UiState

class JoinContract {

    data class State(
        val isLoading: Boolean = false,
        val termsAgreed: List<Boolean> = listOf(false, false, false),
        val email: String = "",
        val emailErrorState: Boolean = false,
        val authCode: String = "",
        val authCodeErrorState: Boolean = false,
        val isSentAuthCode: Boolean = false,
        val isAuthenticated: Boolean = false,
        val isTimerRunning: Boolean = true,
        val remainingTime: Int = 180,
        val password: String? = null,
        val passwordConditions: List<Boolean> = listOf(false, false, false, false),
        val confirmPassword: String = "",
        val nickname: String = ""
    ) : UiState

    sealed class Event : UiEvent {
        object SendAuthCode : Event()
        object AuthenticateCode : Event()
        object Join : Event()
    }

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()

        data class ShowSnackBar(val message: String) : Effect()
    }

}