package com.example.movee.data.repository

import com.example.movee.data.remote.model.favourite.FavouriteResponse
import com.example.movee.data.remote.model.movie.PopularMovieResponse
import com.example.movee.data.remote.service.ApiService
import com.example.movee.uimodels.CreditUiModel
import com.example.movee.uimodels.movie.MovieDetailUiModel
import com.example.movee.uimodels.movie.NowPlayingMovieUiModel
import com.example.movee.uimodels.movie.PopularMovieUiModel
import com.example.movee.util.Constants.API_KEY
import com.example.movee.util.Resource
import javax.inject.Inject

class MovieRepository @Inject constructor(private val service: ApiService) {

    suspend fun getPopularMoviesList(page:Int): Resource<PopularMovieResponse> {
        return try {
            val response = service.popularMovies(page)
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    return@let Resource.Success(res)
                } ?: Resource.Error("Error.")

            } else {
                Resource.Error("Error.")
            }
        } catch (e: Exception) {
            Resource.Error("${e.message}")
        }
    }


    suspend fun getNowPlayingMoviesList(): Resource<List<NowPlayingMovieUiModel>> {
        return try {
            val response = service.nowPlayingMovies()
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    return@let Resource.Success(res.movies.map { it.toUiModel() })
                } ?: Resource.Error("Error.")

            } else {
                Resource.Error("Error.")
            }
        } catch (e: Exception) {

            Resource.Error("${e.message}")
        }
    }

    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailUiModel> {
        return try {
            val response = service.movieDetails(movieId = movieId)
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    return@let Resource.Success(res.toUiModel())
                } ?: Resource.Error("Error.")

            } else {
                Resource.Error("Error.")
            }
        } catch (e: Exception) {

            Resource.Error("${e.message}")
        }
    }

    suspend fun getMovieCredits(movieId: Int): Resource<List<CreditUiModel>> {
        return try {
            val response = service.movieCredits(movieId = movieId)
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    return@let Resource.Success(res.cast.map { it.toUiModel() })
                } ?: Resource.Error("Error.")

            } else {
                Resource.Error("Error.")
            }
        } catch (e: Exception) {

            Resource.Error("${e.message}")
        }
    }

}