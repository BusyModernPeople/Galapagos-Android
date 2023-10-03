package com.busymodernpeople.data.repository

import com.busymodernpeople.data.datasource.AuthDataSource
import com.busymodernpeople.data.network.model.request.ConfirmEmailRequest
import com.busymodernpeople.data.network.model.request.EmailLoginRequest
import com.busymodernpeople.data.network.model.request.GoogleAccessTokenRequest
import com.busymodernpeople.data.network.model.request.JoinRequest
import com.busymodernpeople.data.network.model.request.SendEmailRequest
import com.busymodernpeople.data.network.service.SocialType
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,

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

    override fun socialLogin(
        socialType: SocialType,
        accessToken: String,
        deviceToken: String
    ) = authDataSource.socialLogin(
        socialType = socialType,
        accessToken = accessToken,
        deviceToken = deviceToken
    )

    override fun emailLogin(
        email: String,
        password: String
    ) = authDataSource.emailLogin(
        EmailLoginRequest(
            email = email,
            password = password
        )
    )

    override fun join(
        email: String,
        password: String?,
        nickname: String,
        socialType: String
    ) = authDataSource.join(
        JoinRequest(
            email = email,
            password = password,
            nickname = nickname,
            socialType = socialType
        )
    )

}