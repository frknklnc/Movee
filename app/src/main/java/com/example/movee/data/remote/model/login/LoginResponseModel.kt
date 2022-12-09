package com.example.movee.data.remote.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("success") val success: Boolean,
    @SerializedName("request_token") val requestToken: String
)