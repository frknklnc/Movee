package com.example.movee.data.remote.model.favourite


import com.google.gson.annotations.SerializedName

data class FavouriteResponse(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String
)