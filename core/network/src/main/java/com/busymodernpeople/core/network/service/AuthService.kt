package com.busymodernpeople.core.network.service

import com.busymodernpeople.core.network.adapter.ApiResult
import com.busymodernpeople.core.network.model.request.ConfirmEmailRequest
import com.busymodernpeople.core.network.model.request.SendEmailRequest
import com.busymodernpeople.core.network.model.response.ConfirmEmailResponse
import com.busymodernpeople.core.network.model.response.SendEmailResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/email/confirm")
    suspend fun confirmEmail(
        @Body confirmEmailRequest: ConfirmEmailRequest
    ) : ApiResult<ConfirmEmailResponse>

    @POST("/email/send-code")
    suspend fun sendEmail(
        @Body sendEmailRequest: SendEmailRequest
    ) : ApiResult<SendEmailResponse>

}