package com.example.movee.uimodels.movie

import com.example.movee.uimodels.CreditUiModel

data class MovieDetailModel(val movieDetailUiModel: MovieDetailUiModel?,
                            val castList: List<CreditUiModel>)
