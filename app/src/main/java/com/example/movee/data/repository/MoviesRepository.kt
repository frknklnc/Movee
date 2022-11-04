package com.example.movee.data.repository

import android.util.Log
import com.example.movee.data.remote.api.MoviesAPI
import com.example.movee.domain.model.PopularMovieUiModel
import com.example.movee.util.Constants.API_KEY
import com.example.movee.util.Resource
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api : MoviesAPI) {

    suspend fun getPopularMoviesList():Resource<List<PopularMovieUiModel>>{
        return try{
            val response = api.popularMovies(api_key = API_KEY)
            if (response.isSuccessful){
                response.body()?.let {
                    Log.e("error",response.body().toString())
                    return@let Resource.Success(it.movies.map { it.toUiModel() })
                }?: Resource.Error("Error.")

            }else{
                Log.e("error","else")
                Resource.Error("Error.")
            }
        }catch (e: Exception){
            Log.e("error","catch")
            Resource.Error("Error.")
        }
    }

}

    /*var moviesList: MutableLiveData<List<Movies>>

    init {
        moviesList = MutableLiveData()
    }


    /*fun getMoviesList(){
        api.popularMovies().enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful){
                    val list = response.body()!!.movies
                    moviesList.value = list
                }else{
                    Log.e("movies","${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e("movies","${t.printStackTrace()}")
            }

        })
    }*/


}



/*suspend fun getMoviesList(): Resource<MoviesResponse> {
        val response = try {
            api.popularMovies()
        }catch (e: Exception){
            return Resource.Error("Error.")
        }
        Log.e("asd","asd")
        return Resource.Success(response)
    }*/