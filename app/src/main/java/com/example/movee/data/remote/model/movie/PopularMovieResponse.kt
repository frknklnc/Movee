package com.example.movee.data.remote.model.movie

import com.example.movee.uimodels.movie.PopularMovieUiModel
import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(
    @SerializedName ("page") val page: Int,
    @SerializedName ("results") val movies: List<PopularMovies>,
    @SerializedName ("total_pages") val totalPages: Int,
    @SerializedName ("total_results") val total_results: Int
){
    data class PopularMovies(
        @SerializedName ("adult") val adult: Boolean,
        @SerializedName ("backdrop_path") val backdropPath: String,
        @SerializedName ("genre_ids") val genreIds: List<Int>,
        @SerializedName ("id") val movieId: Int,
        @SerializedName ("original_language") val originalLanguage: String,
        @SerializedName ("original_title") val originalTitle: String,
        @SerializedName ("overview") val overview: String,
        @SerializedName ("popularity") val popularity: Double,
        @SerializedName ("poster_path") val posterPath: String?,
        @SerializedName ("release_date") val releaseDate: String?,
        @SerializedName ("title") val title: String,
        @SerializedName ("video") val video: Boolean,
        @SerializedName ("vote_average") val voteAverage: Double?,
        @SerializedName ("vote_count") val voteCount: Int
    ) {
        fun toUiModel() = PopularMovieUiModel(
            movieId = movieId,
            title = title,
            posterPath = posterPath,
            releaseDate = releaseDate,
            voteAverage = voteAverage
        )
    }
}