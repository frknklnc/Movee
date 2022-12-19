package com.example.movee.data.remote.service

import com.example.movee.data.remote.model.account.AccountResponse
import com.example.movee.data.remote.model.favourite.FavouriteMovieResponse
import com.example.movee.data.remote.model.favourite.FavouriteRequest
import com.example.movee.data.remote.model.favourite.FavouriteResponse
import com.example.movee.util.api.HEADER_REQUIRE_SESSION_TOKEN
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface AccountService {


    //Account
    @GET(ACCOUNT_DETAIL)
    @Headers(HEADER_REQUIRE_SESSION_TOKEN)
    suspend fun getAccountDetail(): Response<AccountResponse>

    //Favourite
    @GET(FAVOURITE_MOVIE)
    @Headers(HEADER_REQUIRE_SESSION_TOKEN)
    suspend fun getFavouriteMovie(
        @Path("account_id") accountId: Int
    ): Response<FavouriteMovieResponse>

    @GET(ADD_FAVOURITE)
    @Headers(HEADER_REQUIRE_SESSION_TOKEN)
    suspend fun addFavourite(
        @Path("account_id") accountId: Int,
        @Body favouriteRequest: FavouriteRequest
    ): Response<FavouriteResponse>


    companion object {

        //Account
        const val ACCOUNT_DETAIL = "account"

        //Favourite
        const val FAVOURITE_MOVIE = "account/{account_id}/favorite/movies"
        const val ADD_FAVOURITE = "account/{account_id}/favorite"

    }
}