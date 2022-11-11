package com.example.movee.uimodels.movies

data class NowPlayingMovieUiModel(val movieId: Int,
                                  val releaseDate: String,
                                  val voteAverage: Double,
                                  val title: String,
                                  val posterPath: String
)