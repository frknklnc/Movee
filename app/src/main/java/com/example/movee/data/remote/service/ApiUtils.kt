package com.example.movee.data.remote.service

class ApiUtils {

    companion object{

        val BASE_URL = "https://api.themoviedb.org/3/"

        fun getMovies(): MovieService {
            return RetrofitClient.getClient(BASE_URL).create(MovieService::class.java)
        }

        fun getTvSeries(): TvService{
            return RetrofitClient.getClient(BASE_URL).create(TvService::class.java)
        }

        fun getActors(): CastService{
            return RetrofitClient.getClient(BASE_URL).create(CastService::class.java)
        }
        fun getSearch(): SearchService{
            return RetrofitClient.getClient(BASE_URL).create(SearchService::class.java)
        }
    }
}