package com.busymodernpeople.data.network.model.request

import com.google.gson.annotations.SerializedName

data class EmailLoginRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
