package com.busymodernpeople.data.datasource

import com.busymodernpeople.data.network.adapter.ApiResult
import com.busymodernpeople.data.network.model.request.ConfirmEmailRequest
import com.busymodernpeople.data.network.model.request.GoogleAccessTokenRequest
import com.busymodernpeople.data.network.model.request.SendEmailRequest
import com.busymodernpeople.data.network.model.request.SocialLoginRequest
import com.busymodernpeople.data.network.model.response.ConfirmEmailResponse
import com.busymodernpeople.data.network.model.response.GoogleAccessTokenResponse
import com.busymodernpeople.data.network.model.response.SendEmailResponse
import com.busymodernpeople.data.network.model.response.SocialLoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {

    fun confirmEmail(
        confirmEmailRequest: ConfirmEmailRequest
    ): Flow<ApiResult<ConfirmEmailResponse>>

    fun sendEmail(
        sendEmailRequest: SendEmailRequest
    ): Flow<ApiResult<SendEmailResponse>>

    fun fetchGoogleAccessToken(
        googleAccessTokenRequest: GoogleAccessTokenRequest
    ): Flow<ApiResult<GoogleAccessTokenResponse>>

    fun loginByGoogle(
        socialLoginRequest: SocialLoginRequest
    ): Flow<ApiResult<SocialLoginResponse>>

    fun loginByKakao(
        socialLoginRequest: SocialLoginRequest
    ): Flow<ApiResult<SocialLoginResponse>>

}