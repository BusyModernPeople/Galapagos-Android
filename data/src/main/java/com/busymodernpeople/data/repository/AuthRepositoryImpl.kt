package com.busymodernpeople.data.repository

import com.busymodernpeople.data.datasource.AuthDataSource
import com.busymodernpeople.data.network.model.request.ConfirmEmailRequest
import com.busymodernpeople.data.network.model.request.GoogleAccessTokenRequest
import com.busymodernpeople.data.network.model.request.SendEmailRequest
import com.busymodernpeople.data.network.model.request.SocialLoginRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override fun confirmEmail(
        email: String,
        code: String
    ) = authDataSource.confirmEmail(
        ConfirmEmailRequest(
            email = email,
            code = code
        )
    )

    override fun sendEmail(
        email: String
    ) = authDataSource.sendEmail(
        SendEmailRequest(email = email)
    )

    override fun fetchGoogleAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        idToken: String
    ) = authDataSource.fetchGoogleAccessToken(
        GoogleAccessTokenRequest(
            grantType = "authorization_code",
            clientId = clientId,
            clientSecret = clientSecret,
            redirectUri = "",
            code = code,
            idToken = idToken
        )
    )

    override fun loginByGoogle(
        accessToken: String,
        deviceToken: String
    ) = authDataSource.loginByGoogle(
        SocialLoginRequest(
            accessToken = accessToken,
            deviceToken = deviceToken
        )
    )

    override fun loginByKakao(
        accessToken: String,
        deviceToken: String
    ) = authDataSource.loginByKakao(
        SocialLoginRequest(
            accessToken = accessToken,
            deviceToken = deviceToken
        )
    )

}