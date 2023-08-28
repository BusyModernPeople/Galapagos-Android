package com.busymodernpeople.data.datasource

import com.busymodernpeople.core.network.adapter.ApiResult
import com.busymodernpeople.core.network.model.request.ConfirmEmailRequest
import com.busymodernpeople.core.network.model.request.GoogleAccessTokenRequest
import com.busymodernpeople.core.network.model.request.SendEmailRequest
import com.busymodernpeople.core.network.model.request.SocialLoginRequest
import com.busymodernpeople.core.network.model.response.ConfirmEmailResponse
import com.busymodernpeople.core.network.model.response.GoogleAccessTokenResponse
import com.busymodernpeople.core.network.model.response.SendEmailResponse
import com.busymodernpeople.core.network.model.response.SocialLoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {

    fun confirmEmail(
        confirmEmailRequest: ConfirmEmailRequest
    ): Flow<ApiResult<ConfirmEmailResponse>>

    fun sendEmail(
        sendEmailRequest: SendEmailRequest
    ): Flow<ApiResult<SendEmailResponse>>

    fun getGoogleAccessToken(
        googleAccessTokenRequest: GoogleAccessTokenRequest
    ): Flow<ApiResult<GoogleAccessTokenResponse>>

    fun loginByGoogle(
        socialLoginRequest: SocialLoginRequest
    ): Flow<ApiResult<SocialLoginResponse>>

    fun loginByKakao(
        socialLoginRequest: SocialLoginRequest
    ): Flow<ApiResult<SocialLoginResponse>>

}