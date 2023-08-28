package com.busymodernpeople.data.datasource

import com.busymodernpeople.core.network.model.request.ConfirmEmailRequest
import com.busymodernpeople.core.network.model.request.GoogleAccessTokenRequest
import com.busymodernpeople.core.network.model.request.SendEmailRequest
import com.busymodernpeople.core.network.model.request.SocialLoginRequest
import com.busymodernpeople.core.network.service.AuthService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthDataSource {

    override fun confirmEmail(
        confirmEmailRequest: ConfirmEmailRequest
    ) = flow {
        emit(authService.confirmEmail(confirmEmailRequest))
    }.flowOn(ioDispatcher)

    override fun sendEmail(
        sendEmailRequest: SendEmailRequest
    ) = flow {
        emit(authService.sendEmail(sendEmailRequest))
    }.flowOn(ioDispatcher)

    override fun getGoogleAccessToken(
        googleAccessTokenRequest: GoogleAccessTokenRequest
    ) = flow {
        emit(authService.getGoogleAccessToken(googleAccessTokenRequest = googleAccessTokenRequest))
    }.flowOn(ioDispatcher)

    override fun loginByGoogle(
        socialLoginRequest: SocialLoginRequest
    ) = flow {
        emit(authService.loginByGoogle(socialLoginRequest))
    }.flowOn(ioDispatcher)

    override fun loginByKakao(
        socialLoginRequest: SocialLoginRequest
    ) = flow {
        emit(authService.loginByKakao(socialLoginRequest))
    }.flowOn(ioDispatcher)

}