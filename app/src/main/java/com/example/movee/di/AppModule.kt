package com.example.movee.di

import com.example.movee.data.repository.MoviesRepository
import com.example.movee.data.remote.api.ApiUtils
import com.example.movee.data.remote.api.MoviesAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoviesAPI(): MoviesAPI {
        return ApiUtils.getMovies()
    }

    @Singleton
    @Provides
    fun provideMoviesRepo( api: MoviesAPI) : MoviesRepository {
        return MoviesRepository(api)
    }

}