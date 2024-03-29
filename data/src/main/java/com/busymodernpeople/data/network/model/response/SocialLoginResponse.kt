package com.busymodernpeople.data.network.model.response

import com.google.gson.annotations.SerializedName

data class SocialLoginResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("nickName")
    val nickName: String,
    @SerializedName("user")
    val user: UserResponse?,  // TODO: User 데이터 클래스 정의 후 수정
    @SerializedName("jwtToken")
    val jwtToken: String?
)
