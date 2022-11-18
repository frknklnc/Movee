package com.example.movee.data.model.tv


import com.example.movee.uimodels.tv.PopularTvUiModel
import com.google.gson.annotations.SerializedName

data class PopularTvResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val tvSeries: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    data class Result(
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("first_air_date")
        val firstAirDate: String,
        @SerializedName("genre_ids")
        val genreİds: List<Int>,
        @SerializedName("id")
        val tvSeriesId: Int,
        @SerializedName("name")
        val title: String,
        @SerializedName("origin_country")
        val originCountry: List<String>,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_name")
        val originalName: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    ) {
        fun toUiModel() = PopularTvUiModel(
            tvSeriesId = tvSeriesId,
            title = title,
            posterPath = posterPath ?: "",
            voteAverage = voteAverage,
        )
    }

}