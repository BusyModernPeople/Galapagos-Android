package com.busymodernpeople.core.network.model.response

import com.google.gson.annotations.SerializedName

data class SendEmailResponse(
    @SerializedName("message")
    val message: String
)
