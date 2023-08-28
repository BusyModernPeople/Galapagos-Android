package com.busymodernpeople.data.network.model.request

import com.google.gson.annotations.SerializedName

data class ConfirmEmailRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("code")
    val code: String
)