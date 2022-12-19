package com.example.movee.data.remote.service

import com.example.movee.data.remote.model.CreditsResponse
import com.example.movee.data.remote.model.SearchResponse
import com.example.movee.data.remote.model.account.AccountResponse
import com.example.movee.data.remote.model.actor.CastCreditResponse
import com.example.movee.data.remote.model.actor.CastDetailResponse
import com.example.movee.data.remote.model.favourite.FavouriteMovieResponse
import com.example.movee.data.remote.model.favourite.FavouriteRequest
import com.example.movee.data.remote.model.favourite.FavouriteResponse
import com.example.movee.data.remote.model.movie.MovieDetailResponse
import com.example.movee.data.remote.model.movie.NowPlayingMovieResponse
import com.example.movee.data.remote.model.movie.PopularMovieResponse
import com.example.movee.data.remote.model.tv.PopularTvResponse
import com.example.movee.data.remote.model.tv.TopRatedTvResponse
import com.example.movee.data.remote.model.tv.TvDetailResponse
import com.example.movee.util.api.HEADER_REQUIRE_SESSION_TOKEN
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    //Movie
    @GET(POPULAR_MOVIE)
    suspend fun popularMovies(
        @Query("page") page: Int = 1
    ): Response<PopularMovieResponse>

    @GET(NOW_PLAYING_MOVIE)
    suspend fun nowPlayingMovies(): Response<NowPlayingMovieResponse>

    @GET(MOVIE_DETAIL)
    suspend fun movieDetails(
        @Path("movie_id") movieId: Int): Response<MovieDetailResponse>

    @GET(MOVIE_CREDITS)
    suspend fun movieCredits(
        @Path("movie_id") movieId: Int): Response<CreditsResponse>

    //Tv
    @GET(POPULAR_TV)
    suspend fun popularTv(): Response<PopularTvResponse>

    @GET(TOP_RATED_TV)
    suspend fun topRatedTv(
        @Query("page") page: Int = 1
    ): Response<TopRatedTvResponse>

    @GET(TV_DETAIL)
    suspend fun tvDetails(
        @Path("tv_id") tvSeriesId: Int): Response<TvDetailResponse>

    @GET(TV_CREDITS)
    suspend fun tvCredits(
        @Path("tv_id") tvSeriesId: Int): Response<CreditsResponse>

    //Cast
    @GET(ACTOR_DETAIL)
    suspend fun actorDetail(
        @Path("person_id") castId: Int): Response<CastDetailResponse>


    @GET(ACTOR_CREDIT)
    suspend fun actorCredit(
        @Path("person_id") castId: Int): Response<CastCreditResponse>

    //Search
    @GET(SEARCH)
    suspend fun search(
        @Query("query") query: String): Response<SearchResponse>

    //Account
    @GET(ACCOUNT_DETAIL)
    @Headers(HEADER_REQUIRE_SESSION_TOKEN)
    suspend fun getAccountDetail() : Response<AccountResponse>

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

        //Movie
        const val POPULAR_MOVIE = "movie/popular"
        const val NOW_PLAYING_MOVIE = "movie/now_playing"
        const val MOVIE_DETAIL = "movie/{movie_id}"
        const val MOVIE_CREDITS = "movie/{movie_id}/credits"

        //Tv
        const val POPULAR_TV = "tv/popular"
        const val TOP_RATED_TV = "tv/top_rated"
        const val TV_DETAIL = "tv/{tv_id}"
        const val TV_CREDITS = "tv/{tv_id}/credits"

        //Cast
        const val ACTOR_DETAIL = "person/{person_id}"
        const val ACTOR_CREDIT = "person/{person_id}/combined_credits"

        //Search
        const val SEARCH = "search/multi"

        //Account
        const val ACCOUNT_DETAIL = "account"

        //Favourite
        const val FAVOURITE_MOVIE = "account/{account_id}/favorite/movies"
        const val ADD_FAVOURITE = "account/{account_id}/favorite"

    }

}