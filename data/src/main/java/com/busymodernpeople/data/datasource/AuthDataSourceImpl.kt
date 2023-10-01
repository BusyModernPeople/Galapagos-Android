package com.busymodernpeople.data.datasource

import com.busymodernpeople.data.network.model.request.ConfirmEmailRequest
import com.busymodernpeople.data.network.model.request.GoogleAccessTokenRequest
import com.busymodernpeople.data.network.model.request.SendEmailRequest
import com.busymodernpeople.data.network.service.AuthService
import com.busymodernpeople.data.network.service.SocialType
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

    override fun fetchGoogleAccessToken(
        googleAccessTokenRequest: GoogleAccessTokenRequest
    ) = flow {
        emit(authService.fetchGoogleAccessToken(googleAccessTokenRequest = googleAccessTokenRequest))
    }.flowOn(ioDispatcher)

    override fun socialLogin(
        socialType: SocialType,
        accessToken: String,
        deviceToken: String
    ) = flow {
        emit(authService.socialLogin(socialType, accessToken, deviceToken))
    }.flowOn(ioDispatcher)

}