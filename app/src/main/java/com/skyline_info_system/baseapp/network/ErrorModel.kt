package com.skyline_info_system.baseapp.network

import com.google.gson.annotations.SerializedName

data class ErrorModel(
    @SerializedName("status") val status: Int?,
    @SerializedName("message") val message: String?,
)