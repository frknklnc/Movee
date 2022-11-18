package com.example.movee.uimodels.movie

data class PopularMovieUiModel(val movieId: Int,
                               val releaseDate: String,
                               val voteAverage: Double,
                               val title: String,
                               val posterPath: String
)
