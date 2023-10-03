package com.busymodernpeople.feature.auth.join

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.core.common.base.BaseViewModel
import com.busymodernpeople.data.network.adapter.ApiResult
import com.busymodernpeople.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    savedStateBundle: SavedStateHandle
) : BaseViewModel<JoinContract.State, JoinContract.Event, JoinContract.Effect>(
    initialState = JoinContract.State()
) {
    val socialType: String = savedStateBundle["socialType"] ?: "EMAIL"
    val email: String = savedStateBundle["email"] ?: ""

    init {
        updateState(currentState.copy(email = email))
    }

    override fun reduceState(event: JoinContract.Event) {
        when (event) {
            is JoinContract.Event.SendAuthCode -> {
                sendAuthCode()
            }

            is JoinContract.Event.AuthenticateCode -> {
                authenticateCode()
            }

            is JoinContract.Event.Join -> {
                join()
            }
        }
    }

    private fun sendAuthCode() = viewModelScope.launch {
        authRepository.sendEmail(
            currentState.email ?: ""
        ).onStart { 
            updateState(currentState.copy(isLoading = true))
        }.collect { result ->
            updateState(currentState.copy(isLoading = false))
            when (result) {
                is ApiResult.Success -> {
                    updateState(
                        currentState.copy(
                            isSentAuthCode = true,
                            emailErrorState = false
                        )
                    )
                    resetTimer()
                }

                is ApiResult.ApiError -> {
                    updateState(currentState.copy(emailErrorState = true))
                    postEffect(JoinContract.Effect.ShowSnackBar(result.message))
                }

                else -> {
                    postEffect(JoinContract.Effect.ShowSnackBar("네트워크 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun resetTimer() = viewModelScope.launch {
        updateState(
            currentState.copy(
                isTimerRunning = true,
                remainingTime = 180
            )
        )

        while (currentState.isTimerRunning && currentState.remainingTime > 0) {
            delay(1000)
            val remainingTime = currentState.remainingTime
            updateState(currentState.copy(remainingTime = remainingTime - 1))
        }

        updateState(currentState.copy(isTimerRunning = false))
    }

    private fun authenticateCode() = viewModelScope.launch {
        authRepository.confirmEmail(
            currentState.email ?: "",
            currentState.authCode
        ).onStart {
            updateState(currentState.copy(isLoading = true))
        }.collect { result ->
            updateState(currentState.copy(isLoading = false))
            when (result) {
                is ApiResult.Success -> {
                    updateState(
                        currentState.copy(
                            isAuthenticated = true,
                            authCodeErrorState = false
                        )
                    )
                }

                is ApiResult.ApiError -> {
                    updateState(currentState.copy(authCodeErrorState = true))
                    postEffect(JoinContract.Effect.ShowSnackBar(result.message))
                }

                else -> {
                    postEffect(JoinContract.Effect.ShowSnackBar("네트워크 에러가 발생했습니다."))
                }
            }
        }
    }

    fun checkPassword() {
        val regExp = listOf(
            "^(?=.*[a-zA-Z]).+",
            "^(?=.*[0-9]).+",
            """^(?=.*[-+_!@#\$%^&*., ?]).+""",
            "^.{8,20}$"
        )
        val conditions = mutableListOf(false, false, false, false)
        val password = currentState.password ?: ""

        for (i: Int in conditions.indices) {
            conditions[i] = password.matches(regExp[i].toRegex())
        }

        updateState(currentState.copy(passwordConditions = conditions))
    }

    private fun join() = viewModelScope.launch {
        authRepository.join(
            currentState.email,
            currentState.password,
            currentState.nickname,
            socialType
        ).onStart {
            updateState(currentState.copy(isLoading = true))
        }.collect { result ->
            updateState(currentState.copy(isLoading = false))
            when (result) {
                is ApiResult.Success -> {
                    postEffect(
                        JoinContract.Effect.NavigateTo(
                            destination = AuthDestinations.Join.COMPLETE,
                            navOptions = navOptions {
                                popUpTo(AuthDestinations.Login.LOGIN)
                            }
                        )
                    )
                }

                is ApiResult.ApiError -> {
                    postEffect(JoinContract.Effect.ShowSnackBar(result.message))
                }

                else -> {
                    postEffect(JoinContract.Effect.ShowSnackBar("네트워크 에러가 발생했습니다."))
                }
            }
        }
    }

}