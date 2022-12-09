package com.example.movee.data.remote.model.account


import com.example.movee.uimodels.account.AccountUiModel
import com.google.gson.annotations.SerializedName

data class AccountResponse(
    @SerializedName("avatar")
    val avatar: Avatar,
    @SerializedName("id")
    val id: Int,
    @SerializedName("include_adult")
    val includeAdult: Boolean,
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String
) {
    data class Avatar(
        @SerializedName("gravatar")
        val gravatar: Gravatar
    ) {
        data class Gravatar(
            @SerializedName("hash")
            val hash: String
        )
    }

    fun toUiModel() = AccountUiModel(
        username = username,
        accountId = id,
        country = iso31661

    )
}