package com.example.movee.data.repository

import com.example.movee.data.remote.api.MoviesAPI
import com.example.movee.uimodels.MovieDetailUiModel
import com.example.movee.uimodels.NowPlayingMovieUiModel
import com.example.movee.uimodels.PopularMovieUiModel
import com.example.movee.util.Constants.API_KEY
import com.example.movee.util.Resource
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api : MoviesAPI) {

    suspend fun getPopularMoviesList():Resource<List<PopularMovieUiModel>>{
        return try{
            val response = api.popularMovies(api_key = API_KEY)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.Success(it.movies.map { it.toUiModel() })
                }?: Resource.Error("Error.")

            }else{
                Resource.Error("Error.")
            }
        }catch (e: Exception){
            Resource.Error("Error.")
        }
    }


    suspend fun getNowPlayingMoviesList():Resource<List<NowPlayingMovieUiModel>>{
        return try{
            val response = api.nowPlayingMovies(api_key = API_KEY)
            if (response.isSuccessful){
                response.body()?.let { res ->
                    return@let Resource.Success(res.movies.map { it.toUiModel() })
                }?: Resource.Error("Error.")

            }else{
                Resource.Error("Error.")
            }
        }catch (e: Exception){

            Resource.Error("Error.")
        }
    }

    suspend fun getMovieDetails(movieId: Int):Resource<MovieDetailUiModel>{
        return try{
            val response = api.movieDetails(api_key = API_KEY, movieId = movieId)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.Success(it.toUiModel())
                }?: Resource.Error("Error.")

            }else{
                Resource.Error("Error.")
            }
        }catch (e: Exception){

            Resource.Error("${e.message}")
        }
    }

}