package com.busymodernpeople.data.network.model.response

import com.google.gson.annotations.SerializedName

data class ConfirmEmailResponse(
    @SerializedName("message")
    val message: String
)
