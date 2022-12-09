package com.example.movee.data.remote.model.login

import com.google.gson.annotations.SerializedName

data class LoginRequestModel(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("request_token") val requestToken: String
)