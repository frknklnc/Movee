package com.example.movee.data.remote.model.favourite


import com.google.gson.annotations.SerializedName

data class FavouriteRequest(
    @SerializedName("favorite")
    val favorite: Boolean,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("media_id")
    val mediaId: Int
)