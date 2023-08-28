package com.busymodernpeople.data.network.model.request

import com.google.gson.annotations.SerializedName

data class SocialLoginRequest(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("deviceToken")
    val deviceToken: String
)
