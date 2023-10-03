package com.busymodernpeople.data.network.model.request

import com.google.gson.annotations.SerializedName

data class JoinRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String?,
    @SerializedName("nickName")
    val nickname: String,
    @SerializedName("socialType")
    val socialType: String
)
