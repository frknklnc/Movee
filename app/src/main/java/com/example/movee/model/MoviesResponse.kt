package com.example.movee.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName ("page") val page: Int,
    @SerializedName ("movies") val movies: List<Movies>,
    @SerializedName ("total_pages") val total_pages: Int,
    @SerializedName ("total_results") val total_results: Int
)