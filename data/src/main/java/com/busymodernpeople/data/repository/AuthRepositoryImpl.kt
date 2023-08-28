package com.busymodernpeople.data.repository

import com.busymodernpeople.core.network.model.request.ConfirmEmailRequest
import com.busymodernpeople.core.network.model.request.GoogleAccessTokenRequest
import com.busymodernpeople.core.network.model.request.SendEmailRequest
import com.busymodernpeople.core.network.model.request.SocialLoginRequest
import com.busymodernpeople.data.datasource.AuthDataSource
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

    override fun getGoogleAccessToken(
        grantType: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        code: String,
        idToken: String
    ) = authDataSource.getGoogleAccessToken(
        GoogleAccessTokenRequest(
            grantType = grantType,
            clientId = clientId,
            clientSecret = clientSecret,
            redirectUri = redirectUri,
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