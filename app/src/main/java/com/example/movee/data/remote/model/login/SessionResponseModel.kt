package com.example.movee.data.remote.model.login

import com.google.gson.annotations.SerializedName

data class SessionResponseModel(
    @SerializedName("success") val success: Boolean,
    @SerializedName("session_id") val sessionId: String
)