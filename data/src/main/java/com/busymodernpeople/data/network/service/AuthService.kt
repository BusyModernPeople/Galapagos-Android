package com.busymodernpeople.data.network.service

import com.busymodernpeople.data.network.adapter.ApiResult
import com.busymodernpeople.data.network.model.request.ConfirmEmailRequest
import com.busymodernpeople.data.network.model.request.GoogleAccessTokenRequest
import com.busymodernpeople.data.network.model.request.JoinRequest
import com.busymodernpeople.data.network.model.request.SendEmailRequest
import com.busymodernpeople.data.network.model.response.ConfirmEmailResponse
import com.busymodernpeople.data.network.model.response.GoogleAccessTokenResponse
import com.busymodernpeople.data.network.model.response.JoinResponse
import com.busymodernpeople.data.network.model.response.SendEmailResponse
import com.busymodernpeople.data.network.model.response.SocialLoginResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

enum class SocialType {
    kakao, google, naver
}

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

    @FormUrlEncoded
    @POST("/users/auth/{socialType}/login")
    suspend fun socialLogin(
        @Path("socialType") socialType: SocialType,
        @Field("accessToken") accessToken: String,
        @Field("deviceToken") deviceToken: String
    ): ApiResult<SocialLoginResponse>

    @POST("/user/signup")
    suspend fun join(
        @Body joinRequest: JoinRequest
    ): ApiResult<JoinResponse>

}