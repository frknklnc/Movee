package com.example.movee.data.remote.service

class ApiUtils {

    companion object{

        val BASE_URL = "https://api.themoviedb.org/3/"

        fun getData(): ApiService {
            return RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)
        }

    }
}