package com.example.movee.data.remote.model


import com.example.movee.R
import com.example.movee.navigation.Route
import com.example.movee.uimodels.SearchUiModel
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    data class Result(
        @SerializedName("adult")
        val adult: Boolean?,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("first_air_date")
        val firstAirDate: String?,
        @SerializedName("genre_ids")
        val genreİds: List<Int>?,
        @SerializedName("id")
        val id: Int,
        @SerializedName("known_for")
        val knownFor: List<KnownFor>?,
        @SerializedName("media_type")
        val mediaType: String,
        @SerializedName("name")
        val name: String?,
        @SerializedName("origin_country")
        val originCountry: List<String>?,
        @SerializedName("original_language")
        val originalLanguage: String?,
        @SerializedName("original_name")
        val originalName: String?,
        @SerializedName("original_title")
        val originalTitle: String?,
        @SerializedName("overview")
        val overview: String?,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("profile_path")
        val profilePath: String?,
        @SerializedName("release_date")
        val releaseDate: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("video")
        val video: Boolean?,
        @SerializedName("vote_average")
        val voteAverage: Double?,
        @SerializedName("vote_count")
        val voteCount: Int?
    ) {
        fun toUiModel() = SearchUiModel(
            name = getDisplayName(),
            imagePath = getImagePath(),
            navRoute = getNavRoute(),
            type = getType()
        )

        private fun getType(): Int =
            when(this.mediaType){
                "movie" -> {
                    R.drawable.ic_movie
                }
                "tv" -> {
                    R.drawable.ic_tvseries
                }
                else -> R.drawable.ic_person
            }

        private fun getDisplayName(): String? =
            when (this.mediaType) {
                "movie" -> this.originalTitle
                else -> this.name
            }

        private fun getImagePath(): String? =
            when (this.mediaType) {
                "person" -> this.profilePath
                else -> this.posterPath
            }

        private fun getNavRoute(): String =
            when (this.mediaType) {
                "movie" -> {
                    Route.MovieDetailScreen.route + "/${this.id}"
                }
                "tv" -> {
                    Route.TvDetailScreen.route + "/${this.id}"
                }
                else -> {
                    Route.CastScreen.route + "/${this.id}"
                }
            }

        data class KnownFor(
            @SerializedName("adult")
            val adult: Boolean?,
            @SerializedName("backdrop_path")
            val backdropPath: String,
            @SerializedName("first_air_date")
            val firstAirDate: String?,
            @SerializedName("genre_ids")
            val genreİds: List<Int>,
            @SerializedName("id")
            val id: Int,
            @SerializedName("media_type")
            val mediaType: String,
            @SerializedName("name")
            val name: String?,
            @SerializedName("origin_country")
            val originCountry: List<String>?,
            @SerializedName("original_language")
            val originalLanguage: String,
            @SerializedName("original_name")
            val originalName: String?,
            @SerializedName("original_title")
            val originalTitle: String?,
            @SerializedName("overview")
            val overview: String,
            @SerializedName("popularity")
            val popularity: Double,
            @SerializedName("poster_path")
            val posterPath: String,
            @SerializedName("release_date")
            val releaseDate: String?,
            @SerializedName("title")
            val title: String?,
            @SerializedName("video")
            val video: Boolean?,
            @SerializedName("vote_average")
            val voteAverage: Double,
            @SerializedName("vote_count")
            val voteCount: Int
        )
    }
}