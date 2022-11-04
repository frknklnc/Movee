package com.example.movee.data.model

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    @SerializedName ("page") val page: Int,
    @SerializedName ("results") val movies: List<PopularMovies>,
    @SerializedName ("total_pages") val total_pages: Int,
    @SerializedName ("total_results") val total_results: Int
)