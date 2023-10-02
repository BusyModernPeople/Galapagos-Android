package com.busymodernpeople.data.network.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("userIdx")
    val userIdx: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("nickName")
    val nickname: String,
    @SerializedName("profile")
    val profile: String
)
