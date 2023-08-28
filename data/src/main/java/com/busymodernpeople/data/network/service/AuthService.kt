package com.busymodernpeople.data.network.service

import com.busymodernpeople.data.network.adapter.ApiResult
import com.busymodernpeople.data.network.model.request.ConfirmEmailRequest
import com.busymodernpeople.data.network.model.request.GoogleAccessTokenRequest
import com.busymodernpeople.data.network.model.request.SendEmailRequest
import com.busymodernpeople.data.network.model.request.SocialLoginRequest
import com.busymodernpeople.data.network.model.response.ConfirmEmailResponse
import com.busymodernpeople.data.network.model.response.GoogleAccessTokenResponse
import com.busymodernpeople.data.network.model.response.SendEmailResponse
import com.busymodernpeople.data.network.model.response.SocialLoginResponse
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
    suspend fun fetchGoogleAccessToken(
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