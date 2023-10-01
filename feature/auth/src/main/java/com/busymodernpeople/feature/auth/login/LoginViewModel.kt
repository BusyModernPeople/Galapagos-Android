package com.busymodernpeople.feature.auth.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.busymodernpeople.core.common.base.BaseViewModel
import com.busymodernpeople.data.network.adapter.ApiResult
import com.busymodernpeople.data.repository.AuthRepository
import com.busymodernpeople.feature.auth.BuildConfig
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
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
                loginByKakao(event.context)
            }
            is LoginContract.Event.NaverLoginButtonClicked -> {
                loginByNaver(event.context)
            }
            is LoginContract.Event.GoogleLoginButtonClicked -> {
                postEffect(LoginContract.Effect.LaunchGoogleLogin)
            }
        }
    }

    private val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            viewModelScope.launch {
                error.printStackTrace()
                postEffect(LoginContract.Effect.ShowSnackBar("카카오톡으로 로그인 실패"))
            }
        } else if (token != null) {
            Log.d("kakao_token", token.accessToken)
        }
    }

    private val naverProfileCallback = object : NidProfileCallback<NidProfileResponse> {

        override fun onError(errorCode: Int, message: String) {
            postEffect(LoginContract.Effect.ShowSnackBar(message))
        }

        override fun onFailure(httpStatus: Int, message: String) {
            postEffect(LoginContract.Effect.ShowSnackBar(message))
        }

        override fun onSuccess(result: NidProfileResponse) {
            Log.d("naver_login_result", result.message.toString())
        }

    }

    private val naverLoginCallback = object : OAuthLoginCallback {

        override fun onError(errorCode: Int, message: String) {
            postEffect(LoginContract.Effect.ShowSnackBar(message))
        }

        override fun onFailure(httpStatus: Int, message: String) {
            postEffect(LoginContract.Effect.ShowSnackBar(message))
        }

        override fun onSuccess() {
            Log.d("naver_token", NaverIdLoginSDK.getAccessToken() ?: "")
            NidOAuthLogin().callProfileApi(naverProfileCallback)
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
            ).onStart {
                updateState(currentState.copy(isLoading = true))
            }.collect { result ->
                updateState(currentState.copy(isLoading = false))
                when (result) {
                    is ApiResult.Success -> {
                        Log.d("google_token", result.data.accessToken)
                    }
                    is ApiResult.NetworkError -> {

                    }
                    is ApiResult.ApiError -> {

                    }
                }
            }
        }
    }


    fun loginByKakao(context: Context) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    error.printStackTrace()
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        viewModelScope.launch {
                            postEffect(LoginContract.Effect.ShowSnackBar("카카오톡으로 로그인 실패"))
                        }
                        return@loginWithKakaoTalk
                    }
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoLoginCallback)
                } else if (token != null) {
                    Log.d("kakao_token", token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoLoginCallback)
        }
    }

    fun loginByNaver(context: Context) {
        NaverIdLoginSDK.authenticate(context, naverLoginCallback)
    }
}