package com.busymodernpeople.core.network.service

import com.busymodernpeople.core.network.adapter.ApiResult
import com.busymodernpeople.core.network.model.request.ConfirmEmailRequest
import com.busymodernpeople.core.network.model.request.GoogleAccessTokenRequest
import com.busymodernpeople.core.network.model.request.SendEmailRequest
import com.busymodernpeople.core.network.model.request.SocialLoginRequest
import com.busymodernpeople.core.network.model.response.ConfirmEmailResponse
import com.busymodernpeople.core.network.model.response.GoogleAccessTokenResponse
import com.busymodernpeople.core.network.model.response.SendEmailResponse
import com.busymodernpeople.core.network.model.response.SocialLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface AuthService {

    @POST("/email/confirm")
    suspend fun confirmEmail(
        @Body confirmEmailRequest: ConfirmEmailRequest
    ): ApiResult<ConfirmEmailResponse>

    @POST("/email/send-code")
    suspend fun sendEmail(
        @Body sendEmailRequest: SendEmailRequest
    ): ApiResult<SendEmailResponse>

    @POST
    suspend fun getGoogleAccessToken(
        @Url url: String = "https://www.googleapis.com/oauth2/v4/token",
        @Body googleAccessTokenRequest: GoogleAccessTokenRequest
    ): ApiResult<GoogleAccessTokenResponse>

    @POST("/users/auth/google/login")
    suspend fun loginByGoogle(
        @Body socialLoginRequest: SocialLoginRequest
    ): ApiResult<SocialLoginResponse>

    @POST("/users/auth/kakao/login")
    suspend fun loginByKakao(
        @Body socialLoginRequest: SocialLoginRequest
    ): ApiResult<SocialLoginResponse>

}