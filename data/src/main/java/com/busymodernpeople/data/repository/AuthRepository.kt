package com.busymodernpeople.data.repository

import com.busymodernpeople.data.network.adapter.ApiResult
import com.busymodernpeople.data.network.model.response.ConfirmEmailResponse
import com.busymodernpeople.data.network.model.response.GoogleAccessTokenResponse
import com.busymodernpeople.data.network.model.response.SendEmailResponse
import com.busymodernpeople.data.network.model.response.SocialLoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun confirmEmail(
        email: String,
        code: String
    ): Flow<ApiResult<ConfirmEmailResponse>>

    fun sendEmail(
        email: String
    ): Flow<ApiResult<SendEmailResponse>>

    fun fetchGoogleAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        idToken: String
    ): Flow<ApiResult<GoogleAccessTokenResponse>>

    fun loginByGoogle(
        accessToken: String,
        deviceToken: String
    ): Flow<ApiResult<SocialLoginResponse>>

    fun loginByKakao(
        accessToken: String,
        deviceToken: String
    ): Flow<ApiResult<SocialLoginResponse>>

}