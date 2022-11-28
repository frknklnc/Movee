package com.example.movee.data.repository

import com.example.movee.data.remote.service.ApiService
import com.example.movee.uimodels.CreditUiModel
import com.example.movee.uimodels.movie.MovieDetailUiModel
import com.example.movee.uimodels.movie.NowPlayingMovieUiModel
import com.example.movee.uimodels.movie.PopularMovieUiModel
import com.example.movee.util.Constants.API_KEY
import com.example.movee.util.Resource
import javax.inject.Inject

class MovieRepository @Inject constructor(private val service: ApiService) {

    suspend fun getPopularMoviesList(): Resource<List<PopularMovieUiModel>> {
        return try {
            val response = service.popularMovies(api_key = API_KEY)
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    return@let Resource.Success(res.movies.map { it.toUiModel() })
                } ?: Resource.Error("Error.")

            } else {
                Resource.Error("Error.")
            }
        } catch (e: Exception) {
            Resource.Error("Error.")
        }
    }


    suspend fun getNowPlayingMoviesList(): Resource<List<NowPlayingMovieUiModel>> {
        return try {
            val response = service.nowPlayingMovies(api_key = API_KEY)
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    return@let Resource.Success(res.movies.map { it.toUiModel() })
                } ?: Resource.Error("Error.")

            } else {
                Resource.Error("Error.")
            }
        } catch (e: Exception) {

            Resource.Error("Error.")
        }
    }

    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailUiModel> {
        return try {
            val response = service.movieDetails(api_key = API_KEY, movieId = movieId)
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
            val response = service.movieCredits(api_key = API_KEY, movieId = movieId)
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