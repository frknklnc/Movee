package com.example.movee.domain.model

data class PopularMovieUiModel(val id: Int,
                               val releaseDate: String,
                               val voteAverage: Double,
                               val title: String,
                               val posterPath: String
)