package com.busymodernpeople.feature.auth.login

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.busymodernpeople.core.common.base.AuthDestinations
import com.busymodernpeople.core.common.base.BaseViewModel
import com.busymodernpeople.core.common.base.HomeDestinations
import com.busymodernpeople.data.network.adapter.ApiResult
import com.busymodernpeople.data.network.service.SocialType
import com.busymodernpeople.data.repository.AuthRepository
import com.busymodernpeople.data.repository.DataStoreRepository
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel<LoginContract.State, LoginContract.Event, LoginContract.Effect>(
    initialState = LoginContract.State()
) {

    override fun reduceState(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.KaKaoLoginButtonClicked -> {
                kakaoLogin(event.context)
            }
            is LoginContract.Event.NaverLoginButtonClicked -> {
                naverLogin(event.context)
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
            val fcmToken = runBlocking { dataStoreRepository.getFcmToken().first() }

            UserApiClient.instance.me { user, error ->
                if (error != null) {
                    postEffect(LoginContract.Effect.ShowSnackBar("$error"))
                } else if (user != null) {
                    val email = user.kakaoAccount?.email ?: ""
                    socialLogin(
                        socialType = SocialType.kakao,
                        email = email,
                        accessToken = token.accessToken,
                        deviceToken = fcmToken
                    )
                }
            }
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
            val accessToken = NaverIdLoginSDK.getAccessToken() ?: ""
            val fcmToken = runBlocking { dataStoreRepository.getFcmToken().first() }

            NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
                override fun onError(errorCode: Int, message: String) {
                    postEffect(LoginContract.Effect.ShowSnackBar("errorCode:$errorCode, errorMessage:$message"))
                }

                override fun onFailure(httpStatus: Int, message: String) {
                    postEffect(LoginContract.Effect.ShowSnackBar("httpStatus:$httpStatus, errorDesc:$message"))
                }

                override fun onSuccess(result: NidProfileResponse) {
                    val email = result.profile?.email ?: ""
                    socialLogin(
                        socialType = SocialType.naver,
                        email = email,
                        accessToken = accessToken,
                        deviceToken = fcmToken
                    )
                }
            })

        }

    }

    fun socialLogin(
        socialType: SocialType,
        email: String,
        accessToken: String,
        deviceToken: String
    ) {
        val type = when (socialType) {
            SocialType.kakao -> {
                "KAKAO"
            }
            SocialType.google -> {
                "GOOGLE"
            }
            SocialType.naver -> {
                "NAVER"
            }
        }

        viewModelScope.launch {
            authRepository.socialLogin(
                socialType = socialType,
                accessToken = accessToken,
                deviceToken = deviceToken
            ).onStart {
                updateState(currentState.copy(isLoading = true))
            }.collect { result ->
                updateState(currentState.copy(isLoading = true))
                when (result) {
                    is ApiResult.Success -> {
                        if (result.data.jwtToken != null) {
                            postEffect(
                                LoginContract.Effect.NavigateTo(
                                    HomeDestinations.ROUTE
                                )
                            )
                            dataStoreRepository.setJwtToken(result.data.jwtToken!!)
                        } else {
                            postEffect(
                                LoginContract.Effect.NavigateTo(
                                    "${AuthDestinations.Join.ROUTE}?socialType=$type&email=$email"
                                )
                            )
                        }
                    }

                    is ApiResult.ApiError -> {
                        postEffect(LoginContract.Effect.ShowSnackBar(result.message))
                    }

                    is ApiResult.NetworkError -> {
                        postEffect(LoginContract.Effect.ShowSnackBar("네트워크 에러가 발생했습니다."))
                    }
                }
            }
        }
    }

    fun emailLogin() = viewModelScope.launch {
        authRepository.emailLogin(
            email = currentState.email,
            password = currentState.password
        ).onStart {
            updateState(currentState.copy(isLoading = true))
        }.collect { result ->
            updateState(currentState.copy(isLoading = false))
            when (result) {
                is ApiResult.Success -> {
                    postEffect(LoginContract.Effect.NavigateTo(HomeDestinations.ROUTE))
                    updateState(currentState.copy(emailErrorState = false))
                    updateState(currentState.copy(passwordErrorState = false))
                }

                is ApiResult.ApiError -> {
                    postEffect(LoginContract.Effect.ShowSnackBar(result.message))
                    updateState(currentState.copy(emailErrorState = true))
                    updateState(currentState.copy(passwordErrorState = true))
                }

                is ApiResult.NetworkError -> {
                    postEffect(LoginContract.Effect.ShowSnackBar("네트워크 에러가 발생했습니다."))
                }
            }
        }
    }

    fun googleLogin(
        clientId: String = BuildConfig.GOOGLE_OAUTH_CLIENT_ID,
        clientSecret: String = BuildConfig.GOOGLE_OAUTH_CLIENT_SECRET,
        code: String,
        idToken: String,
        email: String
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
                        val fcmToken = runBlocking { dataStoreRepository.getFcmToken().first() }

                        socialLogin(
                            socialType = SocialType.google,
                            email = email,
                            accessToken = result.data.accessToken,
                            deviceToken = fcmToken
                        )
                    }

                    is ApiResult.ApiError -> {
                        postEffect(LoginContract.Effect.ShowSnackBar(result.message))
                    }

                    is ApiResult.NetworkError -> {
                        postEffect(LoginContract.Effect.ShowSnackBar("네트워크 에러가 발생했습니다."))
                    }
                }
            }
        }
    }


    fun kakaoLogin(context: Context) {
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
                    val fcmToken = runBlocking { dataStoreRepository.getFcmToken().first() }

                    UserApiClient.instance.me { user, error ->
                        if (error != null) {
                            postEffect(LoginContract.Effect.ShowSnackBar("$error"))
                        } else if (user != null) {
                            val email = user.kakaoAccount?.email ?: ""
                            socialLogin(
                                socialType = SocialType.kakao,
                                email = email,
                                accessToken = token.accessToken,
                                deviceToken = fcmToken
                            )
                        }
                    }
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoLoginCallback)
        }
    }

    fun naverLogin(context: Context) {
        NaverIdLoginSDK.authenticate(context, naverLoginCallback)
    }
}