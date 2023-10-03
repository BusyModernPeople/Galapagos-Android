package com.busymodernpeople.data.network.model.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("errorCode")
    val errorCode: Int,
    @SerializedName("errorMessages")
    val errorMessage: String
)
