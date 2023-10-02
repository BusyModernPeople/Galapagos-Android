package com.busymodernpeople.data.network.model.response

import com.google.gson.annotations.SerializedName

data class JoinResponse(
    @SerializedName("userIdx")
    val userIdx: Long,
    @SerializedName("nickName")
    val nickname: String,
    @SerializedName("jwt")
    val jwt: String
)
